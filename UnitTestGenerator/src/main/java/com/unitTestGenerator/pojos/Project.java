package com.unitTestGenerator.pojos;

import com.unitTestGenerator.ioc.anotations.Component;

import java.io.File;
import java.util.*;

@Component
public class Project {

    private List<Clase> claseList = new ArrayList<>();
    private Map<String, Clase> mapClass = new HashMap<>();;
    private Map<String, List<Clase>> classHashMap = new HashMap<>();;
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
        return getClass3(className);
//        return getClass(className, this);
    }


//    private Clase getClass(String className, Project project){
//        Clase foundClass = null;
//        if(project.getMapClass() != null && !project.getMapClass().isEmpty() && className !=null && !className.equals("")){
//            if(project.getMapClass().containsKey(className)){
//                foundClass = project.getMapClass().get(className);
//            }
//        }
//        return foundClass;
//    }

    private Clase getClass3(String className) {
        Clase foundClass = null;
        if (this.claseList != null && !this.claseList.isEmpty() && className != null && !className.equals("")) {
            for (Clase classs : this.claseList) {
                if (classs.getNombre().equals(className)) {
                    return classs;
                }
            }
        }
        return foundClass;
    }

//    private Clase getClass2(String className){
//        Clase foundClass = null;
//        if(this.claseList != null && !this.claseList.isEmpty() && className !=null && !className.equals("")){
//            int mildePoint = (this.claseList.size() + 1) / 2;
//            List<Clase> firstPart = this.claseList.subList(0, mildePoint);
//            List<Clase> seconPart = this.claseList.subList(mildePoint, this.claseList.size());
//
//            if(ispresent(firstPart, className)){
//               Optional <Clase> classs = getClassFromList(firstPart,className);
//               if(classs.isPresent()){
//                   foundClass = classs.get();
//               }
//            } else {
//                Optional <Clase> classs = getClassFromList(seconPart,className);
//                if(classs.isPresent()){
//                    foundClass = classs.get();
//                }
//            }
//        }
//        return foundClass;
//    }
    //    private  String getProjectImports(List<String> classImports ) {
//        String importText="";
//        for(String imp : classImports) {
//            int lastDotIndex = imp.lastIndexOf('.');
//            String temp = importText.substring(lastDotIndex + 1);
//            String temp2 = importText.substring( importText.length() - lastDotIndex );
//            importText = temp;
//        }
//        return importText;
//    }
    //    private Clase getClass2(String className, String classPackage){
//        Clase foundClass = null;
//        if(this.classHashMap != null && !this.classHashMap.isEmpty() && className !=null && !className.equals("")){
//            if(this.classHashMap.containsKey(className)){
//                List<Clase> list = this.classHashMap.get(className);
//                if( list != null && !list.isEmpty() && list.size() > 1 ){
//                    for (Clase classs : list){
//                       if(classs.getPaquete().equals(classPackage)){
//                           foundClass = classs;
//                       }
//                    }
//                }else if( list != null && !list.isEmpty()){
//                    foundClass = list.get(0);
//                }
//            }
//        }
//        return foundClass;
//    }


    private Boolean ispresent(List<Clase> list, String className){
        return list.stream().anyMatch(classs -> className.equals(classs.getNombre()));
    }

    private Optional <Clase> getClassFromList(List<Clase> list, String className){
        return  list.stream().filter(clase -> clase.getNombre().equals(className)).findFirst();
    }

    public Map<String, List<Clase>> getClassHashMap() {
        return classHashMap;
    }

    public void setClassHashMap(Map<String, List<Clase>> classHashMap) {
        this.classHashMap = classHashMap;
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
