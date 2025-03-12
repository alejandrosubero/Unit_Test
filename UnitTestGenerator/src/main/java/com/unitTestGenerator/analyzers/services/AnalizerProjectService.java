package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.analyzers.services.interfaces.IExtendsInInterfacesService;
import com.unitTestGenerator.analyzers.services.interfaces.IProjectAnalizeService;
import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.IPrintAnalizeImports;

import java.util.Scanner;

@Componente
@Singleton
public class AnalizerProjectService implements IPrintAnalizeImports, IExtendsInInterfacesService, IProjectAnalizeService {

    
    public AnalizerProjectService() {
    }


    public Project analizerProject(Scanner scanner, boolean isAnalisis, Project project) {
        System.out.println("Enter the project path:");
        String pathProject = scanner.next();
        return projectAnalize(pathProject, isAnalisis, project);
    }

    public Project projectAnalize(String pathProject,  boolean isAnalisis, Project project ){
        if(pathProject != null && project != null){
            project = this.executeProjectAnalize(pathProject, isAnalisis );
            this.getInterfaceStructure(project);
            this.getExtendsStructure(project);
            this.projectAnalyzerType(project);
            String uml = printUMLClass(project);
            this.generateImportsMap(project);
            this.printProjectAnalize(project,isAnalisis);
        }
        return project;
    }

    private void projectAnalyzerType(Project project){
        if (project.getMaven()) {
            System.out.println("Project Maven");
        } else if (project.getGradle()) {
            System.out.println("Project Gradle");
        } else if (project.getGradle() && project.getMaven()) {
            System.out.println("Project Gradle and Maven");
        }else {
            System.out.println("Unknown project type");
        }
    }
}
