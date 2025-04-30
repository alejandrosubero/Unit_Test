package com.example.parser;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class parseMain {

    public static void main(String[] args) throws IOException {
        List<String> classSources = Arrays.asList(
                "package com.example;\n" +
                        "import com.example.service.MyService;\n" +
                        "public class ClassA {\n" +
                        "    private MyService myService;\n" +
                        "}",

                "package com.example;\n" +
                        "public class ClassB extends MyService {\n" +
                        "}",

                "package com.example;\n" +
                        "public class ClassC {\n" +
                        "    public void doSomething() {\n" +
                        "        MyService service = new MyService();\n" +
                        "    }\n" +
                        "}",

                "package com.example.service;\n" +
                        "public class MyService {\n" +
                        "    public static void doWork() {}\n" +
                        "}"
        );

        // Crear analizador
        DependencyAnalyzerParser analyzer = new DependencyAnalyzerParser();

        // Analizar dependencias desde las clases
        analyzer.analyze(classSources);

        // Generar archivos de salida
        analyzer.generateDotAndPng("class-dependencies.dot", "class-dependencies.png");

        System.out.println("Generados: class-dependencies.dot y class-dependencies.png");
    }
    }
