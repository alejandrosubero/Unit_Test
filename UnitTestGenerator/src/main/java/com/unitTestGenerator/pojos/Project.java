package com.unitTestGenerator.pojos;

import java.io.File;
import java.util.*;

public class Project {

    private List<Clase> claseList = new ArrayList<>();
    private Map<String, Clase> mapClass = new HashMap<>();;
    private Boolean isMaven;
    private Boolean isGradle;
    private String pathProject;
    private String projectDirectoryTree;
    private String projectClassTree;

    public Project() {
    }

    public Project(List<Clase> claseList) {
        this.claseList = claseList;
    }

    public Project(String pathProject) {
        this.pathProject = pathProject;
        this.projectAnalyzerType(pathProject);
    }

    public Project(Map<String, Clase> mapClass, String pathProject) {
        this.mapClass = mapClass;
        this.pathProject = pathProject;
        this.projectAnalyzerType(pathProject);
    }

    public Project(List<Clase> claseList, String pathProject) {
        this.claseList = claseList;
        this.pathProject = pathProject;
        this.projectAnalyzerType(pathProject);
    }

    public Project(List<Clase> claseList, Boolean isMaven, Boolean isGradle) {
        this.claseList = claseList;
        this.isMaven = isMaven;
        this.isGradle = isGradle;
    }

    public String getProjectDirectoryTree() {
        return projectDirectoryTree;
    }

    public void setProjectDirectoryTree(String projectDirectoryTree) {
        this.projectDirectoryTree = projectDirectoryTree;
    }

    public List<Clase> getClaseList() {
        return claseList;
    }

    public void setClaseList(List<Clase> claseList) {
        this.claseList = claseList;
    }

    public Clase getClass(String className){
        return getClass(className, this);
    }

    private Clase getClass(String className, Project project){
        Clase foundClass = null;
//        if(project.getClaseList() != null && !project.getClaseList().isEmpty() && className !=null && !className.equals("")){
//            for(Clase clasS : project.getClaseList()){
//                if(clasS.getNombre() !=null && clasS.getNombre().toLowerCase().equals(className.toLowerCase())){
//                    foundClass = clasS;
//                }
//            }
//        }
        if(project.getMapClass() != null && !project.getMapClass().isEmpty() && className !=null && !className.equals("")){
            if(project.getMapClass().containsKey(className)){
                foundClass = project.getMapClass().get(className);
            }
        }
        return foundClass;
    }

    public String getProjectClassTree() {
        return projectClassTree;
    }

    public void setProjectClassTree(String projectClassTree) {
        this.projectClassTree = projectClassTree;
    }

    public Map<String, Clase> getMapClass() {
        return mapClass;
    }

    public void setMapClass(Map<String, Clase> mapClass) {
        this.mapClass = mapClass;
    }

    public String getPathProject() {
        return pathProject;
    }

    public void setPathProject(String pathProject) {
        this.pathProject = pathProject;
    }

    public Boolean getMaven() {
        return isMaven;
    }

    public void setMaven(Boolean maven) {
        isMaven = maven;
    }

    public Boolean getGradle() {
        return isGradle;
    }

    public void setGradle(Boolean gradle) {
        isGradle = gradle;
    }

    private void projectAnalyzerType(String pathProject) {
        File proyecto = new File(pathProject);
        boolean isMaven = new File(proyecto, "pom.xml").exists();
        this.isGradle = new File(proyecto, "build.gradle").exists() || new File(proyecto, "build.gradle.kts").exists();
        this.isMaven = isMaven;
    }


}
