package com.unitTestGenerator.pojos;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class Project {

    private List<Clase> claseList;
    private Boolean isMaven;
    private Boolean isGradle;

    public Project() {
    }

    public Project(List<Clase> claseList) {
        this.claseList = claseList;
    }

    public Project(List<Clase> claseList, String pathProject) {
        this.claseList = claseList;
        this.projectAnalyzerType(pathProject);
    }

    public Project(List<Clase> claseList, Boolean isMaven, Boolean isGradle) {
        this.claseList = claseList;
        this.isMaven = isMaven;
        this.isGradle = isGradle;
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
        if(project.getClaseList() != null && !project.getClaseList().isEmpty() && className !=null && !className.equals("")){
            for(Clase clasS : project.getClaseList()){
                if(clasS.getNombre() !=null && clasS.getNombre().toLowerCase().equals(className.toLowerCase())){
                    foundClass = clasS;
                }
            }
        }
        return foundClass;
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
        this.isGradle = new File(proyecto, "build.gradle").exists() ||
                new File(proyecto, "build.gradle.kts").exists();
        this.isMaven = isMaven;
    }


}
