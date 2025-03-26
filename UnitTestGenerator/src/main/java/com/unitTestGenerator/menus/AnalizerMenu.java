package com.unitTestGenerator.menus;

import com.unitTestGenerator.analyzers.services.AnalizerProjectService;
import com.unitTestGenerator.analyzers.services.interfaces.IAnalizerProjectServiceManager;
import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.PrintClassAnalyzers;
import com.unitTestGenerator.printers.PrintProjectAnalyzers;
import com.unitTestGenerator.uml.sevices.PrintClassToUML;
import com.unitTestGenerator.util.IBaseModel;

import java.util.Scanner;

@Component
public class AnalizerMenu implements IAnalizerProjectServiceManager, IBaseModel, PrintProjectAnalyzers {

    private AnalizerProjectService analizerProjectService;
    private PrintClassAnalyzers printClassAnalyzers;

    public AnalizerMenu() {
        this.printClassAnalyzers = printClassAnalyzers;
        this.analizerProjectService = this.getInstanceAPSI();
    }

    public void analizerMenuInitial(Project project){

        Scanner scanner = new Scanner(System.in);

        boolean continuar = false;

        while (!continuar) {
            this.AnalysisOptionsMenuV1000();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 2:
                    methodsOfclass(scanner, project);
                    continuar = this.askContinue(scanner);
                    if (continuar) {
                        this.goToMainMenu();
                    }
                    break;
                case 3:
                    this.classDetail(scanner, project);
                    continuar = this.askContinue(scanner);
                    if (continuar) {
                        this.goToMainMenu();
                    }
                    break;
                case 4:
                    this.printProjectFileTree(project);
                    continuar = this.askContinue(scanner);
                    if (continuar) {
                        this.goToMainMenu();
                    }
                    break;
                case 5:
                    this.printProjectClassTree(project);
                    continuar = this.askContinue(scanner);
                    if (continuar) {
                        this.goToMainMenu();
                    }
                    break;
                case 6:
                    continuar = false;
                    this.goToMainMenu();
                    break;
                case 7:
                    System.out.println("Goodbye");
                    continuar = false;
                    break;
                case 8:
                    System.out.println("Goodbye");
                    continuar = false;
                    break;
                default:
                    this.printClassList(project);
                    this.subMenu1(project,scanner);
            }
        }
    }

    public void AnalysisOptionsMenuV1000(){
        this.printColummString(
                "Choose an option:",
                "1. Print Project Class list",
                "2. Print Methods of one class",
                "3. Print a Class with Details",
                "4. Print the Project File Tree",
                "5. Print the Project Class Tree",
                "6. Return to the Main Menu");
    }

    public void subMenu1V1000(){
        this.printColummString(
                "Choose an option:",
                "1. Print  methods of one class",
                "2. Return to the previous menu",
                "3. Return to the main menu");
    }

    public void subMenu1(Project project, Scanner scanner) {

            this.subMenu1V1000();
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                     methodsOfclass(scanner, project);
                    boolean userResponse = this.askContinue(scanner);
                    if (userResponse){
                        this.goToMainMenu();
                    }else {
                        analizerMenuInitial(project);
                    }
                    break;
                case 2:
                    analizerMenuInitial(project);
                    break;
                case 3:
                    this.goToMainMenu();
                    break;
                default:
                    System.out.println("Invalid option");
                    analizerMenuInitial(project);
            }
    }



    private void methodsOfclass(Scanner scanner, Project project) {
        System.out.println("Enter the name of the Class");
        String response = scanner.next().toLowerCase();
        if(response != null && !response.equals("")){
            this.printMethodsOfClass(project, response);
        }
    }

    private void classDetail(Scanner scanner, Project project) {
        System.out.println("Enter the name of the Class");
        String response = scanner.next().toLowerCase();
        if(response != null && !response.equals("")){
            Clase classs = project.getClass(response);
            if(classs != null && classs.getClassDetail() != null && !classs.getClassDetail().isEmpty()){
                this.printClassAnalyzers.printClassDetail(classs);
            }
        }
    }

    private boolean askContinue(Scanner scanner) {
        System.out.println("Return to main menu? (y/n)");
        String response = scanner.next().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Invalid response. Please enter 'y' for yes or 'n' for no.");
            response = scanner.next().toLowerCase();
        }
        return response.equals("y");
    }

    private void goToMainMenu(){
        AppProjectStarted appProjectStarted =  ContextIOC.getInstance().getClassInstance(AppProjectStarted.class);
        appProjectStarted.start();
    }

    public void analizerMenu(){
        this.printColummString("Analyze project Module",
                "Choose an option:",
                "1. Analyze project",
                "2. Load a Previous Saved Project",
                "3. Exit"
       );
    }

    public void AnalysisOptionsMenu(){
        this.printColummString(
                "Choose an option:",
                "1. Print the list of classes",
                "2. Print the list of classes with methods",
                "3. Print the project class tree",
                "4. Print the project file tree",
                "5. Print a class with details",
                "6. Generate File",
                "7. Return to the previous menu",
                "8. Return to the main menu");
    }

    public void printClassMenu(){
        this.printColummString(
                "Choose an option:",
                "1. Enter the class name",
                "2. Return to the previous menu");
    }

    public void generateFileMenu(){
        this.printColummString(
        "Choose an option:",
                "1. Class Report" +
                "2. Protect Report" +
                "3. Return to the previous menu");
    }





}
