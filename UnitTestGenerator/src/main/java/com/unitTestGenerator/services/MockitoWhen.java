package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockitoWhen implements ReturnType, IBaseModel {

    public MockitoWhen() {
    }


    private String optionalRetrunValue(String testMethod, String tipoRetorno,  TestFileContent fileContent, Metodo metodo){

        StringBuilder optionalRetrunValue = new StringBuilder();
        String typeOfOptionalReturn="";
        String typeOfOptionalName ="";
        String typeOfOptional ="null";

        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(tipoRetorno);

        if (matcher.find()) {
            typeOfOptional = matcher.group(1);
        }

        if(!typeOfOptional.equals("null")){
            typeOfOptionalName =  typeOfOptional.toLowerCase()+"Entity";
            typeOfOptionalReturn = "optional"+typeOfOptional;
            fileContent.addVariable(GeneratedVariableService.getInstance().generateVariable(
                    typeOfOptional,
                    typeOfOptionalName,
                    false, true));
        }
        optionalRetrunValue.append("\n")
                .append(tipoRetorno).append(" ")
                .append(typeOfOptionalReturn).append(" = ")
                .append("Optional.of(").append(typeOfOptionalName).append(");")
                .append("\n");

        optionalRetrunValue.append("\tMockito.when(").append(testMethod).append(")").append(".thenReturn(");
        optionalRetrunValue.append(typeOfOptionalReturn).append(");");

        return optionalRetrunValue.toString();

    }

    public String generateCallMethodMock(String testMethod, Metodo metodo, Project project, String retrunValue, TestFileContent fileContent, String methodName) {
        StringBuilder content = new StringBuilder();
        String typeOfOptionalReturn="";
        String tipoRetorno = "";
/**
 1)   verificar si esta en la lista de metodos
  2)     luego verificar el conteido :
    ejemplo:    Optional <Empleado> empleadoById  = empleadorepository.findById(id);
                    if(empleadoById.isPresent()){
                        return empleadoById.get();
*/

        if(metodo != null && metodo.getTipoRetorno() != null){
             tipoRetorno = metodo.getTipoRetorno();
        }

        if (retrunValue == null && tipoRetorno !=null && tipoRetorno.contains("Optional")){
            content.append( this.optionalRetrunValue(testMethod, tipoRetorno, fileContent, metodo));

        } else if (methodName != null && metodo.getContenido().contains("Optional") && !tipoRetorno.contains("Optional")) {

            if (methodName.equals("findById")){
                tipoRetorno ="Optional<"+metodo.getTipoRetorno()+">";

                Pattern patron = Pattern.compile("\\((.*?)\\)");
                Matcher matcher = patron.matcher(testMethod);
                String data ="";
                if (matcher.find()) {
                     data = matcher.group(1);
                }

                String resultado = testMethod.replace(data, "Mockito.any(Long.class)");

//                Long id = 1L;
//                empleadoEntity.setId(id);
            // Mockito.when(empleadorepository.findById(Mockito.any(Long.class))).thenReturn(optionalEmpleado);

                content.append( this.optionalRetrunValue(resultado, tipoRetorno, fileContent, metodo));
            }

        }else {
            content.append("\tMockito.when(").append(testMethod).append(")").append(".thenReturn(");
            String valueReturn = retrunValue != null ? retrunValue : generateRetrunValue(metodo, project);
            content.append(valueReturn).append(");");
        }


        return content.toString();
    }


    public String generateCallMethodMockDoNothing( String methodNameParts, String classNameCamelCase, String parametrosMethodTest) {
        return new StringBuilder("Mockito.doNothing().when(").append(classNameCamelCase).append(")").append(String.format(".%s(%s);" ,methodNameParts, parametrosMethodTest)).toString();
    }

    public String generateCallAssertType(Metodo metodo, Project project, String result) {
        StringBuilder contex = new StringBuilder();
        String valueReturn = generateRetrunValue(metodo, project);
        contex.append("\n").append("\t").append(getAssertType(metodo.getTipoRetorno(), valueReturn, result));
        return contex.toString();
    }


//    default String generateCallVerificarMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros) {
//        StringBuilder contenido = new StringBuilder();
//        // Verificar si se utilizaron las clases simuladas en los mocks
//        contenido.append("\n").append(verificarMock(variableInstanceName, methodName, parametros));
//        return contenido.toString();
//    }

//    default String generateCallMethodMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros, Metodo metodo, Project project) {
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
//        contenido.append(generateRetrunValue(metodo, project)).append(");");
//        String resultValueName = String.format("%s%s;" ,metodo.getNombre() ,"Result");
//        // Agregar el assert adecuado según el tipo de retorno
//        contenido.append("\n").append(getAssertType(metodo.getTipoRetorno(), null, resultValueName));
//
//        // Verificar si se utilizaron las clases simuladas en los mocks
////        contenido.append("\n").append(verificarMock(variableInstanceName, methodName, parametros));
//
//        return contenido.toString();
//    }


    public String generateRetrunValue (Metodo metodo, Project project) {
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



    public String generateClassObject(Clase clase) {
        StringBuilder contenido = new StringBuilder();

        contenido.append("new ").append(clase.getNombre()).append("(");

        if(clase.getConstructores() !=null && !clase.getConstructores().isEmpty() && clase.getConstructores().stream().anyMatch(Constructor::isNoneParam)){

            if(!clase.getConstructores().isEmpty()){
                List<ParametroMetodo> parametros = new ArrayList<>();
                for (Constructor constructor : clase.getConstructores()){
                    if( !constructor.isEmptyParameters()){
                        parametros.addAll(constructor.getParametros());
                        break;
                    }
                }

                for (int i = 0; i < parametros.size(); i++) {
                    ParametroMetodo parametro = parametros.get(i);
                    contenido.append(parametro.getNombre());

                    if (i < parametros.size() - 1) {
                        contenido.append(", ");
                    }
                }
            }
        }

        contenido.append(")");
        return contenido.toString();
    }


    public String verificarMock(String variableInstanceName, String methodName, List<ParametroMetodo> parametros) {
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


//    default String generarLlamadaMetodoMock(String methodName, String variableInstanceName, List<ParametroMetodo> parametros, Metodo metodo, Project project) {
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
//        contenido.append(generateRetrunValue(metodo, project)).append(");");
//
//        // Agregar el assert adecuado según el tipo de retorno
////        contenido.append("\n").append(getAssertType(metodo.getTipoRetorno(), null));
//
//        // Verificar si se utilizaron las clases simuladas en los mocks
////        contenido.append("\n").append(verificarMock(variableInstanceName, methodName, parametros));
//
//        return contenido.toString();
//    }


    public String getMokitoSetUpBeforeEach(boolean isAutoCloseable){
        StringBuilder mokitoSetUpBeforeEach = new StringBuilder("\n");
        if(isAutoCloseable) {
            mokitoSetUpBeforeEach.append("\t").append("private AutoCloseable closeable;").append("\n").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t@BeforeEach").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\tpublic void setUp() {").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t\tcloseable = MockitoAnnotations.openMocks(this);").append("\n").append("}").append("\n").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t@AfterEach").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\tpublic void tearDown() throws Exception {").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t\tcloseable.close();").append("\n").append("}").append("\n").append("\n");
        }else {
            mokitoSetUpBeforeEach.append("\t").append("\t@BeforeEach").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\tpublic void setUp() {").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t\tMockitoAnnotations.openMocks(this);").append("\n").append("\t").append("}").append("\n").append("\n");
        }
        return mokitoSetUpBeforeEach.toString();
    }



}

