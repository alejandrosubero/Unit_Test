package com.unitTestGenerator.core;


import com.unitTestGenerator.analyzers.services.AnalizerProjectServiceI;
import com.unitTestGenerator.analyzers.services.IProjectAnalizeService;
import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.ioc.anotations.Inyect;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.menus.MainMenue;
import com.unitTestGenerator.test.interfaces.IInternalTest;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Componente
@Singleton
public class AppProjectStarted implements IProjectAnalizeService,MainMenue,IInternalTest {

    @Inyect
    private AnalizerProjectServiceI analizerProjectServiceI;

    @Inyect
    private Project project;

    @Inyect
    private GeneradorPruebasUnitarias generadorPruebasUnitarias;

//    private static AppProjectStarted instance;

    public AppProjectStarted() {
//        this.project = new Project();
    }

//    public static AppProjectStarted getInstance(){
//        if(instance == null){
//            instance = new AppProjectStarted();
//        }
//        return instance;
//    }


    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
           this.welcomeMenue();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
//                    analizarProyecto(scanner, true);
                    continuar = this.askContinue(scanner);
                    break;
                case 2:
                    generateUnitsTest(scanner);
                    continuar = this.askContinue(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye");
                    continuar = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    continuar = this.askContinue(scanner);
            }
        }
    }



    private  void generateUnitsTest(Scanner scanner) {

        if(this.project.getClaseList().isEmpty()){
            analizerProjectServiceI.analizerProject(scanner, false,this.project);
//            this.project = analizarProyecto(scanner, false);
        }
        System.out.println("Enter the class name to test to generate the unit tests:");
        String nombreClase = scanner.next();
        String method =  this.questionOfMethodToTest(scanner);
        if(method.equals("CANCEL")){
            this.askContinue(scanner);
        }
        Boolean useMock = this.questionAboutUseMock(scanner);
        this.generateUnitTest(nombreClase, method, useMock);
    }

    private void generateUnitTest( String nombreClase,String method,Boolean useMock){
        List<Clase> tempClass = new ArrayList<>();
        Clase claseEncontrada = null;

        this.project.getClaseList().stream().forEach(clase -> {
            if (clase.getNombre() != null && clase.getNombre().equals(nombreClase)) {
                tempClass.add(clase);
            }
        });

        try {
            GeneradorPruebasUnitarias generate = this.generadorPruebasUnitarias;
            generate.setProject(this.project);
            claseEncontrada = tempClass.get(0);
            claseEncontrada.setUseMock(useMock);
            claseEncontrada.setTestMethod(method);
            generate.generateTest(claseEncontrada, this.project.getPathProject());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Class not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Clase> getClases() {
        return this.project.getClaseList();
    }

    public Project getProject() {
        return this.project;
    }

    public  String getRutaProyecto() {
        return this.project.getPathProject();
    }



    @Override
    public void executeTest(String pathProject, boolean isAnalisis, String nombreClase, String method, Boolean useMock) {

        analizerProjectServiceI.projectAnalize (pathProject,isAnalisis, this.project);
//        this.projectAnalize( pathProject,  isAnalisis );
        if(!isAnalisis){
            if(method == null || method.equals("")){
                method = "all";
            }
            this.generateUnitTest( nombreClase, method, useMock);
        }
    }

    @Override
    public void analizedTest(String pathProject, boolean isAnalisis, String nombreClase, String method, Boolean useMock) {
        analizerProjectServiceI.projectAnalize (pathProject,isAnalisis, this.project);
//        this.projectAnalize( pathProject,  isAnalisis );
    }



}
