package com.unitTestGenerator.builders;

import com.unitTestGenerator.pojos.TestFileContent;
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


    public  void createTestFile(String ruta, String contenido) {
        File archivo = new File(ruta);
        fileExist(archivo);
        fileWriter( archivo,contenido,ruta);
    }

    private  void fileWriter(File archivo, String contenido, String ruta){
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
            System.out.println("Prueba generada con éxito en " + ruta);
        } catch (IOException e) {
            System.out.println("Error al crear archivo de prueba: " + e.getMessage());
        }
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
        return null;
    }


    private String getContentActual(String content){
    //TODO: HACER INGENIERIA INVERSA CON ANALIZAR PARA CREAR UNA CLASE DE ESTO Y TRATAR DE USAR LO QUE SE NECESITE E IDENTIFICAR QUE ES LO QUE VAS A HACER
        return null;
    }

    //TODO: LA FINALIDAD ES VERIFICAR SI ESXISTE YA EL ARCHIVO CREADO Y PODER REESCRIBIR DEJENDO LO ANTERIOR O CREAR UNO NUEVO APARTE O REESCRIBIR TODO COMPLETAMENTE.
    private  void fileExistS(File archivo){
        if (archivo.getParentFile().exists()) {

            try {
                String contenido = FileUtils.readFileToString(archivo, "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Crear la carpeta si no existe
            if (archivo.getParentFile().mkdirs()) {
                System.out.println("Carpeta creada con éxito: " + archivo.getParentFile());
            } else {
                System.out.println("Error al crear carpeta: " + archivo.getParentFile());
            }
        }
    }

    public void writefiles(File archivoGradle, String contenido){
        FileWriter writer = null;
        try {
            writer = new FileWriter(archivoGradle, false);
            writer.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    System.out.println("Dependencia agregada con éxito");
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }





    public void JavaFileEditor( String filePath, TestFileContent fileContent ) {

        try {
            Stream<String> fileStream = Files.lines(Paths.get(filePath));
            String content = fileStream.collect(Collectors.joining("\n"));
            fileStream.close();

            int lastBraceIndex = content.lastIndexOf("}");

            if (lastBraceIndex == -1) {
                throw new IllegalArgumentException("El archivo no tiene un cierre de llave.");
            }

            // Modificar el contenido en una sola operación
            String updatedContent = content.substring(0, content.indexOf("{") + 1) + "\n" +
                    fileContent.getTestsClassVariables() + "\n" +
                    content.substring(content.indexOf("{") + 1, content.lastIndexOf("}")) +
                    fileContent.getTestsClassMethods() + "\n" +
                    content.substring(content.lastIndexOf("}"));

//            String testMethodCall = String.format("%s.%s(%s)", classNameCamelCase, method.getNombre(), parametersMethodTest);

            // Escribir el contenido modificado de vuelta al archivo
            Files.write(Paths.get(filePath), updatedContent.getBytes());
            System.out.println("Archivo modificado y guardado exitosamente.");

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
        }
    }

}
