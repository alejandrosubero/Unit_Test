package com.example.gradle;


import java.util.ArrayList;
import java.util.List;

public class GradleProjectInfo {
    private String name;
    private String description;
    private String version;
    private String javaVersion;
    private List<DependencyInfo> dependencies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public List<DependencyInfo> getDependencies() {
        return dependencies;
    }

    public void addDependency(DependencyInfo dependency) {
        this.dependencies.add(dependency);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("name=").append(name).append("\n");
        buffer.append("description=").append(description).append("\n");
        buffer.append("version=").append(version).append("\n");
        buffer.append("javaVersion=").append(javaVersion).append("\n");
        buffer.append("dependencies=").append("\n");

        for (DependencyInfo dependecy: dependencies){
            buffer.append(dependecy.toString());
        }
        return buffer.toString();
    }
}
