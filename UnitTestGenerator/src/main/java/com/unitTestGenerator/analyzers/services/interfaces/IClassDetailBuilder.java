package com.unitTestGenerator.analyzers.services.interfaces;

import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Constructor;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.uml.sevices.PrintClassToUML;
import com.unitTestGenerator.util.IRepeatLogic;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import java.util.Optional;

public interface IClassDetailBuilder extends IRepeatLogic {


    default Project generateClassesDetail(Project project) {
        Optional.ofNullable(project)
                .map(Project::getClaseList)
                .ifPresent(clases -> clases.forEach(clase -> {
                    clase.setClassDetail(this.buildClassDetail(clase));
                }));
        return project;
    }


//TODO : SEPARAR DEL DETAIL Y DETAL HTML TEMPLATE, HAY ERRORES EN HTML QUE CONTINUAR REPARANDO
    default String buildClassDetail(Clase classs) {

        String templete = ReadResourceFile();

        StringBuffer buffer = new StringBuffer();
        if (classs != null) {
            String borderClass = repeat("<*><*>", 40 ) + "+\n";
            String border = repeat("---", 40 + 2) ;

            buffer.append("\n");
            buffer.append(borderClass).append("\n");
            buffer.append(border).append("\n");

            buffer.append("Class Name: ").append("\n");
            buffer.append("\t").append(classs.getNombre()).append("\n");
            buffer.append(border).append("\n");
            templete = templete.replace("@NombreClaseTitle@", classs.getNombre());

            buffer.append("Class Package: ").append("\n");
            buffer.append("\t").append(classs.getPaquete()).append("\n");
            buffer.append(border).append("\n");
            templete = templete.replace("@paquetico@", classs.getPaquete());

            buffer.append("Type Class: ").append("\n");
            buffer.append("\t").append(classs.getTypeClass()).append("\n");
            buffer.append(border).append("\n");
            templete = templete.replace("@ClassType@", classs.getTypeClass());

            buffer.append("Class Signature Line: ").append("\n");
            String signature = classs.getClassSignatureLine().replace("{","");
            buffer.append("\t").append(signature).append("\n");
            buffer.append(border).append("\n");
            templete = templete.replace("@classsignatureline@", signature);

            buffer.append("Class classPath: ").append("\n");
            buffer.append("\t").append(classs.getClassPath()).append("\n");
            templete = templete.replace("@classclasspath@", classs.getClassPath());

            buffer.append(border).append("\n");
            buffer.append("Class UML Data Representation : ").append("\n");
            buffer.append(classs.getClassUml()).append("\n");
            templete = templete.replace("@NombreClase@", classs.getNombre());

            PrintClassToUML printClassToUML =  ContextIOC.getInstance().getClassInstance(PrintClassToUML.class);
            String atributos = printClassToUML.getAttributes(classs);
            String metodos =  printClassToUML.getMethods(classs);
            String relations = printClassToUML.getRelations(classs);

           if( atributos != null && !atributos.equals("")){
               String temp = atributos;
               String [] partTemp = temp.split( "\n");
               StringBuffer bufferAttributes = new StringBuffer();

               for(String attribute: partTemp){
                       String tempA =  "<div class=\"costructorElement\"> @aTemp@ </div>";
                       tempA = tempA.replace("@aTemp@", attribute);
                       bufferAttributes.append(tempA);
               }
               templete = templete.replace("@Atributos@", bufferAttributes.toString());
           } else {
               templete =  templete.replace("@Atributos@", "");
           }

            if( metodos != null && !metodos.equals("")){
                String temp = metodos;
                String [] partTemp = temp.split( "\n");
//               "Methods: "
                StringBuffer bufferAttributes = new StringBuffer();

                for(String attribute: partTemp){
                        String tempA =  "<div class=\"costructorElement\"> @aTemp@ </div>";
                        tempA = tempA.replace("@aTemp@", attribute);
                        bufferAttributes.append(tempA);
                }
                templete = templete.replace("@Metodos@", bufferAttributes.toString());
            } else {
                templete =  templete.replace("@Metodos@", "");
            }

//            templete = atributos != null && !atributos.equals("")?templete.replace("@Atributos@", printClassToUML.getAttributes(classs)):templete.replace("@Atributos@", "");
//            templete = metodos != null && !metodos.equals("")?templete.replace("@Metodos@", printClassToUML.getMethods(classs)):templete.replace("@Metodos@", "");

            ...
            templete = relations != null && !relations.equals("")? templete.replace("@Relations@", printClassToUML.getRelations(classs)):  templete.replace("@Relations@", "");


            buffer.append(border).append("\n");
            String line = "<div class=\"costructorElement\"> + @constructors@ </div>";
            if (classs.getConstructores() != null && !classs.getConstructores().isEmpty()) {
                StringBuffer bufferConstructors = new StringBuffer();
                bufferConstructors.append("Class Constructors: ").append("\n");
                for (Constructor constructor : classs.getConstructores()) {
                    bufferConstructors.append("\t").append(constructor.getCostructorSignature()).append("\n");
                }
                line = line.replace("@constructors@",bufferConstructors.toString());
                templete = templete.replace("@listConstructors@",line);

                buffer.append(bufferConstructors);
            }else {
                templete = templete.replace("@listConstructors@","");
            }


            buffer.append(border).append("\n");
            buffer.append("Class Imports: ").append("\n");

            if (classs.getImports() != null && !classs.getImports().getProjectImports().isEmpty()) {
                StringBuffer importsTemplate = new StringBuffer();
                if (!classs.getImports().getProjectImports().isEmpty()) {
                    StringBuffer bufferProjectImportsTemp = new StringBuffer();
                    buffer.append(border).append("\n");
                    buffer.append("\t").append("Project Imports: ").append("\n");
                    bufferProjectImportsTemp.append("\t").append("Project Imports: ").append("\n");

                    for ( String imports : classs.getImports().getProjectImports()){
                        buffer.append("\t").append(imports).append("\n");
                        String externalImport =  "<div class=\"costructorElement\"> @import@ </div>";
                        externalImport = externalImport.replace("@import@",imports);
                        bufferProjectImportsTemp.append(externalImport).append("\n");
                    }
                    importsTemplate.append(bufferProjectImportsTemp);
                }


                if (!classs.getImports().getExternalImports().isEmpty()) {
                    StringBuffer bufferExternalImportsTemp = new StringBuffer();
                    buffer.append(border).append("\n");
                    buffer.append("\t").append("External Imports: ").append("\n");
                    bufferExternalImportsTemp.append("\t").append("External Imports: ").append("\n");

                    for ( String imports : classs.getImports().getExternalImports()){
                        buffer.append("\t").append(imports).append("\n");
                        String externalImport =  "<div class=\"costructorElement\"> @import@ </div>";
                         externalImport = externalImport.replace("@import@",imports);
                        bufferExternalImportsTemp.append(externalImport).append("\n");
                    }
                    importsTemplate.append(bufferExternalImportsTemp);
                }
                templete = templete.replace("@ClassImports@",importsTemplate);
            }else{
                templete = templete.replace("@ClassImports@","");
            }



            if(classs.getStructureInterface() != null && !classs.getStructureInterface().equals("")){
                StringBuffer bufferInterface = new StringBuffer();
                buffer.append(border).append("\n");
                bufferInterface.append("Interface Structure: ").append("\n");
                bufferInterface.append("\t").append(classs.getStructureInterface()).append("\n");
                templete = templete.replace("@Structure-Import@",classs.getStructureInterface());
                buffer.append(bufferInterface);
            }else{
                templete = templete.replace("@Structure-Import@","");
            }

            if(classs.getStructureExtends() != null && !classs.getStructureExtends().equals("")) {
                StringBuffer bufferInterfaceExtends = new StringBuffer();
                bufferInterfaceExtends.append(border).append("\n");
                bufferInterfaceExtends.append("Interface Extends: ").append("\n");
                bufferInterfaceExtends.append("\t").append(classs.getStructureExtends()).append("\n");
                templete = templete.replace("@Structure-Extends@",classs.getStructureExtends());
                buffer.append(bufferInterfaceExtends);
            }else {
                templete = templete.replace("@Structure-Extends@","");
            }

            if(classs.getClassAnotations() != null && !classs.getClassAnotations().equals("")){
                StringBuffer bufferAnotations = new StringBuffer();
                buffer.append(border).append("\n");
                bufferAnotations.append("Class Anotations: ").append("\n");
                bufferAnotations.append("\t").append(classs.getClassAnotations()).append("\n");
                templete = templete.replace("@anotations@",classs.getClassAnotations());
                buffer.append(bufferAnotations);
            }else {
                templete = templete.replace("@anotations@","");
            }

            classs.setClassTemplate(templete);
            buffer.append(border).append("\n");
            buffer.append(borderClass).append("\n");
        }

        return buffer.toString();
    }


//    String templete = ReadResourceFile();

    default String ReadResourceFile (){
            // Obtener el archivo como InputStream
        String contenido ="";
            try (InputStream inputStream = IClassDetailBuilder.class.getClassLoader().getResourceAsStream("template.html")) {

                if (inputStream == null) {
                    throw new RuntimeException("Archivo no encontrado: template.html");
                }

              contenido = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                System.out.println(contenido);

            } catch (Exception e) {
                e.printStackTrace();
            }
return contenido;
    }


}
