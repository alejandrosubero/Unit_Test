package com.unitTestGenerator.util;

import com.unitTestGenerator.builders.BuildTestFile;
import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.interfaces.IFileManager;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.pojos.TestFileContent;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.services.GenerateMethodService;
import com.unitTestGenerator.services.GeneratedVariableService;
import com.unitTestGenerator.services.MethodParameterObject;

import java.io.File;


public class GeneradorPruebasUnitarias implements IBaseModel, IFileManager {

    private Project project;

    public GeneradorPruebasUnitarias() {
    }

    public GeneradorPruebasUnitarias(Project project) {
        this.project = project;
    }

    public void generarPruebas(Clase clase, String pathProject) {

       this.projectTypeDependencesAnalizer(pathProject);

        TestFileContent fileContent = generateTestFileBody(clase);
        String pathOfTest = this.getPathOfTest( clase,  pathProject);
        BuildTestFile.getInstance().createTestFile(pathOfTest, fileContent);
    }

    private  TestFileContent generateTestFileBody(Clase clase) {

        GeneratedVariableService variableService = GeneratedVariableService.getInstance();

        TestFileContent fileContent = TestFileContent.builder()
                .testsClassImport(this.generateImport(clase))
                .testsClassSingne(this.classSingne(clase))
                .testsClassVariables(variableService.generateVariable(clase))
                .testsClassMethods("")
                .build();

        if(!clase.getUseMock()) {
            this.applicationTestPropertiesExist(this.project);
        }

        TestFileContent fileContentGenerate =  GenerateMethodService.getInstance().generateMethods(clase,this.project, fileContent);
        return fileContentGenerate;
    }



    private void applicationTestPropertiesExist(Project project){
        String basePath = stringPaths(true,true,"src","test","resources","application-test.properties");
        if(!filePathExists(project.getPathProject(),basePath)){
            ...
            // crear el archivo verificar las dependencias
        }
    }


    private String classSingne(Clase clase){
        StringBuilder classSingne = new StringBuilder();

        if(clase.getUseMock()){
            classSingne.append("\n").append("@ExtendWith(MockitoExtension.class)").append("\n");
        }else{
            //TODO VERIFICAR DE LAS A NOTACIONES ES MEJOR PARA TESTING EL REPOSITORIO
            classSingne.append("\n");
           if( clase.getNombre().contains("JpaRepository") || clase.getNombre().contains("Repository") || clase.getNombre().contains("CrudRepository")){
               classSingne.append("\n").append("@DataJpaTest").append("\n");
               classSingne.append("@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)").append("\n");
           } else {
               classSingne.append("\n").append("\t").append("@ExtendWith(SpringExtension.class)").append("\n");
               classSingne.append("\t").append("@SpringBootTest").append("\n");
               classSingne.append("\t").append("@ActiveProfiles(\"test\")").append("\n");
           }
        }
        classSingne.append("\t").append("public class " + clase.getNombre() + "Test {\n");
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
        contex.append("import org.junit.jupiter.api.Test;").append("\n");
        contex.append("import org.junit.jupiter.api.BeforeEach;").append("\n");
        contex.append("import org.junit.jupiter.api.extension.ExtendWith;").append("\n");
//        contex.append("import org.junit.jupiter.api.extension.ExtendWith;").append("\n");
        contex.append("import static org.assertj.core.api.Assertions.assertThat;").append("\n");
        contex.append("import org.assertj.core.api.Assertions;").append("\n");

        if(!clase.getUseMock()){
            contex.append("import org.springframework.beans.factory.annotation.Autowired;");
        }else {
            contex.append("import org.mockito.InjectMocks;").append("\n");
            contex.append("import org.mockito.Mock;").append("\n");
            contex.append("import org.mockito.InjectMocks;").append("\n");
            contex.append("import org.mockito.Mock;").append("\n");
            contex.append("import org.mockito.Mockito;").append("\n");
            contex.append("import org.mockito.MockitoAnnotations;").append("\n");
            contex.append("import org.mockito.junit.jupiter.MockitoExtension;").append("\n").append("\n");
        }


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