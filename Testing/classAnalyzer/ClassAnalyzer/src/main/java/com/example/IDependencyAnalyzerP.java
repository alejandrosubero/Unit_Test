package com.example;

import com.example.classrelationanalyzer.ClassNode;
import com.example.classrelationanalyzer.ClassRelationAnalyzer;
import com.example.dependency.DependencyAnalyzer;
import com.example.uml.DependencyAnalyzerParserUmlPlus;

import java.io.IOException;
import java.util.List;

public interface IDependencyAnalyzerP {


    default void generateUml(String outputDotPath, String outputPngPath, List<String> classSources) throws IOException {
        DependencyAnalyzerParserUmlPlus parser = new DependencyAnalyzerParserUmlPlus();

        parser.analyze(classSources);
        outputDotPath = outputDotPath + ".dot";
        outputPngPath = outputPngPath + ".png";
        parser.generateUMLClassDiagram(outputDotPath, outputPngPath);
    }


    default void generateDependencyAnalyzerDotPng(String outputDotPath, String outputPngPath, List<String> classSources) throws IOException {

        com.example.parser.DependencyAnalyzerParser analyzer = new com.example.parser.DependencyAnalyzerParser();

        analyzer.analyze(classSources);
        outputDotPath = outputDotPath + ".dot";
        outputPngPath = outputPngPath + ".png";
        // Generar archivos de salida
        analyzer.generateDotAndPng(outputDotPath, outputPngPath);
    }


//    default void generateDependencyAnalyzerConsole(List<String> classSources, String targetClassName) {
//        DependencyAnalyzer analyzer = new DependencyAnalyzer();
//        analyzer.analyzeDependencies(classSources, targetClassName);
//        analyzer.printDependencyTree();
//    }

    default String interfaceRelations(List<String> classSources, String targetClassName) {
        ClassRelationAnalyzer analyzer = new ClassRelationAnalyzer();
        analyzer.analyzeClasses(classSources);
        ClassNode tree = analyzer.buildTree(targetClassName);
        String treeAsString = analyzer.buildTreeString(tree);
        return treeAsString;
    }


}
