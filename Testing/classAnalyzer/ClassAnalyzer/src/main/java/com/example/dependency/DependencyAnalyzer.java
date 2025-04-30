package com.example.dependency;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DependencyAnalyzer {

    // Estructura para guardar dependencias
    private final Map<String, Set<String>> dependencyTree = new HashMap<>();

    /**
     * Analiza una lista de clases (en forma de String) y genera el árbol de dependencias.
     *
     * @param classSources Lista de código fuente de las clases
     * @param targetClassName Nombre de la clase a buscar dependencias (ej: "MyService")
     */
    public void analyzeDependencies(List<String> classSources, String targetClassName) {
        String simpleClassName = targetClassName.contains(".")
                ? targetClassName.substring(targetClassName.lastIndexOf(".") + 1)
                : targetClassName;

        for (String classSource : classSources) {
            String className = extractClassName(classSource);
            if (className == null) {
                continue; // No se pudo identificar el nombre de la clase
            }

            if (usesTargetClass(classSource, simpleClassName)) {
                dependencyTree.computeIfAbsent(simpleClassName, k -> new HashSet<>()).add(className);
            }
        }
    }

    /**
     * Imprime el árbol de dependencias en forma estructurada.
     */
    public void printDependencyTree() {

        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Set<String>> entry : dependencyTree.entrySet()) {
            buffer.append(entry.getKey());
            System.out.println(entry.getKey());
            for (String dependent : entry.getValue()) {
                System.out.println(" └── " + dependent);
            }
        }
    }

    // Extrae el nombre de la clase del código fuente
    private String extractClassName(String classSource) {
        Pattern pattern = Pattern.compile("class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(classSource);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // Determina si la clase usa la clase objetivo
    private boolean usesTargetClass(String classSource, String targetClassName) {
        return containsImport(classSource, targetClassName) ||
                containsExtends(classSource, targetClassName) ||
                containsInjection(classSource, targetClassName) ||
                containsNewInstance(classSource, targetClassName) ||
                containsStaticUsage(classSource, targetClassName);
    }

    private boolean containsImport(String classSource, String targetClassName) {
        return classSource.contains("import ") && classSource.contains(targetClassName + ";");
    }

    private boolean containsExtends(String classSource, String targetClassName) {
        return classSource.contains("extends " + targetClassName);
    }

    private boolean containsInjection(String classSource, String targetClassName) {
        Pattern fieldPattern = Pattern.compile(targetClassName + "\\s+\\w+\\s*;");
        Pattern constructorPattern = Pattern.compile("\\(" + "\\s*" + targetClassName + "\\s+\\w+\\s*\\)");
        return fieldPattern.matcher(classSource).find() || constructorPattern.matcher(classSource).find();
    }

    private boolean containsNewInstance(String classSource, String targetClassName) {
        Pattern pattern = Pattern.compile("new\\s+" + targetClassName + "\\s*\\(");
        return pattern.matcher(classSource).find();
    }

    private boolean containsStaticUsage(String classSource, String targetClassName) {
        Pattern pattern = Pattern.compile(targetClassName + "\\.");
        return pattern.matcher(classSource).find();
    }
}
