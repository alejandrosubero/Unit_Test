package com.unitTestGenerator.analyzers.services;


import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.ImportAnalizePojo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImportAnalize {
//    ImportAnalize.importAnalize(String filePath, String projectName, String javaCode);


    public static ImportAnalizePojo importAnalize(Clase clase, String projectNamx) {

        ImportAnalizePojo importAnalizePojo = null;

//        String packageBaseName = DynamicPackageRootFinder.getName(clase);
        String packageBaseName = projectNamx;
        try {
            if(packageBaseName != null && !packageBaseName.equals("")){
                Pattern importPattern = Pattern.compile("import\\s+([\\w.]+);");
                Matcher matcher = importPattern.matcher(clase.getRawClass());

                String projectBasePackage = extractBasePackage(clase.getClassPath(), packageBaseName);
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
                String reconstructedCode = externalImports.toString() + "\n" + projectImports.toString() + "\n" + clase.getClassPath();

                importAnalizePojo = ImportAnalizePojo.builder().externalImports(externalImports.toString()).projectImports(projectImports.toString()).build();
                clase.setRawClass(reconstructedCode);
                clase.setImports(importAnalizePojo);
            }
            return importAnalizePojo;

        } catch (Exception e) {
            e.printStackTrace();
            return importAnalizePojo;
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
