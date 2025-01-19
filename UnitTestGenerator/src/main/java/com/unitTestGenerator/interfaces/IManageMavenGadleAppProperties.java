package com.unitTestGenerator.interfaces;

import com.unitTestGenerator.analyzers.GradleAnalyzer;
import com.unitTestGenerator.analyzers.PomAnalyzer;
import com.unitTestGenerator.builders.interfaces.IFileManager;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.util.IBaseModel;

import java.io.File;

public interface IManageMavenGadleAppProperties extends IApplicationTestProperties {


    default void gradleAnalyzer(String pathProject, Integer importType){
        File fileGradle = new File( this.stringEnsamble(pathProject , Separator ,"build.gradle"));
        GradleAnalyzer analyzer = new GradleAnalyzer(fileGradle);
        if(importType == 1){
            analyzer.startedMokitoDependencys();
        }else {
            analyzer.startedWithoutMock();
        }
    }

    default void projectTypeDependencesAnalizer(String pathProject, Project project){
        if(project.getMaven()) {
            PomAnalyzer.getInstance().addDependencys(pathProject,1);
        } else if(project.getGradle()){
            this.gradleAnalyzer( pathProject, 1);
        }
    }

    default void addLombokDependency(Project project){
        if(project.getMaven()) {
            PomAnalyzer.getInstance().addDependencys(project.getPathProject(),2);
        } else if(project.getGradle()){
            this.gradleAnalyzer( project.getPathProject(), 1);
        }
    }


    default void applicationTestPropertiesExist(Project project){
        String basePath = stringPaths(true,true,project.getPathProject(),"src","test","resources","application-test.properties");

        if(!filePathExists(basePath)){
            if(project.getMaven()) {
                PomAnalyzer.getInstance().addDependencys(project.getPathProject(),0);
            } else if(project.getGradle()){
                this.gradleAnalyzer( project.getPathProject(), 0);
            }
            File applicationTestPropertiesFile = new File(basePath);

            String fileContent ="";
            String oldContent = this.fileExist(applicationTestPropertiesFile);

            fileContent =
                    oldContent != null && !oldContent.equals("") && !oldContent.contains(this.contentApplicationTestPropertiesFile()) ?
                            stringEnsamble(oldContent, "\n", this.contentApplicationTestPropertiesFile())
                            : this.contentApplicationTestPropertiesFile();
            this.writefilesI(applicationTestPropertiesFile, fileContent);
        }
    }



}
