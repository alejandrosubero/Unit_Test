package com.example.dependency;

import java.util.Arrays;
import java.util.List;

public class MainDependency {

    public static void main(String[] args) {
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
                "package test; public class S implements D {}",
                "package test; \"import com.example.service.MyService;\\n\" +public class S implements C {}",
                "package test; public interface H {}",
                "package test; public class P implements H {}"
        );




        DependencyAnalyzer analyzer = new DependencyAnalyzer();
        analyzer.analyzeDependencies(classSources, "com.example.service.MyService");
        analyzer.printDependencyTree();
    }
}
