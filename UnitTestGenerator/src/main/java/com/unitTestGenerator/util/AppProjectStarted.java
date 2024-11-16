package com.unitTestGenerator.util;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppProjectStarted {


    private String pathProject;
    private static AppProjectStarted instance;
    public  List<Clase> clases = new ArrayList<>();
    private Project project;



    private  AppProjectStarted() {
    }

    public static AppProjectStarted getInstance(){
        if(instance == null){
            instance = new AppProjectStarted();
        }
        return instance;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Welcome to the unit test creation tool");
            System.out.println("Choose an option:");
            System.out.println("1. Analyze project");
            System.out.println("2. Generate unit tests");
            System.out.println("3. Exit");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    analizarProyecto(scanner, true);
                    continuar = preguntarContinuar(scanner);
                    break;
                case 2:
                    generarPruebasUnitarias(scanner);
                    continuar = preguntarContinuar(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye");
                    continuar = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    continuar = preguntarContinuar(scanner);
            }
        }
    }


    private void analizarProyecto(Scanner scanner,  boolean isAnalisis ) {

        System.out.println("Enter the project path:");
        this.pathProject = scanner.next();

        if(this.pathProject != null){
            clases = AnalizadorProyecto.analizarProyecto(pathProject);
            project = new Project(clases, pathProject);
            this.projectAnalyzerType();

            if(isAnalisis) {
                System.out.println("Classes found:");
                for (Clase clase : clases) {
                    System.out.println(clase.getNombre() + "  package: " + clase.getPaquete());
                }
            }
        }
    }

    private  void generarPruebasUnitarias(Scanner scanner) {
        List<Clase> clasesTemporal = new ArrayList<>();

        if(clases.isEmpty()){
            analizarProyecto(scanner, false);
        }

        System.out.println("Enter the class name to test to generate the unit tests:");
        String nombreClase = scanner.next();

        clases.stream().forEach(clase -> {
            if (clase.getNombre() != null && clase.getNombre().equals(nombreClase)) {
                clasesTemporal.add(clase);
            }
        });

        GeneradorPruebasUnitarias generate = new GeneradorPruebasUnitarias(this.project);
        Clase claseEncontrada = clasesTemporal.get(0);
        if (claseEncontrada != null) {
            generate.generarPruebas(claseEncontrada, this.pathProject);
        } else {
            System.out.println("Class not found");
        }

    }


    private  boolean preguntarContinuar(Scanner scanner) {
        System.out.println("Return to main menu? (y/n)");
        String respuesta = scanner.next().toLowerCase();

        while (!respuesta.equals("y") && !respuesta.equals("n")) {
            System.out.println("Invalid response. Please enter 'y' for yes or 'n' for no.");
            respuesta = scanner.next().toLowerCase();
        }
        return respuesta.equals("y");
    }

    private void projectAnalyzerType(){
        if (this.project.getMaven()) {
            System.out.println("Project Maven");
        } else if (this.project.getGradle()) {
            System.out.println("Project Gradle");
        } else if (this.project.getGradle() && this.project.getMaven()) {
            System.out.println("Project Gradle and Maven");
        }else {
            System.out.println("Unknown project type");
        }
    }


    public List<Clase> getClases() {
        return clases;
    }

    public Project getProject() {
        return project;
    }

    public  String getRutaProyecto() {
        return pathProject;
    }

}
