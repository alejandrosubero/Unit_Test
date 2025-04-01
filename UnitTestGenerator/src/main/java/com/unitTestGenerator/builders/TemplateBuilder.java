package com.unitTestGenerator.builders;

import com.unitTestGenerator.analyzers.services.interfaces.IClassDetailBuilder;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Constructor;
import com.unitTestGenerator.uml.sevices.PrintClassToUML;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class TemplateBuilder {

    public TemplateBuilder() {
    }


    separar en varias clases ...
    public void buildClassDetailHtml(Clase classs) {

        String templete = ReadResourceFile();

        if (classs != null) {

            templete = templete.replace("@NombreClaseTitle@", classs.getNombre());
            templete = templete.replace("@paquetico@", classs.getPaquete());
            templete = templete.replace("@ClassType@", classs.getTypeClass());
            String signature = classs.getClassSignatureLine().replace("{","");
            templete = templete.replace("@classsignatureline@", signature);
            templete = templete.replace("@classclasspath@", classs.getClassPath());
            templete = templete.replace("@NombreClase@", classs.getNombre());

            PrintClassToUML printClassToUML =  ContextIOC.getInstance().getClassInstance(PrintClassToUML.class);
            String atributos = printClassToUML.getAttributes(classs);
            String metodos =  printClassToUML.getMethods(classs);

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

            if (classs.getClassRelations() != null && classs.getClassRelations().check()) {

                String getRelationsImplement = printClassToUML.getRelationsImplement( classs);
                String getRelationsExtends =printClassToUML.getRelationsExtends( classs);
                String getRelationsIoc =printClassToUML.getRelationsIoc( classs);
                String getRelationsStrongAssociation =printClassToUML.getRelationsStrongAssociation( classs);
                String getRelationsAssociationStaticOrPatterBuild =printClassToUML.getRelationsAssociationStaticOrPatterBuild( classs);

                StringBuffer relationBuffer = new StringBuffer();

                if (getRelationsImplement != null && !getRelationsImplement.equals("")) {
                    String temp = getRelationsImplement;
                    relationBuffer.append(relationHtml(temp));
                }

                if (getRelationsExtends != null && !getRelationsExtends.equals("")) {
                    String temp = getRelationsExtends;
                    relationBuffer.append(relationHtml(temp));
                }

                if (getRelationsIoc != null && !getRelationsIoc.equals("")) {
                    String temp = getRelationsIoc;
                    relationBuffer.append(relationHtml(temp));
                }

                if (getRelationsStrongAssociation != null && !getRelationsStrongAssociation.equals("")) {
                    String temp = getRelationsStrongAssociation;
                    relationBuffer.append(relationHtml(temp));
                }

                if (getRelationsAssociationStaticOrPatterBuild != null && !getRelationsAssociationStaticOrPatterBuild.equals("")) {
                    String temp = getRelationsAssociationStaticOrPatterBuild;
                    relationBuffer.append(relationHtml(temp));
                }

                templete = templete.replace("@Relations@", relationBuffer.toString());
            } else {
                templete = templete.replace("@Relations@", "");
            }



            String line = "<div class=\"costructorElement\"> + @constructors@ </div>";
            if (classs.getConstructores() != null && !classs.getConstructores().isEmpty()) {
                StringBuffer bufferConstructors = new StringBuffer();
                bufferConstructors.append("Class Constructors: ").append("\n");
                for (Constructor constructor : classs.getConstructores()) {
                    bufferConstructors.append("\t").append(constructor.getCostructorSignature()).append("\n");
                }
                line = line.replace("@constructors@",bufferConstructors.toString());
                templete = templete.replace("@listConstructors@",line);

            }else {
                templete = templete.replace("@listConstructors@","");
            }

            if (classs.getImports() != null && !classs.getImports().getProjectImports().isEmpty()) {
                StringBuffer importsTemplate = new StringBuffer();
                if (!classs.getImports().getProjectImports().isEmpty()) {
                    StringBuffer bufferProjectImportsTemp = new StringBuffer();
                    bufferProjectImportsTemp.append("\t").append("Project Imports: ").append("\n");

                    for ( String imports : classs.getImports().getProjectImports()){
                        String externalImport =  "<div class=\"costructorElement\"> @import@ </div>";
                        externalImport = externalImport.replace("@import@",imports);
                        bufferProjectImportsTemp.append(externalImport).append("\n");
                    }
                    importsTemplate.append(bufferProjectImportsTemp);
                }


                if (!classs.getImports().getExternalImports().isEmpty()) {
                    StringBuffer bufferExternalImportsTemp = new StringBuffer();
                    bufferExternalImportsTemp.append("\t").append("External Imports: ").append("\n");

                    for ( String imports : classs.getImports().getExternalImports()){
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
                bufferInterface.append("Interface Structure: ").append("\n");
                bufferInterface.append("\t").append(classs.getStructureInterface()).append("\n");
                templete = templete.replace("@Structure-Import@",bufferInterface.toString());
            }else{
                templete = templete.replace("@Structure-Import@","");
            }

            if(classs.getStructureExtends() != null && !classs.getStructureExtends().equals("")) {
                StringBuffer bufferInterfaceExtends = new StringBuffer();
                bufferInterfaceExtends.append("Interface Extends: ").append("\n");
                bufferInterfaceExtends.append("\t").append(classs.getStructureExtends()).append("\n");
                templete = templete.replace("@Structure-Extends@",bufferInterfaceExtends.toString());
            }else {
                templete = templete.replace("@Structure-Extends@","");
            }

            if(classs.getClassAnotations() != null && !classs.getClassAnotations().equals("")){
                StringBuffer bufferAnotations = new StringBuffer();
                bufferAnotations.append("Class Anotations: ").append("\n");
                bufferAnotations.append("\t").append(classs.getClassAnotations()).append("\n");
                templete = templete.replace("@anotations@",bufferAnotations.toString());
            }else {
                templete = templete.replace("@anotations@","");
            }

            classs.setClassTemplate(templete);
        }
    }



    private String relationHtml(String retation){
        StringBuffer relationBuffer = new StringBuffer();
        String temp = retation;
        String[] partTemp = temp.split("\n");

        for(String attribute: partTemp){
            String tempA =  "<div class=\"costructorElement\"> @aTemp@ </div>";
            tempA = tempA.replace("@aTemp@", attribute);
            relationBuffer.append(tempA);
        }
        return relationBuffer.toString();
    }

    private String ReadResourceFile (){
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
