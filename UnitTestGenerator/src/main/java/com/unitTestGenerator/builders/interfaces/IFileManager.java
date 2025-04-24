package com.unitTestGenerator.builders.interfaces;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.util.IBaseModel;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IFileManager extends IBaseModel {

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
            System.out.println("Write Successful :" +fileToWrite.getName()+" Path: "+ fileToWrite.getAbsolutePath());
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


    default  String getFileContent(File file, String filePath){
        Stream<String> fileStream = null;
        String content ="";
        try {
            if(file != null){
                fileStream = Files.lines(file.toPath());
            } else if(filePath != null){
                fileStream = Files.lines(Paths.get(filePath));
            }
            content = fileStream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            fileStream.close();
        }
        return content;
    }

    default String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }


    default Boolean filePathExists(String filePath ) {

        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            System.out.println("the file exist in : " + filePath);
            return true;
        } else {
            System.out.println("the file does't exist in : " + filePath);
            return false;
        }
    }

    default String getPathOfTest(Clase clase, String pathProject){
        String claseName = clase.getNombre();
        String basePath=  this.stringPaths(true,true,"src","test","java");//"/src/test/java/"
        String packagePath = clase.getPaquete().replace(".", Separator);
        String pathOfTest = this.stringEnsamble(pathProject,basePath, packagePath, Separator, claseName, "Test.java");
        return pathOfTest;
    }


    // Método usando NIO (Java 7+ - recomendado)
    public static boolean deleteFileNIO(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo no puede ser nula o vacía");
        }

        try {
            Path path = Paths.get(filePath);
            // Verificar que es un archivo regular y existe
            if (!Files.isRegularFile(path)) {
                System.err.println("La ruta no corresponde a un archivo regular: " + filePath);
                return false;
            }
            // Eliminar el archivo
            return Files.deleteIfExists(path);

        } catch (SecurityException e) {
            System.err.println("Error de permisos al eliminar: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error al eliminar archivo: " + e.getMessage());
            return false;
        }
    }

    // Método usando IO (Java legacy)
    public static boolean deleteFileIO(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo no puede ser nula o vacía");
        }
        File file = new File(filePath);
        // Validaciones adicionales
        if (!file.exists()) {
            System.err.println("El archivo no existe: " + filePath);
            return false;
        }
        if (!file.isFile()) {
            System.err.println("La ruta no es un archivo: " + filePath);
            return false;
        }
        return file.delete();
    }

}
