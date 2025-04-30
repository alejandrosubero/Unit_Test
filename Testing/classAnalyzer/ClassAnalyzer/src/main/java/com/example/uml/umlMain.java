package com.example.uml;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class umlMain {

    public static void main(String[] args) throws IOException {

        List<String> classSources = Arrays.asList(
                "package com.example;\n" +
                        "import com.example.service.MyService;\n" +
                        "public class ClassA {\n" +
                        "    private MyService myService;\n" +
                        "}",
                "package com.example;\n" +
                        "import com.example.service.MyService;\n" +
                        "public class hp {\n" +
                        "    private ClassA myServiceHP;\n" +
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
        DependencyAnalyzerParser parser = new DependencyAnalyzerParser();
        parser.analyze(classSources);
        parser.generateUMLClassDiagram("uml_class_diagram.dot", "uml_class_diagram.png");
    }
}
