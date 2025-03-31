package com.unitTestGenerator.menus;

import com.unitTestGenerator.analyzers.services.AnalizerProjectService;
import com.unitTestGenerator.analyzers.services.interfaces.IAnalizerProjectServiceManager;
import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.core.ProjectHolder;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.PrintClassAnalyzers;
import com.unitTestGenerator.printers.PrintProjectAnalyzers;
import com.unitTestGenerator.test.AppTestDatabase;
import com.unitTestGenerator.util.IBaseModel;

import java.util.Scanner;

//V1.1.0.0  In progress....

@Component
@Singleton
public class AnalizerMenuV1100  implements IAnalizerProjectServiceManager, IBaseModel, PrintProjectAnalyzers {

    private ProjectHolder projectHolder;
    private AnalizerProjectService analizerProjectService;
    private PrintClassAnalyzers printClassAnalyzers;

    public AnalizerMenuV1100(ProjectHolder projectHolder, AnalizerProjectService analizerProjectService, PrintClassAnalyzers printClassAnalyzers) {
        this.projectHolder = projectHolder;
        this.analizerProjectService = analizerProjectService;
        this.printClassAnalyzers = printClassAnalyzers;
    }



    public void analizerMenu(){
        this.printColummString("Analyze project Module",
                "Choose an option:",
                "1. Analyze project",
                "2. Load a Previous Saved Project",
                "3. Exit"
        );
    }

    public void analizerMenu1(Project project, Scanner scanner) {
        this.analizerMenu();
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                Project projectAnalized  = analizerProjectService.analizerProject(scanner, true, project);
                this.projectHolder.setProject(projectAnalized);
                break;
            case 2:
                Project projectsave = ContextIOC.getInstance(AppTestDatabase.class).getClassInstance(SavedProjectMenu.class).started(scanner);

                if(projectsave == null){
                    this.analizerMenu1( project,  scanner);
                } else {
                    ...
                }

                break;
            case 3:
                this.goToMainMenu();
                break;
            default:
                System.out.println("Invalid option");
//                analizerMenuInitial(project);
        }
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



    public void analizerMenuInitial(Project project){
        Scanner scanner = new Scanner(System.in);
        this.AnalysisOptionsMenu();
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

    public void subMenu2V1000(){
        this.printColummString(
                "Choose an option:",
                "1. Exit",
                "2. Return to the previous menu",
                "3. Return to the main menu");
    }

    public void subMenu1V1000(){
        this.printColummString(
                "Choose an option:",
                "1. Print  methods of one class",
                "2. Return to the previous menu",
                "3. Return to the main menu");
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
