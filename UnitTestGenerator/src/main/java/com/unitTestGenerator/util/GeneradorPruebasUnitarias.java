package com.unitTestGenerator.util;

import com.unitTestGenerator.builders.BuildTestFile;
import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.*;
import com.unitTestGenerator.services.GenerateMethodService;
import com.unitTestGenerator.services.GeneratedVariableService;
import com.unitTestGenerator.services.MethodParameterObject;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class GeneradorPruebasUnitarias implements IBaseModel {

    private Project project;

    public GeneradorPruebasUnitarias() {
    }

    public GeneradorPruebasUnitarias(Project project) {
        this.project = project;
    }

    public void generarPruebas(Clase clase, String pathProject) {

       this.projectTypeDependencesAnalizer(pathProject);
        String contedOfTestClass = generateTestFileBody(clase);
        String pathOfTest = this.getPathOfTest( clase,  pathProject);
        BuildTestFile.getInstance().createTestFile(pathOfTest, contedOfTestClass);
    }

    private  String generateTestFileBody(Clase clase) {
        StringBuilder contenido = new StringBuilder();
        GeneratedVariableService variableService = GeneratedVariableService.getInstance();

        contenido.append(this.generateImport(clase)).append("\n");
        contenido.append(this.classSingne(clase));
        contenido.append(variableService.generateVariable(clase));
        contenido.append(GenerateMethodService.getInstance().generateMethods(clase,project) );
        contenido.append("}\n");
        return contenido.toString();
    }

    private String classSingne(Clase clase){
        StringBuilder classSingne = new StringBuilder();

        if(clase.getUseMock()){
            classSingne.append("\n").append("@ExtendWith(MockitoExtension.class)").append("\n");
        }else{
            classSingne.append("\n");
           if( clase.getNombre().contains("JpaRepository") || clase.getNombre().contains("Repository") || clase.getNombre().contains("CrudRepository")){
               classSingne.append("\n").append("@DataJpaTest").append("\n");
               classSingne.append("@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)").append("\n");
           }
        }
        classSingne.append("public class " + clase.getNombre() + "Test {\n");
        return classSingne.toString();
    }

    private void gradleAnalyzer(String pathProject){
        File fileGradle = new File( this.stringEnsamble(pathProject , Separator ,"build.gradle"));
        GradleAnalyzer analyzer = new GradleAnalyzer(fileGradle);
        analyzer.started();
    }

    private void projectTypeDependencesAnalizer(String pathProject){
        if(project.getMaven()) {
            PomAnalyzer.getInstance().agregarDependencias(pathProject);
        } else if(this.project.getGradle()){
            this.gradleAnalyzer( pathProject);
        }
    }

    private String getPathOfTest(Clase clase, String pathProject){
        String claseName = clase.getNombre();
        String basePath=  this.stringPaths(true,true,"src","test","java");//"/src/test/java/"
        String packagePath = clase.getPaquete().replace(".", Separator);
        String pathOfTest = this.stringEnsamble(pathProject,basePath, packagePath, Separator, claseName, "Test.java");
        return pathOfTest;
    }

    private String generateImport(Clase clase){
        StringBuilder contex = new StringBuilder();

        contex.append("package ").append(clase.getPaquete()).append(";").append("\n");
        contex.append("import org.junit.Test;\n");
        contex.append("import org.junit.Assert;\n");
        contex.append("import org.junit.jupiter.api.BeforeEach;\n");
        contex.append("import org.assertj.core.api.Assertions;\n");
        contex.append("//import org.junit.jupiter.api.Test;\n");
        contex.append("import org.junit.jupiter.api.extension.ExtendWith;\n");
        contex.append("import org.mockito.InjectMocks;\n");
        contex.append("import org.mockito.Mock;\n");
        contex.append("import org.mockito.Mockito;\n");
        contex.append("import org.mockito.MockitoAnnotations;\n");
        contex.append("import org.mockito.junit.jupiter.MockitoExtension;\n").append("\n");
        contex.append( this.stringEnsamble("import ", clase.getPaquete(), ".",clase.getNombre())).append(";").append("\n");
        contex.append(MethodParameterObject.getInstance().getImportMethodParameterObject(clase,project));

        for(Variable variable : clase.getVariables()){
            this.project.getClaseList().stream().forEach(projectClass -> {
                if (projectClass.getNombre() != null && projectClass.getNombre().equals(variable.getTipo())) {
                    contex.append(
                            this.stringEnsamble(
                                    "import ", projectClass.getPaquete(), ".",projectClass.getNombre())
                    ).append(";").append("\n");
                }
            });
        }
        return contex.toString();
    }



}