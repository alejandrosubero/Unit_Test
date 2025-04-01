package com.unitTestGenerator.analyzers.services.interfaces;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Constructor;
import com.unitTestGenerator.pojos.Project;
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



    default String buildClassDetail(Clase classs) {

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

            buffer.append("Class Package: ").append("\n");
            buffer.append("\t").append(classs.getPaquete()).append("\n");
            buffer.append(border).append("\n");

            buffer.append("Type Class: ").append("\n");
            buffer.append("\t").append(classs.getTypeClass()).append("\n");
            buffer.append(border).append("\n");

            buffer.append("Class Signature Line: ").append("\n");
            String signature = classs.getClassSignatureLine().replace("{","");
            buffer.append("\t").append(signature).append("\n");
            buffer.append(border).append("\n");

            buffer.append("Class classPath: ").append("\n");
            buffer.append("\t").append(classs.getClassPath()).append("\n");

            buffer.append(border).append("\n");
            buffer.append("Class UML Data Representation : ").append("\n");
            buffer.append(classs.getClassUml()).append("\n");


            buffer.append(border).append("\n");
            if (classs.getConstructores() != null && !classs.getConstructores().isEmpty()) {
                StringBuffer bufferConstructors = new StringBuffer();
                bufferConstructors.append("Class Constructors: ").append("\n");
                for (Constructor constructor : classs.getConstructores()) {
                    bufferConstructors.append("\t").append(constructor.getCostructorSignature()).append("\n");
                }
                buffer.append(bufferConstructors);
            }

            buffer.append(border).append("\n");
            buffer.append("Class Imports: ").append("\n");
            if (classs.getImports() != null && !classs.getImports().getProjectImports().isEmpty()) {
                if (!classs.getImports().getProjectImports().isEmpty()) {
                    StringBuffer bufferProjectImports = new StringBuffer();
                    buffer.append(border).append("\n");
                    bufferProjectImports.append("\t").append("Project Imports: ").append("\n");
                    classs.getImports().getProjectImports().forEach(imports -> {
                        bufferProjectImports.append("\t").append(imports).append("\n");
                    });
                    buffer.append(bufferProjectImports);
                }


                if (!classs.getImports().getExternalImports().isEmpty()) {
                    StringBuffer bufferExternalImports = new StringBuffer();

                    buffer.append(border).append("\n");
                    bufferExternalImports.append("\t").append("External Imports: ").append("\n");
                    classs.getImports().getExternalImports().forEach(imports -> {
                        bufferExternalImports.append("\t").append(imports).append("\n");
                    });
                    buffer.append(bufferExternalImports);
                }
            }

            if(classs.getStructureInterface() != null && !classs.getStructureInterface().equals("")){
                StringBuffer bufferInterface = new StringBuffer();
                buffer.append(border).append("\n");
                bufferInterface.append("Interface Structure: ").append("\n");
                bufferInterface.append("\t").append(classs.getStructureInterface()).append("\n");
                buffer.append(bufferInterface);
            }

            if(classs.getStructureExtends() != null && !classs.getStructureExtends().equals("")) {
                StringBuffer bufferInterfaceExtends = new StringBuffer();
                bufferInterfaceExtends.append(border).append("\n");
                bufferInterfaceExtends.append("Interface Extends: ").append("\n");
                bufferInterfaceExtends.append("\t").append(classs.getStructureExtends()).append("\n");
                buffer.append(bufferInterfaceExtends);
            }

            if(classs.getClassAnotations() != null && !classs.getClassAnotations().equals("")){
                StringBuffer bufferAnotations = new StringBuffer();
                buffer.append(border).append("\n");
                bufferAnotations.append("Class Anotations: ").append("\n");
                bufferAnotations.append("\t").append(classs.getClassAnotations()).append("\n");
                buffer.append(bufferAnotations);
            }

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
