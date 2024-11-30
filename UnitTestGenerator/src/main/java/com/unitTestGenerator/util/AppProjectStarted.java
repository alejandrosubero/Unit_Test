package com.unitTestGenerator.util;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
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
            clases = AnalizadorProyecto.getInstance().analizarProyecto(pathProject);
            project = new Project(clases, pathProject);
            this.projectAnalyzerType();

            if(isAnalisis) {
                System.out.println("Classes found:...");
                for (Clase clase : clases) {
                    if(clase.getMetodos() != null && !clase.getMetodos().isEmpty()){
                        System.out.println(clase.getNombre() + "  package: " + clase.getPaquete());
                        for(Metodo metod : clase.getMetodos()){
                            if(metod.getInstanceMethodCalls() != null && !metod.getInstanceMethodCalls().isEmpty() ){
                                metod.getInstanceMethodCalls().forEach(instanceMethodCall -> {
                                    System.out.println(  "CALL: -> Method: "+ metod.getNombre() + "| Call: "+instanceMethodCall.getOperation()+" |.");
                                });
                            }
                        }
                    }
                }
            }

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
        Clase claseEncontrada = null;

        if(clases.isEmpty()){
            analizarProyecto(scanner, false);
        }

        System.out.println("Enter the class name to test to generate the unit tests:");
        String nombreClase = scanner.next();

        String method =  questionOfMethodToTest( scanner);
        if(method.equals("CANCEL")){
            preguntarContinuar(scanner);
        }

        Boolean useMock = this.questionAboutUseMock(scanner);

        clases.stream().forEach(clase -> {
            if (clase.getNombre() != null && clase.getNombre().equals(nombreClase)) {
                clasesTemporal.add(clase);
            }
        });

        try {
            GeneradorPruebasUnitarias generate = new GeneradorPruebasUnitarias(project);
            claseEncontrada = clasesTemporal.get(0);
            claseEncontrada.setUseMock(useMock);
            claseEncontrada.setTestMethod(method);
            generate.generarPruebas(claseEncontrada, pathProject);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Class not found");
        }
    }


    private boolean questionAboutUseMock(Scanner scanner) {
        String respuesta ="n";
        System.out.println("Do you want to use Mock in test Class?.(y/n)");
        respuesta = scanner.next().toLowerCase();

        while (!respuesta.equals("y") && !respuesta.equals("n")) {
            System.out.println("Invalid response. Please enter 'y' for yes or 'n' for no.");
            respuesta = scanner.next().toLowerCase();
        }
        return respuesta.equals("y");
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

    private Integer getNumber(Scanner scanner){
        System.out.println("What method do you want to test?");
        System.out.println("Choose an option:");
        System.out.println("1. All");
        System.out.println("2. Nama of Specific method");
        int opcion = 0;

        try {
            opcion = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("No valid data... ");
            System.out.println("Choose an option:");
            System.out.println("Only numbers 1 or 2");
            System.out.println("1. All");
            System.out.println("2. Nama of Specific method");
            opcion = scanner.nextInt();
        }

        return opcion;
    }

    public String questionOfMethodToTest(Scanner scanner){

        String respuesta ="";
        Integer opcion = this.getNumber( scanner);

        if(opcion == 1){
            respuesta = "all";
        }
        if(opcion == 2 ){
            System.out.println("Enter the name of the method to be tested");
            respuesta = scanner.next().toLowerCase();
        }

        if( opcion != 1 && opcion != 2){
            System.out.println("Invalid response. Please enter '1' for yes or '2' .");
            if( opcion != 1 && opcion != 2){
                System.out.println("You have entered an invalid option again. Do you want to continue or cancel?");
                System.out.println("1. All");
                System.out.println("2. Nama of Specific method");
                int opcionInvalid = scanner.nextInt();
                respuesta = opcionInvalid == 1? "all":"CANCEL";
            }
        }
        return respuesta;
    }





}
