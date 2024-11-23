package com.unitTestGenerator.util;

import com.unitTestGenerator.builders.BuildTestFile;
import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.services.GeneratedVariableService;

import java.io.File;
import java.util.Locale;


public class GeneradorPruebasUnitarias implements IBaseModel {

    private Project project;

    public GeneradorPruebasUnitarias() {
    }

    public GeneradorPruebasUnitarias(Project project) {
        this.project = project;
    }

    public void generarPruebas(Clase clase, String pathProject) {

       this.projectTypeDependencesAnalizer(pathProject);
        String contedOfTestClass = obtenerContenidoPrueba(clase);
        String pathOfTest = this.getPathOfTest( clase,  pathProject);
        BuildTestFile.getInstance().crearArchivoPrueba(pathOfTest, contedOfTestClass);
    }

    private  String obtenerContenidoPrueba(Clase clase) {
        StringBuilder contenido = new StringBuilder();
        GeneratedVariableService variableService = GeneratedVariableService.getInstance();

        contenido.append(this.generateImport(clase)).append("\n");
        contenido.append(this.classSingne(clase));
        contenido.append(variableService.generateVariable(clase));
        contenido.append(this.generateMethods(clase));
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
            PomAnalyzer.agregarDependencias(pathProject);
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
        contex.append("import org.assertj.core.api.Assertions;\n");
        contex.append("import org.junit.jupiter.api.Test;\n");
        contex.append("import org.junit.jupiter.api.extension.ExtendWith;\n");
        contex.append("import org.mockito.InjectMocks;\n");
        contex.append("import org.mockito.Mock;\n");
        contex.append("import org.mockito.Mockito;\n");
        contex.append("import org.mockito.junit.jupiter.MockitoExtension;\n").append("\n");

        contex.append( this.stringEnsamble("import ", clase.getPaquete(), ".",clase.getNombre())).append("\n");

        for(Variable variable : clase.getVariables()){
            this.project.getClaseList().stream().forEach(projectClass -> {
                if (projectClass.getNombre() != null && projectClass.getNombre().equals(variable.getTipo())) {
                    contex.append(
                            this.stringEnsamble(
                                    "import ", projectClass.getPaquete(), ".",projectClass.getNombre())
                    ).append("\n");
                }
            });
        }

        return contex.toString();
    }


    private String generateMethods( Clase clase){
        StringBuilder contex = new StringBuilder();

        if (clase.getTestMethod().toLowerCase().equals("all")){
            clase.getMetodos().forEach(metodo -> contex.append(obtenerContenidoMetodo(metodo)));
        }else{
            for (Metodo metodo : clase.getMetodos()) {
                if (clase.getTestMethod().toLowerCase().equals(metodo.getNombre().toLowerCase())){
                    contex.append(obtenerContenidoMetodo(metodo));
                }
            }
        }
        contex.append("\n");
        return contex.toString();
    }

    private static String obtenerContenidoMetodo(Metodo metodo) {
        return "\n@Test\npublic void test" + metodo.getNombre().substring(0, 1).toUpperCase() + metodo.getNombre().substring(1) + "() {\n" +
                "    // Implementar prueba\n" +
                "    Assert.assertTrue(true);\n" +
                "}\n";
    }



}