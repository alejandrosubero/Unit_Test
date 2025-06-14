package com.example.maven;


import java.io.File;

public class PomLocator {

    public static File findPomFile(String projectPath) {
        if (projectPath == null || projectPath.trim().isEmpty()) {
            System.err.println("Ruta del proyecto no válida.");
            return null;
        }

        File projectDir = new File(projectPath);

        if (!projectDir.exists() || !projectDir.isDirectory()) {
            System.err.println("El directorio del proyecto no existe o no es un directorio válido: " + projectPath);
            return null;
        }

        File pomFile = new File(projectDir, "pom.xml");

        if (!pomFile.exists() || !pomFile.isFile()) {
            System.err.println("No se encontró pom.xml en: " + pomFile.getAbsolutePath());
            return null;
        }

        return pomFile;
    }
}
