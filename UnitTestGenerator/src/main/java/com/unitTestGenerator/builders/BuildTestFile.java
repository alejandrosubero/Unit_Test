package com.unitTestGenerator.builders;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.TestFileContent;
import com.unitTestGenerator.services.AnalyzeClassService;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;
import java.io.FileWriter;
import java.io.IOException;



import org.apache.commons.io.FileUtils;

public class BuildTestFile {

    public BuildTestFile() {
    }

    public static BuildTestFile getInstance(){
        return new BuildTestFile();
    }


    public  void createTestFile(String ruta, TestFileContent content) {
        File archivo = new File(ruta);
        String oldVlue = fileExist(archivo);

        if(oldVlue != null && !oldVlue.equals("")){
            this.JavaFileEditor(oldVlue,content);
        }

        this.writefiles( archivo,content.toString());
//        fileWriter( archivo,contenido,ruta);
    }


    private String fileExist(File archivo){
        String contenido ="";
        if (!archivo.getParentFile().exists()) {
            if (archivo.getParentFile().mkdirs()) {
                System.out.println("Carpeta creada con éxito: " + archivo.getParentFile());
            } else {
                System.out.println("Error al crear carpeta: " + archivo.getParentFile());
            }
        }else{
            try {
                contenido = FileUtils.readFileToString(archivo, "UTF-8");
                return contenido;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return contenido;
    }


    public void writefiles(File fileToWrite, String content){
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileToWrite, false);
            writer.write(content);
            System.out.println("Write Successful :" + fileToWrite.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error in process write file: " + fileToWrite.getAbsolutePath());
            System.err.println("Error : " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Error to close file: " + e.getMessage());
            }
        }
    }


    public String JavaFileEditor( String oldContentValue, TestFileContent newfileContent) {
        try {

            String contentWithVariables = "";
            String updatedContent = "";
            Integer lastBraceIndex = oldContentValue.lastIndexOf("}");
            Integer firstBraceIndex = oldContentValue.indexOf("{");

            if (lastBraceIndex == -1) {
                return "The file Don't have \" } \" ";
            }
            //TODO: SE REQUIERE UN METODO QUE ANALICE SI EXISTE EN EL CONTENIDO LAS VARIABLES Y EN LOS METODOS EL METODO PARA MODIFICARLO

            Clase newClassContent = AnalyzeClassService.getInstance().analyzeClaseContentString(newfileContent.toString());
            Clase oldClassContent = AnalyzeClassService.getInstance().analyzeClaseContentString(oldContentValue);

            if (newfileContent != null && newfileContent.getTestsClassVariables() != null) {
                oldClassContent.getVariables().forEach(variable -> {
                    if(!newClassContent.getVariables().contains(variable)){
                        contentWithVariables = this.formatUpdatedContent(oldContentValue, firstBraceIndex, newfileContent.getTestsClassVariables());
                    }
                });
                //        AnalyzeClassService.getInstance().getAnalisisOfVariables()
            }

            if (newfileContent != null && newfileContent.getTestsClassMethods() != null) {
                //        AnalyzeClassService.getInstance().getAnalisisOfContenidoMetodo()
                updatedContent = this.formatUpdatedContent(contentWithVariables, lastBraceIndex, newfileContent.getTestsClassMethods());
            }

           return updatedContent;

        } catch (Exception e) {
            System.err.println("Error in processing File: " + e.getMessage());
            return "";
        }
    }


    private static String formatUpdatedContent(String content, Integer braceIndex, String newContent) {
        if( content != null &&  braceIndex != null && newContent != null) {
            return String.format("%s\n%s\n%s", content.substring(0, braceIndex), newContent, content.substring(braceIndex));
        }
        return "Null content...";
    }


    private String getContentActual(File file, String ruta){
        Stream<String> fileStream = null;
        String content ="";
        try {
            fileStream = Files.lines(file.toPath());
            content = fileStream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            fileStream.close();
        }
        return content;
    }

    private  void fileWriter(File archivo, String contenido, String ruta){
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
            System.out.println("Prueba generada con éxito en " + ruta);
        } catch (IOException e) {
            System.out.println("Error al crear archivo de prueba: " + e.getMessage());
        }
    }


}
