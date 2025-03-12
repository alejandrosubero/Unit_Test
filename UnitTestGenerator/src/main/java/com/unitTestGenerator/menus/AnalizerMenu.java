package com.unitTestGenerator.menus;

import com.unitTestGenerator.analyzers.services.AnalizerProjectService;
import com.unitTestGenerator.analyzers.services.interfaces.IAnalizerProjectServiceManager;
import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.util.IBaseModel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Componente
public class AnalizerMenu implements IAnalizerProjectServiceManager, IBaseModel {


    private AnalizerProjectService analizerProjectService;

    public AnalizerMenu() {
        this.analizerProjectService = this.getInstanceAPSI();
    }

    public void analizerMenuInitial(Project project, Scanner scanner){

        boolean continuar = true;

        while (continuar) {
            this.analizerOptionMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    analizerProjectService.analizerProject(scanner, true, project);
                    continuar = this.askContinue(scanner);
                    break;
                case 2:
                    // Load a Previous Saved Project -> menu"
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

    public void analizerOptionMenu(){
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("Analyze project Module").append("\n");
//        buffer.append("Choose an option:").append("\n");
//        buffer.append("1. Analyze project").append("\n");
//        buffer.append("2. Load a Previous Saved Project").append("\n");
//        buffer.append("3. Exit").append("\n");
//        System.out.println(buffer.toString());

        this.printColummString("Analyze project Module",
                "Choose an option:",
                "1. Analyze project",
                "2. Load a Previous Saved Project",
                "3. Exit"
       );
    }

    // need change for adapter a new requirements.
    private boolean askContinue(Scanner scanner) {
        System.out.println("Return to main menu? (y/n)");
        String response = scanner.next().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Invalid response. Please enter 'y' for yes or 'n' for no.");
            response = scanner.next().toLowerCase();
        }
        return response.equals("y");
    }

}
