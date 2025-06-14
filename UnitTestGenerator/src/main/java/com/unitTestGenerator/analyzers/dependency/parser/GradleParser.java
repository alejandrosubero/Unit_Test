package com.unitTestGenerator.analyzers.dependency.parser;




import com.unitTestGenerator.analyzers.dependency.pojos.DependencyInfo;
import com.unitTestGenerator.analyzers.dependency.pojos.ProjectInfo;
import com.unitTestGenerator.ioc.anotations.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GradleParser {

    public static ProjectInfo parseGradleFile(File gradleFile) {
        ProjectInfo info = new ProjectInfo();

        if (!gradleFile.exists()) {
            System.err.println("Archivo build.gradle no encontrado.");
            return info;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(gradleFile))) {
            String line;
            Pattern namePattern = Pattern.compile("rootProject\\.name\\s*=\\s*['\"](.+?)['\"]");
            Pattern descriptionPattern = Pattern.compile("description\\s*=\\s*['\"](.+?)['\"]");
            Pattern versionPattern = Pattern.compile("version\\s*=\\s*['\"](.+?)['\"]");
            Pattern javaVersionPatternOld = Pattern.compile("(sourceCompatibility|targetCompatibility)\\s*=\\s*['\"](.+?)['\"]");
            Pattern javaVersionPatternToolchain = Pattern.compile("languageVersion\\s*=\\s*JavaLanguageVersion\\.of\\((\\d{1,2})\\)");
            Pattern dependencyPattern = Pattern.compile(
                    "(implementation|api|compileOnly|annotationProcessor|testImplementation|runtimeOnly|testRuntimeOnly)\\s+(.*)"
            );

            boolean insideJavaBlock = false;
            boolean insideToolchainBlock = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("java {")) {
                    insideJavaBlock = true;
                } else if (line.equals("}")) {
                    if (insideToolchainBlock) {
                        insideToolchainBlock = false;
                    } else if (insideJavaBlock) {
                        insideJavaBlock = false;
                    }
                } else if (insideJavaBlock && line.startsWith("toolchain {")) {
                    insideToolchainBlock = true;
                }

                Matcher nameMatcher = namePattern.matcher(line);
                if (nameMatcher.find()) {
                    info.setName(nameMatcher.group(1));
                }

                Matcher descMatcher = descriptionPattern.matcher(line);
                if (descMatcher.find()) {
                    info.setDescription(descMatcher.group(1));
                }

                Matcher versionMatcher = versionPattern.matcher(line);
                if (versionMatcher.find()) {
                    info.setVersion(versionMatcher.group(1));
                }

                Matcher javaOldMatcher = javaVersionPatternOld.matcher(line);
                if (javaOldMatcher.find()) {
                    info.setJavaVersion(javaOldMatcher.group(2));
                }

                if (insideToolchainBlock) {
                    Matcher javaToolchainMatcher = javaVersionPatternToolchain.matcher(line);
                    if (javaToolchainMatcher.find()) {
                        info.setJavaVersion(javaToolchainMatcher.group(1));
                    }
                }

                Matcher depMatcher = dependencyPattern.matcher(line);
                if (depMatcher.find()) {
                    String rawDeps = depMatcher.group(2);
                    rawDeps = rawDeps.split("//")[0].trim();

                    String[] deps = rawDeps.split(",");
                    for (String dep : deps) {
                        dep = dep.trim();
                        if (dep.startsWith("'") && dep.endsWith("'")) {
                            dep = dep.substring(1, dep.length() - 1);
                        } else if (dep.startsWith("\"") && dep.endsWith("\"")) {
                            dep = dep.substring(1, dep.length() - 1);
                        }

                        if (!dep.isEmpty()) {
                            info.addDependency(new DependencyInfo(dep));
                        }
                    }
                }
            }

            if (info.getName() == null) {
                info.setName(gradleFile.getParentFile().getName());
            }

        } catch (IOException e) {
            System.err.println("Error leyendo build.gradle: " + e.getMessage());
        }

        return info;
    }
}
