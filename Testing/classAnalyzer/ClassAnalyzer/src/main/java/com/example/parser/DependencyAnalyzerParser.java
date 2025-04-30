//package com.example.parser;
//
//
//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ast.CompilationUnit;
//import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
//import com.github.javaparser.ast.body.FieldDeclaration;
//import com.github.javaparser.ast.body.MethodDeclaration;
//import com.github.javaparser.ast.expr.*;
//import com.github.javaparser.utils.SourceRoot;
//import guru.nidi.graphviz.attribute.Label;
//import guru.nidi.graphviz.engine.Format;
//import guru.nidi.graphviz.engine.Graphviz;
//import guru.nidi.graphviz.model.Factory;
//import guru.nidi.graphviz.model.Graph;
//import guru.nidi.graphviz.model.Node;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static guru.nidi.graphviz.model.Factory.*;
//
//public class DependencyAnalyzerParser {
//    private final Map<String, Set<String>> dependencyMap = new HashMap<>();
//    private final Map<String, Set<String>> reverseDependencyMap = new HashMap<>();
//
//    public void analyze(List<String> classSources) {
//        JavaParser parser = new JavaParser();
//
//        for (String source : classSources) {
//            parser.parse(source).ifSuccessful(cu -> {
//                Optional<ClassOrInterfaceDeclaration> classDeclOpt = cu.findFirst(ClassOrInterfaceDeclaration.class);
//                classDeclOpt.ifPresent(classDecl -> {
//                    String className = classDecl.getNameAsString();
//                    dependencyMap.putIfAbsent(className, new HashSet<>());
//
//                    classDecl.getExtendedTypes().forEach(t -> addDependency(className, t.getNameAsString()));
//                    classDecl.getImplementedTypes().forEach(t -> addDependency(className, t.getNameAsString()));
//
//                    for (FieldDeclaration field : classDecl.getFields()) {
//                        String type = field.getElementType().asString();
//                        addDependency(className, type);
//                    }
//
//                    classDecl.findAll(ObjectCreationExpr.class).forEach(expr -> {
//                        addDependency(className, expr.getType().getNameAsString());
//                    });
//
//                    classDecl.findAll(MethodCallExpr.class).forEach(expr -> {
//                        expr.getScope().ifPresent(scope -> {
//                            String used = scope.toString();
//                            addDependency(className, used);
//                        });
//                    });
//                });
//            });
//        }
//    }
//
//    private void addDependency(String from, String to) {
//        if (!from.equals(to)) {
//            dependencyMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);
//            reverseDependencyMap.computeIfAbsent(to, k -> new HashSet<>()).add(from);
//        }
//    }
//
//    public void generateDotAndPng(String outputDotPath, String outputPngPath) throws IOException {
//        Graph g = graph("dependencies").directed();
//        Map<String, Node> nodes = new HashMap<>();
//
//        for (String className : dependencyMap.keySet()) {
//            Node node = node(className).with(Label.html("<b>" + className + "</b>"));
//            nodes.put(className, node);
//        }
//
//        Graph finalGraph = g;
//        for (Map.Entry<String, Set<String>> entry : dependencyMap.entrySet()) {
//            String from = entry.getKey();
//            for (String to : entry.getValue()) {
//                Node fromNode = nodes.get(from);
//                Node toNode = nodes.computeIfAbsent(to, n -> node(n).with(Label.html("<b>" + n + "</b>")));
//                finalGraph = finalGraph.with(fromNode.link(toNode));
//            }
//        }
//
//        for (Map.Entry<String, Set<String>> entry : reverseDependencyMap.entrySet()) {
//            String to = entry.getKey();
//            for (String from : entry.getValue()) {
//                Node toNode = nodes.get(to);
//                Node fromNode = nodes.get(from);
//                if (toNode != null && fromNode != null) {
//                    finalGraph = finalGraph.with(toNode.link(fromNode));
//                }
//            }
//        }
//
//        File dotFile = new File(outputDotPath);
//        try (FileWriter writer = new FileWriter(dotFile)) {
//            writer.write(finalGraph.toString());
//        }
//
//        Graphviz.fromGraph(finalGraph).render(Format.PNG).toFile(new File(outputPngPath));
//    }
//}

package com.example.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class DependencyAnalyzerParser {
    private final Map<String, Set<String>> dependencyMap = new HashMap<>();

    public void analyze(List<String> classSources) {
        JavaParser parser = new JavaParser();

        for (String source : classSources) {
            parser.parse(source).ifSuccessful(cu -> {
                Optional<ClassOrInterfaceDeclaration> classDeclOpt = cu.findFirst(ClassOrInterfaceDeclaration.class);
                classDeclOpt.ifPresent(classDecl -> {
                    String className = classDecl.getNameAsString();
                    dependencyMap.putIfAbsent(className, new HashSet<>());

                    classDecl.getExtendedTypes().forEach(t -> addDependency(className, t.getNameAsString()));
                    classDecl.getImplementedTypes().forEach(t -> addDependency(className, t.getNameAsString()));

                    for (FieldDeclaration field : classDecl.getFields()) {
                        String type = field.getElementType().asString();
                        addDependency(className, type);
                    }

                    classDecl.findAll(ObjectCreationExpr.class).forEach(expr ->
                            addDependency(className, expr.getType().getNameAsString())
                    );

                    classDecl.findAll(MethodCallExpr.class).forEach(expr ->
                            expr.getScope().ifPresent(scope ->
                                    addDependency(className, scope.toString())
                            )
                    );
                });
            });
        }
    }

    private void addDependency(String from, String to) {
        if (!from.equals(to)) {
            dependencyMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);
        }
    }

    public void generateDotAndPng(String outputDotPath, String outputPngPath) throws IOException {
        Graph g = graph("dependencies").directed();
        Map<String, Node> nodes = new HashMap<>();

        for (String className : dependencyMap.keySet()) {
            Node node = node(className).with(Label.html("<b>" + className + "</b>"));
            nodes.put(className, node);
        }

        Graph finalGraph = g;
        for (Map.Entry<String, Set<String>> entry : dependencyMap.entrySet()) {
            String from = entry.getKey();
            for (String to : entry.getValue()) {
                Node fromNode = nodes.get(from);
                Node toNode = nodes.computeIfAbsent(to, n -> node(n).with(Label.html("<b>" + n + "</b>")));
                finalGraph = finalGraph.with(fromNode.link(toNode));
            }
        }

        try (FileWriter writer = new FileWriter(new File(outputDotPath))) {
            writer.write(finalGraph.toString());
        }

        Graphviz.fromGraph(finalGraph).render(Format.PNG).toFile(new File(outputPngPath));
    }
}


//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
//import com.github.javaparser.ast.body.FieldDeclaration;
//import com.github.javaparser.ast.expr.*;
//import com.unitTestGenerator.ioc.anotations.Component;
//import guru.nidi.graphviz.attribute.Label;
//import guru.nidi.graphviz.engine.Format;
//import guru.nidi.graphviz.engine.Graphviz;
//import guru.nidi.graphviz.model.Graph;
//import guru.nidi.graphviz.model.Node;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
//import static guru.nidi.graphviz.model.Factory.*;
//
//@Component
//public class DependencyAnalyzerParser {
//    private final Map<String, Set<String>> dependencyMap = new HashMap<>();
//    private final Map<String, Set<String>> reverseDependencyMap = new HashMap<>();
//
//    public void analyze(List<String> classSources) {
//        JavaParser parser = new JavaParser();
//
//        for (String source : classSources) {
//            parser.parse(source).ifSuccessful(cu -> {
//                Optional<ClassOrInterfaceDeclaration> classDeclOpt = cu.findFirst(ClassOrInterfaceDeclaration.class);
//                classDeclOpt.ifPresent(classDecl -> {
//                    String className = classDecl.getNameAsString();
//                    dependencyMap.putIfAbsent(className, new HashSet<>());
//
//                    classDecl.getExtendedTypes().forEach(t -> addDependency(className, t.getNameAsString()));
//                    classDecl.getImplementedTypes().forEach(t -> addDependency(className, t.getNameAsString()));
//
//                    for (FieldDeclaration field : classDecl.getFields()) {
//                        String type = field.getElementType().asString();
//                        addDependency(className, type);
//                    }
//
//                    classDecl.findAll(ObjectCreationExpr.class).forEach(expr -> {
//                        addDependency(className, expr.getType().getNameAsString());
//                    });
//
//                    classDecl.findAll(MethodCallExpr.class).forEach(expr -> {
//                        expr.getScope().ifPresent(scope -> {
//                            String used = scope.toString();
//                            addDependency(className, used);
//                        });
//                    });
//                });
//            });
//        }
//    }
//
//    private void addDependency(String from, String to) {
//        if (!from.equals(to)) {
//            dependencyMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);
//            reverseDependencyMap.computeIfAbsent(to, k -> new HashSet<>()).add(from);
//        }
//    }
//
//    public void generateDotAndPng(String outputDotPath, String outputPngPath) throws IOException {
//        Graph g = graph("dependencies").directed();
//        Map<String, Node> nodes = new HashMap<>();
//
//        for (String className : dependencyMap.keySet()) {
//            Node node = node(className).with(Label.html("<b>" + className + "</b>"));
//            nodes.put(className, node);
//        }
//
//        Graph finalGraph = g;
//        for (Map.Entry<String, Set<String>> entry : dependencyMap.entrySet()) {
//            String from = entry.getKey();
//            for (String to : entry.getValue()) {
//                Node fromNode = nodes.get(from);
//                Node toNode = nodes.computeIfAbsent(to, n -> node(n).with(Label.html("<b>" + n + "</b>")));
//                finalGraph = finalGraph.with(fromNode.link(toNode));
//            }
//        }
//
//        for (Map.Entry<String, Set<String>> entry : reverseDependencyMap.entrySet()) {
//            String to = entry.getKey();
//            for (String from : entry.getValue()) {
//                Node toNode = nodes.get(to);
//                Node fromNode = nodes.get(from);
//                if (toNode != null && fromNode != null) {
//                    finalGraph = finalGraph.with(toNode.link(fromNode));
//                }
//            }
//        }
//
//        File dotFile = new File(outputDotPath);
//        try (FileWriter writer = new FileWriter(dotFile)) {
//            writer.write(finalGraph.toString());
//        }
//
//        Graphviz.fromGraph(finalGraph).render(Format.PNG).toFile(new File(outputPngPath));
//    }
//}
