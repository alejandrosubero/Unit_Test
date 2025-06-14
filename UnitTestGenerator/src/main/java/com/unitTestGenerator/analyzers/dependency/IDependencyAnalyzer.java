package com.unitTestGenerator.analyzers.dependency;



import com.unitTestGenerator.analyzers.dependency.console.DependencyAnalyzer;
import com.unitTestGenerator.analyzers.dependency.interfaceanalyzer.ClassNode;
import com.unitTestGenerator.analyzers.dependency.interfaceanalyzer.ClassRelationAnalyzer;
import com.unitTestGenerator.analyzers.dependency.locators.GradleLocator;
import com.unitTestGenerator.analyzers.dependency.locators.PomLocator;
import com.unitTestGenerator.analyzers.dependency.parser.DependencyAnalyzerParser;
import com.unitTestGenerator.analyzers.dependency.parser.DependencyAnalyzerParserUml;
import com.unitTestGenerator.analyzers.dependency.parser.GradleParser;
import com.unitTestGenerator.analyzers.dependency.parser.PomParser;
import com.unitTestGenerator.analyzers.dependency.pojos.ProjectInfo;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.pojos.Project;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IDependencyAnalyzer {



    default void getProjectInfoFromMaven(Project project){
            String path = project.getPathProject();
            ProjectInfo info = PomParser.parsePom(PomLocator.findPomFile(path));
            project.setInfo(info);
        }


    default void getProjectInfoFromGradle(Project project) {

        String projectPath = project.getPathProject();
        File buildGradle = GradleLocator.findBuildGradle(projectPath);
        if (buildGradle != null) {
            ProjectInfo info = GradleParser.parseGradleFile(buildGradle);
            project.setInfo(info);
        } else {
            System.out.println("Don't find the build.gradle file in: " + projectPath);
        }
    }

// delete all relete to thes method .................
    default void generateUmlDiagram(String outputPath, List<String> classSources) throws IOException {
        DependencyAnalyzerParserUml parser = ContextIOC.getInstance().getClassInstance(DependencyAnalyzerParserUml.class);
        parser.analyze(classSources);
        String outputDotPath = outputPath + ".dot";
        String outputPngPath = outputPath + ".png";
        parser.generateUMLClassDiagram(outputDotPath, outputPngPath);
        System.out.println(outputDotPath);
        System.out.println(outputPngPath);
    }

    // delete all relete to thes method .................
    default void generateDependencyDotPng(String outputPath, List<String> classSources) throws IOException {
        DependencyAnalyzerParser analyzer = ContextIOC.getInstance().getClassInstance(DependencyAnalyzerParser.class);
        analyzer.analyze(classSources);
        String outputDotPath = outputPath + ".dot";
        String outputPngPath = outputPath + ".png";
        // Generar archivos de salida
        analyzer.generateDotAndPng(outputDotPath, outputPngPath);
        System.out.println(outputDotPath);
        System.out.println(outputPngPath);
    }

    // delete all relete to thes method .................
    default void generateDependencyConsole(List<String> classSources, String targetClassName) {
        DependencyAnalyzer analyzer = ContextIOC.getInstance().getClassInstance(DependencyAnalyzer.class);
        analyzer.analyzeDependencies(classSources, targetClassName);
        analyzer.printDependencyTree();
    }

    default String interfaceRelations(List<String> classSources, String targetClassName) {
        ClassRelationAnalyzer analyzer = ContextIOC.getInstance().getClassInstance(ClassRelationAnalyzer.class);
        analyzer.analyzeClasses(classSources);
        ClassNode tree = analyzer.buildTree(targetClassName);
        String treeAsString = analyzer.buildTreeString(tree);
        return treeAsString;
    }


}
