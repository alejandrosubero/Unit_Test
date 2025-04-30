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
                        "}"
        );

        DependencyAnalyzer analyzer = new DependencyAnalyzer();
        analyzer.analyzeDependencies(classSources, "com.example.service.MyService");
        analyzer.printDependencyTree();
    }
}
