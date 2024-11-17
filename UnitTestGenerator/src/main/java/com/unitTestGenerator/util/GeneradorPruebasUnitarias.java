package com.unitTestGenerator.util;

import com.unitTestGenerator.builders.BuildTestFile;
import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.services.GeneratedVariableService;

import java.io.File;


public class GeneradorPruebasUnitarias implements IBaseModel {

    private Project project;

    public GeneradorPruebasUnitarias() {
    }

    public GeneradorPruebasUnitarias(Project project) {
        this.project = project;
    }

    public void generarPruebas(Clase clase, String pathProject) {

        if(project.getMaven()) {
            PomAnalyzer.agregarDependencias(pathProject);
        } else if(this.project.getGradle()){
            this.gradleAnalyzer( pathProject);
        }

        String nombreClase = clase.getNombre();
        String contenidoPrueba = obtenerContenidoPrueba(clase);
        String basePath=  this.stringPaths(true,true,"src","test","java");//"/src/test/java/"
//        String basePath= this.stringEnsamble(Separator,"src",Separator,"test",Separator,"java",Separator);//"/src/test/java/"
        String packagePath = clase.getPaquete().replace(".", Separator);
        String rutaPrueba = this.stringEnsamble(pathProject,basePath, packagePath, Separator, nombreClase, "Test.java");

        BuildTestFile.getInstance().crearArchivoPrueba(rutaPrueba, contenidoPrueba);

    }

    public void gradleAnalyzer(String pathProject){
        File fileGradle = new File( this.stringEnsamble(pathProject , Separator ,"build.gradle"));
        GradleAnalyzer analyzer = new GradleAnalyzer(fileGradle);
        analyzer.started();
    }

    private  String obtenerContenidoPrueba(Clase clase) {
        StringBuilder contenido = new StringBuilder();
        GeneratedVariableService variableService = GeneratedVariableService.getInstance();

        contenido.append("package ").append(clase.getPaquete()).append(";").append("\n");
        contenido.append("import org.junit.Test;\n");
        contenido.append("import org.junit.Assert;\n");
        contenido.append("import org.assertj.core.api.Assertions;\n");
        contenido.append("import org.junit.jupiter.api.Test;\n");
        contenido.append("import org.junit.jupiter.api.extension.ExtendWith;\n");
        contenido.append("import org.mockito.InjectMocks;\n");
        contenido.append("import org.mockito.Mock;\n");
        contenido.append("import org.mockito.Mockito;\n");
        contenido.append("import org.mockito.junit.jupiter.MockitoExtension;\n").append("\n");

        contenido.append( this.stringEnsamble("import ", clase.getPaquete(), ".",clase.getNombre())).append("\n");

        for(Variable variable : clase.getVariables()){

           this.project.getClaseList().stream().forEach(projectClass -> {
                if (projectClass.getNombre() != null && projectClass.getNombre().equals(variable.getTipo())) {
                    contenido.append(
                            this.stringEnsamble(
                                    "import ", projectClass.getPaquete(), ".",projectClass.getNombre())
                    ).append("\n");
                }
            });

        }

        contenido.append("\n").append("public class " + clase.getNombre() + "Test {\n");

        // Generar pruebas para variables

        contenido.append(variableService.generateVariable(clase));

        for (Metodo metodo : clase.getMetodos()) {
            contenido.append(obtenerContenidoMetodo(metodo));
        }
        contenido.append("}\n");

        return contenido.toString();
    }

    private static String obtenerContenidoMetodo(Metodo metodo) {
        return "\n@Test\npublic void test" + metodo.getNombre().substring(0, 1).toUpperCase() + metodo.getNombre().substring(1) + "() {\n" +
                "    // Implementar prueba\n" +
                "    Assert.assertTrue(true);\n" +
                "}\n";
    }



}