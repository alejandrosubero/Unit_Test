package com.example;

import com.example.classrelationanalyzer.ClassNode;
import com.example.classrelationanalyzer.ClassRelationAnalyzer;

import java.util.Arrays;
import java.util.List;

public class Main {
    // Método principal para probar
    public static void main(String[] args) {
        List<String> javaSources = Arrays.asList(
                // Interface A
                "package test; public interface A {}",
                // Interface C extends A
                "package test; public interface C extends A {}",
                // Interface D extends C
                "package test; public interface D extends C {}",
                // Class B implements A
                "package test; public class B implements A {}",
                // Class W implements D
                "package test; public class W implements D {}",
                "package test; public class S implements D {}",
                "package test; public class S implements C {}",
                "package test; public interface H {}",
                "package test; public class P implements H {}"
        );

        ClassRelationAnalyzer analyzer = new ClassRelationAnalyzer();
        analyzer.analyzeClasses(javaSources);

        ClassNode tree = analyzer.buildTree("C");
        // Generar el String
        String treeAsString = analyzer.buildTreeString(tree);
        // Imprimirlo
        System.out.println(treeAsString);

    }
}