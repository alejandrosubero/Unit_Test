package com.example.gradle;


import com.example.maven.ProjectInfo;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        String projectPath = "D:\\TEST REPOSITORIES\\demo";

        File buildGradle = GradleLocator.findBuildGradle(projectPath);

        if (buildGradle != null) {
            ProjectInfo info = GradleParser.parseGradleFile(buildGradle);
            System.out.println(info.toString());
        } else {
            System.out.println("No se encontró build.gradle en: " + projectPath);
        }
    }
}
