package com.example;

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
                "package test; public class W implements D {}"
        );

        ClassRelationAnalyzer analyzer = new ClassRelationAnalyzer();
        analyzer.analyzeClasses(javaSources);

        ClassNode tree = analyzer.buildTree("A");
//        analyzer.printTree(tree, "");

        analyzer.printTree(tree, "", true);

    }
}