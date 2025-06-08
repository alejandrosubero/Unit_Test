package com.unitTestGenerator.analyzers.dependency.reverse;


import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Singleton
public class ReverseDependencyScanner {

    private final Map<String, ReverseDependencyNode> nodeMap = new HashMap<>();
    private final Map<String, Set<String>> usageMap = new HashMap<>();
    private final Map<String, String> fileToClassMap = new HashMap<>();

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("^\\s*package\\s+([\\w\\.]+);");
    private static final Pattern CLASS_PATTERN = Pattern.compile("\\b(class|interface|enum)\\s+(\\w+)");
    private static final Pattern IMPORT_PATTERN = Pattern.compile("^\\s*import\\s+([\\w\\.]+);");

    public Map<String, ReverseDependencyNode> getNodeMap() {
        return nodeMap;
    }

    public void scanProject(Path root) throws IOException {
        List<Path> javaFiles;

        try (Stream<Path> walk = Files.walk(root)) {
            javaFiles = walk.filter(p -> p.toString().endsWith(".java"))
                    .collect(Collectors.toList());
        }

        for (Path javaFile : javaFiles) {
            String className = getFullyQualifiedClassName(javaFile);
            if (className != null) {
                fileToClassMap.put(javaFile.toString(), className);
                String pack = extractPackageFromLine(javaFile);
                nodeMap.putIfAbsent(className, new ReverseDependencyNode(getSimpleName(className), pack));
//                nodeMap.putIfAbsent(className, ContextIOC.getInstance().getClassInstance(ReverseDependencyNode.class).setReverseDependencyNode(getSimpleName(className), pack));
                usageMap.putIfAbsent(className, new HashSet<>());
            }
        }

        for (Path javaFile : javaFiles) {
            String sourceClass = fileToClassMap.get(javaFile.toString());
            if (sourceClass == null) continue;

            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(javaFile)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            Set<String> usedClasses = extractUsedClasses(lines, sourceClass);

            for (String usedClass : usedClasses) {
                Set<String> users = usageMap.get(usedClass);
                if (users != null) {
                    users.add(sourceClass);
                }
            }
        }
    }

    private String extractPackageFromLine(Path javaFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(javaFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = PACKAGE_PATTERN.matcher(line);
                if (matcher.find()) return matcher.group(1);
            }
        }
        return "";
    }

    private Set<String> extractUsedClasses(List<String> lines, String currentClass) {
        Set<String> used = new HashSet<>();
        Map<String, String> simpleToFQ = nodeMap.entrySet().stream()
                .filter(e -> !e.getKey().equals(currentClass))
                .collect(Collectors.toMap(e -> getSimpleName(e.getKey()), Map.Entry::getKey));

        for (String line : lines) {
            Matcher importMatcher = IMPORT_PATTERN.matcher(line);
            if (importMatcher.find()) {
                used.add(importMatcher.group(1));
            }

            for (Map.Entry<String, String> entry : simpleToFQ.entrySet()) {
                if (line.contains(entry.getKey())) {
                    used.add(entry.getValue());
                }
            }
        }
        return used;
    }


    private String getFullyQualifiedClassName(Path javaFile) {
        String packageName = "";
        String className = null;
        try (BufferedReader reader = Files.newBufferedReader(javaFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher pkgMatcher = PACKAGE_PATTERN.matcher(line);
                if (pkgMatcher.find()) {
                    packageName = pkgMatcher.group(1);
                }
                Matcher clsMatcher = CLASS_PATTERN.matcher(line);
                if (clsMatcher.find()) {
                    className = clsMatcher.group(2);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (className != null) ? (packageName.isEmpty() ? className : packageName + "." + className) : null;
    }

    private String getSimpleName(String fqName) {
        int idx = fqName.lastIndexOf('.');
        return (idx != -1) ? fqName.substring(idx + 1) : fqName;
    }

    public Map<String, List<String>> getReverseDependencyMap() {
        Map<String, List<String>> result = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : usageMap.entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return result;
    }

    public List<ReverseDependencyNode> buildDependencyTree() {
        for (Map.Entry<String, Set<String>> entry : usageMap.entrySet()) {
            String targetClass = entry.getKey();
            ReverseDependencyNode node = nodeMap.get(targetClass);
            for (String userClass : entry.getValue()) {
                ReverseDependencyNode userNode = nodeMap.get(userClass);
                if (userNode != null) {
                    node.addUsedBy(userNode);
                }
            }
        }
        return new ArrayList<>(nodeMap.values());
    }

    public List<ReverseDependencyNode> analizer(String router) throws IOException {
        if (router == null || router.isEmpty()) return Collections.emptyList();
        scanProject(Paths.get(router));
        return buildDependencyTree();
    }


}
