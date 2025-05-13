package com.example.uml;


import com.example.utilis.DataTIme;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.*;

    public class DependencyAnalyzerParserUmlPlus  implements DataTIme {
        private final Map<String, Set<String>> dependencyMap = new HashMap<>();
        private final Map<String, Set<String>> reverseDependencyMap = new HashMap<>();
        private final Map<String, List<String>> fieldsMap = new HashMap<>();
        private final Map<String, List<String>> methodsMap = new HashMap<>();
        private final Set<String> allClasses = new HashSet<>();

        public void analyze(List<String> classSources) {


            JavaParser parser = new JavaParser();
            parser.getParserConfiguration()
                    .setSymbolResolver(new JavaSymbolSolver(new CombinedTypeSolver(
                            new ReflectionTypeSolver(),
                            new JavaParserTypeSolver(new File("src/main/java")) // Ruta de tus clases
                    )));

            for (String source : classSources) {
                parser.parse(source).ifSuccessful(cu -> {
                    cu.findFirst(ClassOrInterfaceDeclaration.class).ifPresent(classDecl -> {
                      
                        String className = classDecl.getFullyQualifiedName().orElse(classDecl.getNameAsString());
// Forzar FQN incluso si no está declarado explícitamente
                        if (!className.contains(".") && classDecl.toTypeDeclaration().isPresent()) {
                            String packageName = classDecl.toTypeDeclaration().get().getNameAsString();
                            className = packageName + "." + className;
                        }

//                        String className = classDecl.getFullyQualifiedName().orElse(classDecl.getNameAsString());
//// Si la clase está en un paquete, añadirlo al nombre
//                        if (classDecl.getPackageDeclaration().isPresent()) {
//                            String packageName = classDecl.getPackageDeclaration().get().getNameAsString();
//                            className = packageName + "." + className;
//                        }



                        allClasses.add(className);
                        dependencyMap.putIfAbsent(className, new HashSet<>());
                        fieldsMap.putIfAbsent(className, new ArrayList<>());
                        methodsMap.putIfAbsent(className, new ArrayList<>());

                         final String classNamea = className;

                        // Herencia e interfaces
                        classDecl.getExtendedTypes().forEach(t ->
                                addDependency(classNamea, getBaseType(t.getNameAsString())));
                        classDecl.getImplementedTypes().forEach(t ->
                                addDependency(classNamea, getBaseType(t.getNameAsString())));

                        // Campos
                        for (FieldDeclaration field : classDecl.getFields()) {
                            String rawType = field.getElementType().asString();
                            String baseType = getBaseType(rawType);
                            for (VariableDeclarator var : field.getVariables()) {
                                String name = var.getNameAsString();
                                fieldsMap.get(className).add(
                                        escapeHtml(baseType + " " + name)
                                );
                                addDependency(className, baseType);
                            }
                        }

                        // Métodos
                        for (MethodDeclaration method : classDecl.getMethods()) {
                            // Tipo de retorno
                            String returnType = getBaseType(method.getType().asString());
                            addDependency(className, returnType);

                            // Parámetros
                            method.getParameters().forEach(p -> {
                                String paramType = getBaseType(p.getType().asString());
                                addDependency(classNamea, paramType);
                            });

                            String sig = escapeHtml(
                                    returnType + " " +
                                            method.getNameAsString() +
                                            "(" +
                                            method.getParameters().stream()
                                                    .map(p -> getBaseType(p.getType().asString()) + " " + p.getName())
                                                    .collect(Collectors.joining(", ")) +
                                            ")"
                            );
                            methodsMap.get(className).add(sig);


                            // Dependencias en cuerpo del método
                            method.findAll(ObjectCreationExpr.class)
                                    .forEach(e -> addDependency(classNamea, getBaseType(e.getType().getNameAsString())));

//                            method.findAll(MethodCallExpr.class).forEach(expr ->
//                                    expr.getScope().ifPresent(s ->
//                                            addDependency(classNamea, getBaseType(s.toString())))
//                            );

                            method.findAll(MethodCallExpr.class).forEach(expr -> {
                                expr.getScope().ifPresent(scope -> {
                                    // Extraer el nombre del scope directamente como String
                                    String scopeName = scope.toString();
                                    // Si el scope es un nombre simple (ej: "myService"), buscar su tipo declarado en los campos
                                    String fqn = findDeclaredTypeOfField(classNamea, scopeName);
                                    if (fqn != null) {
                                        addDependency(classNamea, fqn);
                                    }
                                });
                            });


                        }
                    });
                });
            }
        }

//        private String getBaseType(String type) {
//            // Mejorado: mantener paquetes en tipos base
//            return type.replaceAll("<.*>", "");
//        }

        private String findDeclaredTypeOfField(String currentClass, String fieldName) {
            // Buscar en los campos de la clase actual
            for (String field : fieldsMap.getOrDefault(currentClass, Collections.emptyList())) {
                if (field.contains(fieldName)) {
                    // Ejemplo: field = "com.example.ClassA myService"
                    return field.split(" ")[0]; // Retorna "com.example.ClassA"
                }
            }
            return null; // No se encontró el campo
        }

        private String getBaseType(String type) {
            // Eliminar genéricos pero conservar el paquete completo
            return type.replaceAll("<.*>", "").trim();
        }


        private boolean isExternalDependency(String className) {
            // Mejorado: filtrar solo librerías estándar
            return className.startsWith("java.") ||
                    className.startsWith("javax.") ||
                    className.startsWith("org.junit");
        }


        private String escapeHtml(String input) {
            return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("\\", "\\\\");
        }

        private void addDependency(String from, String to) {
            if (from.equals(to) || isExternalDependency(to)) return;
            System.out.println("[DEBUG] Dependencia: " + from + " -> " + to);
            dependencyMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);
            reverseDependencyMap.computeIfAbsent(to, k -> new HashSet<>()).add(from);
        }


        public void generateUMLClassDiagram(String outputDotPath, String outputPngPath) throws IOException {
            Graph g = graph("UML_Class_diagram").directed()
                    .graphAttr().with(
                            attr("label", "UML Class Diagram\\nGenerated at: " + this.getTime()),
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



            // 1. Crear nodos para TODAS las clases detectadas
            for (String className : allClasses) {
                List<String> fields = fieldsMap.getOrDefault(className, Collections.emptyList());
                List<String> methods = methodsMap.getOrDefault(className, Collections.emptyList());

                String label = String.format("{%s|%s|%s}",
                        escapeHtml(className),
                        fields.stream().collect(Collectors.joining("\\l")),
                        methods.stream().collect(Collectors.joining("\\l")));

                Node node = node(className).with(attr("label", label));
                nodes.put(className, node);
                g = g.with(node);
            }



// 2. Crear relaciones incluyendo herencia e implementaciones
            for (Map.Entry<String, Set<String>> entry : dependencyMap.entrySet()) {
                String from = entry.getKey();
                for (String to : entry.getValue()) {
                    if (nodes.containsKey(from) && nodes.containsKey(to)) {
                        g = g.with(nodes.get(from).link(
                                to(nodes.get(to)).with(attr("arrowhead", "open"))
                        ));
                    }
                }
            }




            // Generar archivos
            try (FileWriter writer = new FileWriter(outputDotPath)) {
                writer.write(g.toString());
            }
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(outputPngPath));
        }
    }
