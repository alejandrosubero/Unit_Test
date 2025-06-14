package com.unitTestGenerator.analyzers.dependency.locators;

import java.io.File;

public class GradleLocator {

    public static File findBuildGradle(String projectPath) {
        File dir = new File(projectPath);
        if (dir.exists() && dir.isDirectory()) {
            File gradleFile = new File(dir, "build.gradle");
            if (gradleFile.exists()) {
                return gradleFile;
            }
        }
        return null;
    }
}
