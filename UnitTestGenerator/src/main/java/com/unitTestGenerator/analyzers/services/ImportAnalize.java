package com.unitTestGenerator.analyzers.services;


import com.unitTestGenerator.pojos.ImportAnalizePojo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImportAnalize {



    public static ImportAnalizePojo importAnalize(String filePath, String projectName, String javaCode) {

        try {

            // Extraer imports con regex
            Pattern importPattern = Pattern.compile("import\\s+([\\w.]+);");
            Matcher matcher = importPattern.matcher(javaCode);

            String projectBasePackage = extractBasePackage(filePath, projectName);
            StringBuilder projectImports = new StringBuilder();
            StringBuilder externalImports = new StringBuilder();

            while (matcher.find()) {
                String importStatement = matcher.group(1);
                if (importStatement.startsWith(projectBasePackage)) {
                    projectImports.append("import ").append(importStatement).append(";\n");
                } else {
                    externalImports.append("import ").append(importStatement).append(";\n");
                }
            }

            // Reconstrucción del código sin modificarlo
            String reconstructedCode = externalImports.toString() + "\n" + projectImports.toString() + "\n" + javaCode;

            System.out.println("===== EXTERNAL IMPORTS =====");
            System.out.println(externalImports);
            System.out.println("===== PROJECT IMPORTS =====");
            System.out.println(projectImports);
            System.out.println("===== FULL RECONSTRUCTED CODE =====");
            System.out.println(reconstructedCode);

            ImportAnalizePojo importAnalizePojo = new  ImportAnalizePojo( externalImports.toString(), projectImports.toString());
            return importAnalizePojo;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractBasePackage(String filePath, String projectName) {
        String normalizedPath = filePath.replace("\\", "/");
        int projectIndex = normalizedPath.indexOf("/" + projectName + "/");
        if (projectIndex == -1) {
            throw new IllegalArgumentException("No se pudo determinar el paquete base para el proyecto: " + projectName);
        }

        String basePath = normalizedPath.substring(projectIndex + projectName.length() + 2);
        String[] pathParts = basePath.split("/");
        int srcIndex = -1;

        for (int i = 0; i < pathParts.length; i++) {
            if (pathParts[i].equals("src")) {
                srcIndex = i;
                break;
            }
        }

        if (srcIndex == -1 || srcIndex + 2 >= pathParts.length) {
            throw new IllegalArgumentException("Formato del path no reconocido");
        }

        String[] packageParts = new String[pathParts.length - (srcIndex + 2)];
        System.arraycopy(pathParts, srcIndex + 2, packageParts, 0, packageParts.length);
        return String.join(".", packageParts);
    }
}
