package com.unitTestGenerator.interfaces;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IFileManager {

    default String fileExist(File file){
        String content ="";
        if (!file.getParentFile().exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("Carpeta creada con éxito: " + file.getParentFile());
            } else {
                System.out.println("Error al crear carpeta: " + file.getParentFile());
            }
        }else{
            if(file.exists()) {
                try {
                    content = FileUtils.readFileToString(file, "UTF-8");
                    return content;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return content;
    }


    default void writefilesI(File fileToWrite, String content){
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


    default   void fileWriter(File archivo, String contenido, String ruta){
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
            System.out.println("Prueba generada con éxito en " + ruta);
        } catch (IOException e) {
            System.out.println("Error al crear archivo de prueba: " + e.getMessage());
        }
    }


    default  String getFileContent(File file, String ruta){
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

}
