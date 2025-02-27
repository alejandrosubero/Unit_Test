package com.unitTestGenerator.analyzers.services;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnalyzeCassMethodService {

    public static void analyzeMethod(String method) {

        // Get the static classes used in the method
        List<String> staticClasses = new ArrayList<>();
        Pattern staticPattern = Pattern.compile("\\b(\\w+)\\.\\w+\\b");
        Matcher staticMatcher = staticPattern.matcher(method);
        while (staticMatcher.find()) {
            staticClasses.add(staticMatcher.group(1));
        }

        // Get the classes that implement the Singleton pattern
        List<String> singletonClasses = new ArrayList<>();
        Pattern singletonPattern = Pattern.compile("\\b(\\w+)\\.getInstance\\(\\)\\b");
        Matcher singletonMatcher = singletonPattern.matcher(method);
        while (singletonMatcher.find()) {
            singletonClasses.add(singletonMatcher.group(1));
        }

        // Print the results
        System.out.println("Static classes used in the method:");
        for (String clazz : staticClasses) {
            System.out.println(clazz);
        }

        System.out.println("Classes that implement the Singleton pattern:");
        for (String clazz : singletonClasses) {
            System.out.println(clazz);
        }
    }

}
