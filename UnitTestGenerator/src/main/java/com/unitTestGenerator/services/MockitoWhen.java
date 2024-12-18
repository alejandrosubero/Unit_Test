package com.unitTestGenerator.services;

import com.unitTestGenerator.pojos.*;

import java.util.List;

public interface MockitoWhen extends ReturnType {

    default String generateCallMethodMock(String testMethod, Metodo metodo, Project project, String retrunValue) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("Mockito.when(").append(testMethod).append(")").append(".thenReturn(");
        String valueReturn = retrunValue != null ? retrunValue: generateRetrunValue(metodo, project);
        contenido.append(valueReturn).append(");");
        return contenido.toString();
    }

    default String generateCallMethodMockDoNothing( String methodNameParts, String classNameCamelCase, String parametrosMethodTest) {
        return new StringBuilder("Mockito.doNothing().when(").append(classNameCamelCase).append(")").append(String.format(".%s(%s);" ,methodNameParts, parametrosMethodTest)).toString();
    }

    default String generateCallAssertType(Metodo metodo, Project project, String result) {
        StringBuilder contex = new StringBuilder();
        String valueReturn = generateRetrunValue(metodo, project);
        contex.append("\n").append(getAssertType(metodo.getTipoRetorno(), valueReturn, result));
        return contex.toString();
    }


    default String generateCallVerificarMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros) {
        StringBuilder contenido = new StringBuilder();
        // Verificar si se utilizaron las clases simuladas en los mocks
        contenido.append("\n").append(verificarMock(variableInstanceName, methodName, parametros));
        return contenido.toString();
    }

    default String generateCallMethodMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros, Metodo metodo, Project project) {
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
        contenido.append(generateRetrunValue(metodo, project)).append(");");
        String resultValueName = String.format("%s%s;" ,metodo.getNombre() ,"Result");
        // Agregar el assert adecuado según el tipo de retorno
        contenido.append("\n").append(getAssertType(metodo.getTipoRetorno(), null, resultValueName));

        // Verificar si se utilizaron las clases simuladas en los mocks
//        contenido.append("\n").append(verificarMock(variableInstanceName, methodName, parametros));

        return contenido.toString();
    }




    default String generateRetrunValue (Metodo metodo, Project project) {
        String tipoRetorno = metodo.getTipoRetorno();
        Clase claseRetorno = null;

        if(!isValidTypeReturn(tipoRetorno)){
            claseRetorno = project.getClass(tipoRetorno);
        }

        if (claseRetorno != null) {
            // Generar un objeto de la clase de retorno
            return generateClassObject(claseRetorno);
        } else {
            // Retornar un valor por defecto
            return getValorPorDefecto(tipoRetorno);
        }
    }



    default String generateClassObject(Clase clase) {
        StringBuilder contenido = new StringBuilder();

        contenido.append("new ").append(clase.getNombre()).append("(");

        if(clase.getConstructores() !=null && !clase.getConstructores().isEmpty() && clase.getConstructores().stream().anyMatch(Constructor::isNoneParam)){
            // Agregar los parámetros del constructor
//           Optional <Constructor> constructorOptional =  clase.getConstructores().stream().filter(constructor -> !constructor.isEmptyParameters()).findFirst();
            List<ParametroMetodo> parametros = clase.getConstructores().stream().filter(constructor -> !constructor.isEmptyParameters()).findFirst().get().getParametros();

            for (int i = 0; i < parametros.size(); i++) {
                ParametroMetodo parametro = parametros.get(i);
                contenido.append(parametro.getNombre());

                if (i < parametros.size() - 1) {
                    contenido.append(", ");
                }
            }
        }

        contenido.append(")");
        return contenido.toString();
    }


    default String verificarMock(String variableInstanceName, String methodName, List<ParametroMetodo> parametros) {
        StringBuilder contenido = new StringBuilder();

        contenido.append("Mockito.verify(").append(variableInstanceName).append(", Mockito.times(1)).").append(methodName).append("(");

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


    default String generarLlamadaMetodoMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros, Metodo metodo, Project project) {
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
        contenido.append(generateRetrunValue(metodo, project)).append(");");

        // Agregar el assert adecuado según el tipo de retorno
//        contenido.append("\n").append(getAssertType(metodo.getTipoRetorno(), null));

        // Verificar si se utilizaron las clases simuladas en los mocks
        contenido.append("\n").append(verificarMock(variableInstanceName, methodName, parametros));

        return contenido.toString();
    }


    default String getMokitoSetUpBeforeEach(boolean isAutoCloseable){
        StringBuilder mokitoSetUpBeforeEach = new StringBuilder("\n");
        if(isAutoCloseable) {
            mokitoSetUpBeforeEach.append("private AutoCloseable closeable;").append("\n").append("\n");
            mokitoSetUpBeforeEach.append("@BeforeEach").append("\n");
            mokitoSetUpBeforeEach.append("void setUp() {").append("\n");
            mokitoSetUpBeforeEach.append("closeable = MockitoAnnotations.openMocks(this);").append("\n").append("}").append("\n").append("\n");
            mokitoSetUpBeforeEach.append("@AfterEach").append("\n");
            mokitoSetUpBeforeEach.append("void tearDown() throws Exception {").append("\n");
            mokitoSetUpBeforeEach.append("  closeable.close();").append("\n").append("}").append("\n").append("\n");
        }else {
            mokitoSetUpBeforeEach.append("@BeforeEach").append("\n");
            mokitoSetUpBeforeEach.append("void setUp() {").append("\n");
            mokitoSetUpBeforeEach.append("MockitoAnnotations.openMocks(this);")
                    .append("\n").append("}").append("\n").append("\n");
        }
        return mokitoSetUpBeforeEach.toString();
    }



}

