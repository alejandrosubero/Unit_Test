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
            this.AnalysisOptionsMenuV1000();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 2:
                    methodsOfclass(scanner, project);
                    subMenu2( project,  scanner);
                    break;
                case 3:
                    this.classDetail(scanner, project);
                    subMenu2( project,  scanner);
                    break;
                case 4:
                    this.printProjectFileTree(project);
                    subMenu2( project,  scanner);
                    break;
                case 5:
                    this.printProjectClassTree(project);
                    subMenu2( project,  scanner);
                    break;
                case 6:
                    this.goToMainMenu();
                    break;
                default:
                    this.printClassList(project);
                    this.subMenu1(project,scanner);
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

    public void subMenu2V1000(){
        this.printColummString(
                "Choose an option:",
                "1. Exit",
                "2. Return to the previous menu",
                "3. Return to the main menu");
    }

    public void subMenu1(Project project, Scanner scanner) {
            this.subMenu1V1000();
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                     methodsOfclass(scanner, project);
                    subMenu2( project,  scanner);
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

    public void subMenu2(Project project, Scanner scanner) {
        this.subMenu2V1000();
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Good bye");
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
    

    private void goToMainMenu(){
        AppProjectStarted appProjectStarted =  ContextIOC.getInstance().getClassInstance(AppProjectStarted.class);
        appProjectStarted.start();
    }







}
