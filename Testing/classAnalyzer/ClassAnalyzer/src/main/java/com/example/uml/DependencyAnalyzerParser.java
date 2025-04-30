package com.example.uml;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.*;



public class DependencyAnalyzerParser {
    private final Map<String, Set<String>> dependencyMap = new HashMap<>();
    private final Map<String, Set<String>> reverseDependencyMap = new HashMap<>();
    private final Map<String, List<String>> fieldsMap = new HashMap<>();
    private final Map<String, List<String>> methodsMap = new HashMap<>();

    public void analyze(List<String> classSources) {
        JavaParser parser = new JavaParser();
        for (String source : classSources) {
            parser.parse(source).ifSuccessful(cu -> {
                cu.findFirst(ClassOrInterfaceDeclaration.class).ifPresent(classDecl -> {
                    String className = classDecl.getNameAsString();
                    dependencyMap.putIfAbsent(className, new HashSet<>());
                    fieldsMap.putIfAbsent(className, new ArrayList<>());
                    methodsMap.putIfAbsent(className, new ArrayList<>());

                    // 1) EXTENDS / IMPLEMENTS siguen igual
                    classDecl.getExtendedTypes().forEach(t -> addDependency(className, t.getNameAsString()));
                    classDecl.getImplementedTypes().forEach(t -> addDependency(className, t.getNameAsString()));

                    // 2) CAMPOS: guardamos nombre y tipo
                    for (FieldDeclaration field : classDecl.getFields()) {
                        String type = field.getElementType().asString();
                        for (VariableDeclarator var : field.getVariables()) {
                            String name = var.getNameAsString();
                            fieldsMap.get(className).add(type + " " + name);
                            addDependency(className, type);
                        }
                    }

                    // 3) MÉTODOS: guardamos firma (tipo retorno + nombre + params)
                    for (MethodDeclaration method : classDecl.getMethods()) {
                        String sig = method.getType().asString() + " "
                                + method.getNameAsString()
                                + "("
                                + method.getParameters().stream()
                                .map(p -> p.getType() + " " + p.getName())
                                .collect(Collectors.joining(", "))
                                + ")";
                        methodsMap.get(className).add(sig);

                        // detectar invocaciones a new / static calls
                        method.findAll(ObjectCreationExpr.class)
                                .forEach(e -> addDependency(className, e.getType().getNameAsString()));
                        method.findAll(MethodCallExpr.class).forEach(expr ->
                                expr.getScope().ifPresent(s -> addDependency(className, s.toString()))
                        );
                    }
                });
            });
        }
    }

    private void addDependency(String from, String to) {
        if (!from.equals(to)) {
            dependencyMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);
            reverseDependencyMap.computeIfAbsent(to, k -> new HashSet<>()).add(from);
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

        for (Map.Entry<String, Set<String>> entry : reverseDependencyMap.entrySet()) {
            String to = entry.getKey();
            for (String from : entry.getValue()) {
                Node toNode = nodes.get(to);
                Node fromNode = nodes.get(from);
                if (toNode != null && fromNode != null) {
                    finalGraph = finalGraph.with(toNode.link(fromNode));
                }
            }
        }

        File dotFile = new File(outputDotPath);
        try (FileWriter writer = new FileWriter(dotFile)) {
            writer.write(finalGraph.toString());
        }

        Graphviz.fromGraph(finalGraph).render(Format.PNG).toFile(new File(outputPngPath));
    }

    public void generateUMLClassDiagram(String outputDotPath, String outputPngPath) throws IOException {
        // Grafo y atributos (igual que antes) …
        Graph g = graph("UML_Class_diagram").directed()
                .graphAttr().with(
                        attr("label", "UML Class diagram"),
                        attr("labelloc", "t"),
                        attr("fontname", "Helvetica,Arial,sans-serif")
                )
                .nodeAttr().with(
                        attr("fontname", "Helvetica,Arial,sans-serif"),
                        attr("shape", "record"),
                        attr("style", "filled"),
                        attr("fillcolor", "gray95")
                );

        Map<String, Node> nodes = new HashMap<>();

        for (String className : dependencyMap.keySet()) {
            // Construimos lista de líneas para fields y métodos
            List<String> fields = fieldsMap.getOrDefault(className, Collections.emptyList());
            List<String> methods = methodsMap.getOrDefault(className, Collections.emptyList());

            String fieldBlock = fields.isEmpty()
                    ? ""
                    : fields.stream().collect(Collectors.joining("\\l")) + "\\l";   // \l = newline left-justified
            String methodBlock = methods.isEmpty()
                    ? ""
                    : methods.stream().collect(Collectors.joining("\\l")) + "\\l";

            // Sintaxis record: {ClassName|fields|methods}
            String label = "{" + className
                    + "|" + fieldBlock
                    + "|" + methodBlock
                    + "}";

            Node n = node(className).with(attr("label", label));
            nodes.put(className, n);
            g = g.with(n);
        }

        // Relaciones igual que antes…
        for (Map.Entry<String, Set<String>> entry : dependencyMap.entrySet()) {
            String from = entry.getKey();
            for (String to : entry.getValue()) {
                Node fromNode = nodes.get(from);
                Node toNode = nodes.computeIfAbsent(to, k -> {
                    String lbl = "{" + k + "}";
                    return node(k).with(attr("label", lbl));
                });
                g = g.with(fromNode.link(toNode));
            }
        }

        // Exportar .dot y .png (igual que antes)
        try (FileWriter w = new FileWriter(outputDotPath)) {
            w.write(g.toString());
        }
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(outputPngPath));
    }

}
