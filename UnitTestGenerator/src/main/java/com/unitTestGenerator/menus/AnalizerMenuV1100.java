package com.unitTestGenerator.menus;

import com.unitTestGenerator.analyzers.services.AnalizerProjectService;
import com.unitTestGenerator.analyzers.services.interfaces.IAnalizerProjectServiceManager;
import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.core.ProjectHolder;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.PrintProjectAnalyzers;
import com.unitTestGenerator.util.IBaseModel;

import java.util.Scanner;

//V1.1.0.0  In progress....

@Component
@Singleton
public class AnalizerMenuV1100  implements IAnalizerProjectServiceManager, IBaseModel, PrintProjectAnalyzers {

    private ProjectHolder projectHolder;
    private AnalizerProjectService analizerProjectService;

    public AnalizerMenuV1100(ProjectHolder projectHolder, AnalizerProjectService analizerProjectService) {
        this.projectHolder = projectHolder;
        this.analizerProjectService = analizerProjectService;
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
//                analizerMenuInitial(project);
                break;
            case 3:
                this.goToMainMenu();
                break;
            default:
                System.out.println("Invalid option");
//                analizerMenuInitial(project);
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
