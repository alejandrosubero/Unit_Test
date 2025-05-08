package com.example.uml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class umlMain {

    public static void main(String[] args) throws IOException {




        List<String> classSources = Arrays.asList( "package com.example;\n" +
                        "\n" +
                        "/**\n" +
                        " * Clase de prueba generada para analizar problemas con Graphviz.\n" +
                        " * Contenido especial:\n" +
                        " * - Comillas dobles: \"texto\"\n" +
                        " * - Backslashes: \\\\ \n" +
                        " * - Llaves: { ejemplo }\n" +
                        " * - Corchetes: [ ejemplo ]\n" +
                        " * - Diamantes: <T>, Map<String, List<Integer>>\n" +
                        " * - Pipes: |\n" +
                        " * - Saltos de línea y tabulaciones: \\n, \\t\n" +
                        " * - Combinaciones anidadas: new HashMap<String, List<String>>()\n" +
                        " */\n" +
                        "\n" +
                        "import java.util.*;\n" +
                        "import org.junit.jupiter.api.Test;\n" +
                        "import org.springframework.boot.test.context.SpringBootTest;\n" +
                        "import org.springframework.test.context.ActiveProfiles;\n" +
                        "import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;\n" +
                        "import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;\n" +
                        "import org.springframework.test.context.junit.jupiter.SpringExtension;\n" +
                        "import org.mockito.junit.jupiter.MockitoExtension;\n" +
                        "\n" +
                        "@SpringBootTest\n" +
                        "@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)\n" +
                        "@ExtendWith(MockitoExtension.class)\n" +
                        "@ExtendWith(SpringExtension.class)\n" +
                        "@ActiveProfiles(\"test\")\n" +
                        "@DataJpaTest\n" +
                        "public class TestFileContent {\n" +
                        "\n" +
                        "\tprivate String className = \"TestFile\";\n" +
                        "\tprivate String description = \"Special chars: \\\" \\\\ \\n \\t { } [ ] < > |\";\n" +
                        "\n" +
                        "\tprivate List<String> list = new ArrayList<>();\n" +
                        "\tprivate Map<String, List<Integer>> map = new HashMap<>();\n" +
                        "\tprivate String[] tags = {\"unit\", \"integration\"};\n" +
                        "\n" +
                        "\tpublic void buildTestFile() {\n" +
                        "\t\tStringBuilder contex = new StringBuilder();\n" +
                        "\t\tcontex.append(this.stringEnsamble(\"import \", \"com.example\", \".\", \"TestFileContent\"));\n" +
                        "\t\tcontex.append(\"\\n\").append(this.getMockImport());\n" +
                        "\t\tcontex.append(\"\\n\").append(\"@ExtendWith(MockitoExtension.class)\");\n" +
                        "\t\tcontex.append(\"\\n\").append(\"@ActiveProfiles(\\\"test\\\")\");\n" +
                        "\t\tSystem.out.println(map.get(\"key\").get(0));\n" +
                        "\t}\n" +
                        "\n" +
                        "\tprivate String stringEnsamble(String... parts) {\n" +
                        "\t\treturn String.join(\"\", parts);\n" +
                        "\t}\n" +
                        "\n" +
                        "\tprivate String getMockImport() {\n" +
                        "\t\treturn \"import org.mockito.*;\";\n" +
                        "\t}\n" +
                        "}",
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
                "package test; public interface A {}",
                // Interface C extends A
                "package test; public interface C extends A {}",
                // Interface D extends C
                "package test; public interface D extends C {}",
                // Class B implements A
                "package test; public class B implements A {}",
                // Class W implements D
                "package test; public class W implements D {" +
                        "    public void doSomething() {\n" +
                        "        MyService service = new MyService();\n" +
                        "    }\n" + "}",
                "package test; \n public class S implements D { \n" +
                        "TestFileContent testClass = new TestFileContent();" +
                        "}",
                "package test; \n import com.example.service.MyService; \n public class S implements C {}",
                "package test; public interface H {}",
                "package test; public class P implements H {}"
        );



        DependencyAnalyzerParserUmlPlus parser = new DependencyAnalyzerParserUmlPlus();
//        DependencyAnalyzerParserUml parser = new DependencyAnalyzerParserUml();
        parser.analyze(classSources);
        parser.generateUMLClassDiagram("uml_class_diagram.dot", "uml_class_diagram.png");
    }
}
//String problematicClass =
//        "package com.example;\n" +
//                "\n" +
//                "/**\n" +
//                " * Clase de prueba generada para analizar problemas con Graphviz.\n" +
//                " * Contenido especial:\n" +
//                " * - Comillas dobles: \"texto\"\n" +
//                " * - Backslashes: \\\\ \n" +
//                " * - Llaves: { ejemplo }\n" +
//                " * - Corchetes: [ ejemplo ]\n" +
//                " * - Diamantes: <T>, Map<String, List<Integer>>\n" +
//                " * - Pipes: |\n" +
//                " * - Saltos de línea y tabulaciones: \\n, \\t\n" +
//                " * - Combinaciones anidadas: new HashMap<String, List<String>>()\n" +
//                " */\n" +
//                "\n" +
//                "import java.util.*;\n" +
//                "import org.junit.jupiter.api.Test;\n" +
//                "import org.springframework.boot.test.context.SpringBootTest;\n" +
//                "import org.springframework.test.context.ActiveProfiles;\n" +
//                "import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;\n" +
//                "import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;\n" +
//                "import org.springframework.test.context.junit.jupiter.SpringExtension;\n" +
//                "import org.mockito.junit.jupiter.MockitoExtension;\n" +
//                "\n" +
//                "@SpringBootTest\n" +
//                "@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)\n" +
//                "@ExtendWith(MockitoExtension.class)\n" +
//                "@ExtendWith(SpringExtension.class)\n" +
//                "@ActiveProfiles(\"test\")\n" +
//                "@DataJpaTest\n" +
//                "public class TestFileContent {\n" +
//                "\n" +
//                "\tprivate String className = \"TestFile\";\n" +
//                "\tprivate String description = \"Special chars: \\\" \\\\ \\n \\t { } [ ] < > |\";\n" +
//                "\n" +
//                "\tprivate List<String> list = new ArrayList<>();\n" +
//                "\tprivate Map<String, List<Integer>> map = new HashMap<>();\n" +
//                "\tprivate String[] tags = {\"unit\", \"integration\"};\n" +
//                "\n" +
//                "\tpublic void buildTestFile() {\n" +
//                "\t\tStringBuilder contex = new StringBuilder();\n" +
//                "\t\tcontex.append(this.stringEnsamble(\"import \", \"com.example\", \".\", \"TestFileContent\"));\n" +
//                "\t\tcontex.append(\"\\n\").append(this.getMockImport());\n" +
//                "\t\tcontex.append(\"\\n\").append(\"@ExtendWith(MockitoExtension.class)\");\n" +
//                "\t\tcontex.append(\"\\n\").append(\"@ActiveProfiles(\\\"test\\\")\");\n" +
//                "\t\tSystem.out.println(map.get(\"key\").get(0));\n" +
//                "\t}\n" +
//                "\n" +
//                "\tprivate String stringEnsamble(String... parts) {\n" +
//                "\t\treturn String.join(\"\", parts);\n" +
//                "\t}\n" +
//                "\n" +
//                "\tprivate String getMockImport() {\n" +
//                "\t\treturn \"import org.mockito.*;\";\n" +
//                "\t}\n" +
//                "}";
