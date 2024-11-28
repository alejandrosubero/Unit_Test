package com.unitTestGenerator.util;

import com.unitTestGenerator.builders.BuildTestFile;
import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.*;
import com.unitTestGenerator.services.GeneratedVariableService;

import java.io.File;
import java.util.List;
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
            clase.getMetodos().forEach(metodo -> contex.append(obtenerContenidoMetodo(metodo, clase)));
        }else{
            for (Metodo metodo : clase.getMetodos()) {
                if (clase.getTestMethod().toLowerCase().equals(metodo.getNombre().toLowerCase())){
                    contex.append(obtenerContenidoMetodo(metodo, clase));
                }
            }
        }
        contex.append("\n");
        return contex.toString();
    }

    private  String obtenerContenidoMetodo(Metodo metodo, Clase clase) {

        String methodName = String.format("%s%s", metodo.getNombre().substring(0, 1).toUpperCase(), metodo.getNombre().substring(1));
        String content = generarContenidoMetodoPrueba(metodo, clase);
        String methodoTest = String.format("\n@Test\npublic void test%s() {\n  %s \n }\n", methodName,content);
        return methodoTest;
    }

    private String generarContenidoMetodoPrueba(Metodo metodo, Clase clase) {
        StringBuilder contenido = new StringBuilder();

        // Verificar si se utiliza mock
        if (clase.getUseMock()) {
            // Generar contenido para mock
            contenido.append(generarContenidoMock(metodo));
        } else {
            // Generar contenido sin mock
            contenido.append(generarContenidoSinMock(metodo));
        }

        return contenido.toString();
    }

    private String generarContenidoMock(Metodo metodo) {
        StringBuilder contenido = new StringBuilder();

        // Obtener las instancias de métodos
        List<InstanceMethodCall> instanceMethodCalls = metodo.getInstanceMethodCalls();

        // Recorrer las instancias de métodos
        for (InstanceMethodCall instanceMethodCall : instanceMethodCalls) {
            // Obtener el nombre del método
            String methodName = instanceMethodCall.getMethod();

            // Obtener el nombre de la variable de instancia
            String variableInstanceName = instanceMethodCall.getVariableInstace();

            // Obtener los parámetros del método
            List<ParametroMetodo> parametros = instanceMethodCall.getParametros();

            // Generar la llamada al método mock
            contenido.append(generarLlamadaMetodoMock(methodName, variableInstanceName, parametros));
        }
        return contenido.toString();
    }

    private String generarLlamadaMetodoMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros) {
        StringBuilder contenido = new StringBuilder();

        // Agregar la llamada al método mock
        contenido.append("Mockito.when(").append(variableInstanceName).append(".").append(methodName).append("(");

        // Agregar los parámetros
        for (int i = 0; i < parametros.size(); i++) {
            ParametroMetodo parametro = parametros.get(i);
            contenido.append(parametro.getNombre());

            if (i < parametros.size() - 1) {
                contenido.append(", ");
            }
        }

        contenido.append(")).thenReturn(");

        // Agregar el valor de retorno
        contenido.append(");");

        return contenido.toString();
    }

    private String generarContenidoSinMock(Metodo metodo) {
        StringBuilder contenido = new StringBuilder();

        // Obtener el nombre del método
        String methodName = metodo.getNombre();

        // Obtener los parámetros del método
        List<ParametroMetodo> parametros = metodo.getParametros();

        // Generar la llamada al método
        contenido.append(generarLlamadaMetodo(methodName, parametros));

        return contenido.toString();
    }

    private String generarLlamadaMetodo(String methodName, List<ParametroMetodo> parametros) {
        StringBuilder contenido = new StringBuilder();

        // Agregar la llamada al método
        contenido.append(methodName).append("(");

        // Agregar los parámetros
        for (int i = 0; i < parametros.size(); i++) {
            ParametroMetodo parametro = parametros.get(i);
            contenido.append(parametro.getNombre());

            if (i < parametros.size() - 1) {
                contenido.append(", ");
            }
        }

        contenido.append(");");

        return contenido.toString();
    }



}