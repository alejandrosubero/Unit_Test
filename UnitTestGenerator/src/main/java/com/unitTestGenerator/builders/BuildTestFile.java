package com.unitTestGenerator.builders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BuildTestFile {

    public BuildTestFile() {
    }

    public static BuildTestFile getInstance(){
        return new BuildTestFile();
    }


    public  void crearArchivoPrueba(String ruta, String contenido) {
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
    private  void fileExist(File archivo){
        if (!archivo.getParentFile().exists()) {
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
}
