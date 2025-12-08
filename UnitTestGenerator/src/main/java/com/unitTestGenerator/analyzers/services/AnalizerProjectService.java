package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.analyzers.services.interfaces.IClassDetailBuilder;
import com.unitTestGenerator.analyzers.services.interfaces.IExtendsInInterfacesService;
import com.unitTestGenerator.analyzers.services.interfaces.IProjectAnalizeService;
import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.interfaces.IPorjectName;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.interfaces.IPrintAnalizeImports;
import com.unitTestGenerator.printers.interfaces.IPrintService;
import com.unitTestGenerator.util.PathValidator;
import com.unitTestGenerator.util.interfaces.validPath;

import java.util.Scanner;

@Component
@Singleton
public class AnalizerProjectService implements IPrintService, IPorjectName,IPrintAnalizeImports, IExtendsInInterfacesService, IProjectAnalizeService, IClassDetailBuilder, validPath {

    
    public AnalizerProjectService() {
    }


    public Project analizerProject(Scanner scanner, boolean isAnalisis, Project project) {

        String pathProject ="";
       if(project !=null && project.getPathProject() != null && !project.getPathProject().equals("")){
            pathProject = project.getPathProject();
           this.service().print_DARKGREEN("Project path: "+ pathProject);
       }else{
           this.service().print_DARKGREEN("Enter the project path:");
            pathProject = scanner.next();
       }

       if(pathProject == null || pathProject.equals("")){
           this.service().print_RED("ERROR: The project path is empty");
           ContextIOC.getInstance().getClassInstance(AppProjectStarted.class).start();
           return null;
       }

        if ( this.isValidPathAndExists(pathProject)){
           return projectAnalize(pathProject, isAnalisis, project);
       }else {
           String newPath = this.pathError( scanner, pathProject);
           if ( newPath != null && !newPath.equals("")){
               return projectAnalize(newPath, isAnalisis, project);
           }else {
               return null;
           }
       }
    }

    private void goToMainMenu(){
        ContextIOC.getInstance().getClassInstance(AppProjectStarted.class).start();
    }

    private String pathError(Scanner scanner,  String pathProject ){
        this.service().print_RED("!!!!! ERROR: The project path is Invalid !!!!! ");
        this.service().print_DARKGREEN("Enter the project path:");
        pathProject = scanner.next();

        if ( this.isValidPathAndExists(pathProject)){
            return pathProject;
        } else {
            this.goToMainMenu();
        }
      return "";
    }


    public Project analizerProjectSaveProject( boolean isAnalisis, Project project) {
        System.out.println("Enter the project path:");
        return projectAnalize(project.getPathProject(), isAnalisis, project);
    }

    public Project projectAnalize(String pathProject,  boolean isAnalisis, Project project ){
        if(pathProject != null && project != null){
            project = this.executeProjectAnalize(pathProject, isAnalisis );
            this.getInterfaceStructure(project);
            this.getExtendsStructure(project);
            this.projectAnalyzerType(project);
            project.getPrinterProject().setProjectUml(printListClassToUML(project));
            this.generateImportsMap(project);
            this.generateClassesDetail(project);
            project.setName(this.getArtifatOrFileName(pathProject));
//            String projectToElement =  ContextIOC.getInstance().getClassInstance(PrintClassToUML.class).projectToElement(project);
//            this.printProjectAnalize(project,isAnalisis);
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
