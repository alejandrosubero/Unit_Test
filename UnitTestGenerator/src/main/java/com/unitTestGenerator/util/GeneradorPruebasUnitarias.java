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
        String contedOfTestClass = obtenerContenidoPrueba(clase);
        String pathOfTest = this.getPathOfTest( clase,  pathProject);
        BuildTestFile.getInstance().crearArchivoPrueba(pathOfTest, contedOfTestClass);
    }

    private  String obtenerContenidoPrueba(Clase clase) {
        StringBuilder contenido = new StringBuilder();
        GeneratedVariableService variableService = GeneratedVariableService.getInstance();

        contenido.append(this.generateImport(clase)).append("\n");
        //
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


//    private String generateMethods( Clase clase){
//        StringBuilder contex = new StringBuilder();
//
//        if (clase.getTestMethod().toLowerCase().equals("all")){
//            clase.getMetodos().forEach(metodo -> contex.append(obtenerContenidoMetodo(metodo, clase)));
//        }else{
//            for (Metodo metodo : clase.getMetodos()) {
//                if (clase.getTestMethod().toLowerCase().equals(metodo.getNombre().toLowerCase())){
//                    contex.append(obtenerContenidoMetodo(metodo, clase));
//                }
//            }
//        }
//        contex.append("\n");
//        return contex.toString();
//    }
//
//    private  String obtenerContenidoMetodo(Metodo metodo, Clase clase) {
//
//        String methodName = String.format("%s%s", metodo.getNombre().substring(0, 1).toUpperCase(), metodo.getNombre().substring(1));
//        String content = generarContenidoMetodoPrueba(metodo, clase);
//        String methodoTest = String.format("\n@Test\npublic void test%s() {\n  %s \n }\n", methodName,content);
//        return methodoTest;
//    }
//
//    private String generarContenidoMetodoPrueba(Metodo metodo, Clase clase) {
//        StringBuilder contenido = new StringBuilder();
//        String conten = clase.getUseMock()? generarContenidoMock(metodo):generarContenidoSinMock(metodo);
//        contenido.append(conten);
//        return contenido.toString();
//    }
//
//    private String generarContenidoSinMock(Metodo metodo) {
//        StringBuilder contenido = new StringBuilder();
//
//        // Obtener el nombre del método
//        String methodName = metodo.getNombre();
//
//        // Obtener los parámetros del método
//        List<ParametroMetodo> parametros = metodo.getParametros();
//
//        // Generar la llamada al método
//        contenido.append(generarLlamadaMetodo(methodName, parametros));
//
//        return contenido.toString();
//    }
//
//    private String generarLlamadaMetodo(String methodName, List<ParametroMetodo> parametros) {
//        StringBuilder contenido = new StringBuilder();
//        // Agregar la llamada al método
//        contenido.append(methodName).append("(");
//        // Agregar los parámetros
//        for (int i = 0; i < parametros.size(); i++) {
//            ParametroMetodo parametro = parametros.get(i);
//            contenido.append(parametro.getNombre());
//            if (i < parametros.size() - 1) {
//                contenido.append(", ");
//            }
//        }
//        contenido.append(");");
//        return contenido.toString();
//    }
//
//
//    private String generarContenidoMock(Metodo metodo) {
//        StringBuilder contenido = new StringBuilder();
//
//        // Obtener las instancias de métodos
//        List<InstanceMethodCall> instanceMethodCalls = metodo.getInstanceMethodCalls();
//
//        // Recorrer las instancias de métodos
//        for (InstanceMethodCall instanceMethodCall : instanceMethodCalls) {
//            // Obtener el nombre del método
//            String methodName = instanceMethodCall.getMethod();
//
//            // Obtener el nombre de la variable de instancia
//            String variableInstanceName = instanceMethodCall.getVariableInstace();
//
//            // Obtener los parámetros del método
//            List<ParametroMetodo> parametros = instanceMethodCall.getParametros();
//
//            // Generar la llamada al método mock
//            contenido.append(generarLlamadaMetodoMock(methodName, variableInstanceName, parametros,  metodo,  this.project));
//        }
//        return contenido.toString();
//    }
//
//    //==============
//
//    private String generarLlamadaMetodoMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros, Metodo metodo, Project project) {
//        StringBuilder contenido = new StringBuilder();
//
//        // Agregar la llamada al método mock
//        contenido.append("Mockito.when(").append(variableInstanceName).append(".").append(methodName).append("(");
//
//        // Agregar los parámetros
//        for (int i = 0; i < parametros.size(); i++) {
//            ParametroMetodo parametro = parametros.get(i);
//            contenido.append(parametro.getNombre());
//
//            if (i < parametros.size() - 1) {
//                contenido.append(", ");
//            }
//        }
//
//        contenido.append(")).thenReturn(");
//
//        // Agregar el valor de retorno
//        contenido.append(generarValorDeRetorno(metodo, project)).append(");");
//
//        // Agregar el assert adecuado según el tipo de retorno
//        contenido.append("\n").append(getAssertType(metodo.getTipoRetorno(), null));
//
//        // Verificar si se utilizaron las clases simuladas en los mocks
//        contenido.append("\n").append(verificarMock(variableInstanceName, methodName, parametros));
//
//        return contenido.toString();
//    }
//
//    private String generarValorDeRetorno(Metodo metodo, Project project) {
//        String tipoRetorno = metodo.getTipoRetorno();
//        Clase claseRetorno = null;
//
//        if(!isValidTypeReturn(tipoRetorno)){
//            claseRetorno = project.getClass(tipoRetorno);
//        }
//
//        if (claseRetorno != null) {
//            // Generar un objeto de la clase de retorno
//            return generarObjetoClase(claseRetorno);
//        } else {
//            // Retornar un valor por defecto
//            return getValorPorDefecto(tipoRetorno);
//        }
//    }
//
//
//    private String generarObjetoClase(Clase clase) {
//        StringBuilder contenido = new StringBuilder();
//
//        contenido.append("new ").append(clase.getNombre()).append("(");
//
//        if(clase.getConstructores() !=null && !clase.getConstructores().isEmpty() && clase.getConstructores().stream().anyMatch(Constructor::isNoneParam)){
//            // Agregar los parámetros del constructor
////           Optional <Constructor> constructorOptional =  clase.getConstructores().stream().filter(constructor -> !constructor.isEmptyParameters()).findFirst();
//            List<ParametroMetodo> parametros = clase.getConstructores().stream().filter(constructor -> !constructor.isEmptyParameters()).findFirst().get().getParametros();
//
//            for (int i = 0; i < parametros.size(); i++) {
//                ParametroMetodo parametro = parametros.get(i);
//                contenido.append(parametro.getNombre());
//
//                if (i < parametros.size() - 1) {
//                    contenido.append(", ");
//                }
//            }
//        }
//
//        contenido.append(")");
//        return contenido.toString();
//    }
//
//    private String getValorPorDefecto(String tipoRetorno) {
//        switch (tipoRetorno) {
//            case "int":
//                return "0";
//            case "long":
//                return "0L";
//            case "double":
//                return "0.0";
//            case "float":
//                return "0.0f";
//            case "boolean":
//                return "false";
//            case "String":
//                return "\"\"";
//            case "Integer":
//                return "0";
//            case "Long":
//                return "0L";
//            case "Double":
//                return "0.0";
//            case "Float":
//                return "0.0F";
//            case "Boolean":
//                return "false";
//            default:
//                return "null";
//        }
//    }
//
//    private boolean isValidTypeReturn(String typeReturn) {
//        switch (typeReturn) {
//            case "int":
//            case "long":
//            case "double":
//            case "float":
//            case "boolean":
//            case "String":
//            case "Integer":
//            case "Long":
//            case "Double":
//            case "Float":
//            case "Boolean":
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    private String getAssertType(String tipoRetorno, String valorDeRetorno) {
//
//        if(valorDeRetorno == null){
//            valorDeRetorno = getValorPorDefecto(tipoRetorno);
//        }
//
//        switch (tipoRetorno) {
//            case "int":
//            case "long":
//            case "double":
//            case "float":
//            case "Integer":
//            case "Long":
//            case "Double":
//            case "Float":
//                return "Assert.assertEquals(" + valorDeRetorno + ", resultado);";
//            case "boolean":
//                if (valorDeRetorno.equals("true")) {
//                    return "Assert.assertTrue(resultado);";
//                } else {
//                    return "Assert.assertFalse(resultado);";
//                }
//            case "String":
//                return "Assert.assertEquals(\"" + valorDeRetorno + "\", resultado);";
//            default:
//                if (valorDeRetorno == null) {
//                    return "Assert.assertNull(resultado);";
//                } else {
//                    return "Assert.assertNotNull(resultado);";
//                }
//        }
//    }
//
//    private String verificarMock(String variableInstanceName, String methodName, List<ParametroMetodo> parametros) {
//        StringBuilder contenido = new StringBuilder();
//
//        contenido.append("Mockito.verify(").append(variableInstanceName).append(", Mockito.times(1)).").append(methodName).append("(");
//
//        // Agregar los parámetros
//        for (int i = 0; i < parametros.size(); i++) {
//            ParametroMetodo parametro = parametros.get(i);
//            contenido.append(parametro.getNombre());
//
//            if (i < parametros.size() - 1) {
//                contenido.append(", ");
//            }
//        }
//
//        contenido.append(");");
//
//        return contenido.toString();
//    }


}