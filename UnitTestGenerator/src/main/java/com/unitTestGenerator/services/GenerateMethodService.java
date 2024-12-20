package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenerateMethodService implements IBaseModel, MockitoWhen {

    private Project project;
    private static GenerateMethodService instance;
    private static final List<String> COMMON_METHODS = Arrays.asList("save", "findAllById", "findById", "delete", "deleteAll", "deleteById");

    private GenerateMethodService(){}

    public static GenerateMethodService getInstance(){
        if(instance == null){
            instance = new GenerateMethodService();
        }
        return instance;
    }

    private void setProject(Project project){
        this.project = project;
    }

    public String generateMethods( Clase clase, Project project){
        this.setProject(project);
        StringBuilder contex = new StringBuilder();

        if (clase.getTestMethod().toLowerCase().equals("all")){
            clase.getMetodos().forEach(metodo -> {
                contex.append(MethodParameterObject.getInstance().methodParameterObject(metodo, project, clase.getUseMock()));
                contex.append(obtenerContenidoMetodo(metodo, clase));
        });
        }else{
            for (Metodo metodo : clase.getMetodos()) {
                if (clase.getTestMethod().toLowerCase().equals(metodo.getNombre().toLowerCase())){
                    contex.append(MethodParameterObject.getInstance().methodParameterObject(metodo, project, clase.getUseMock()));
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
        String methodoTest = String.format("\n \t@Test\n \tpublic void test%s() {\n  \t%s \n }\n", methodName,content);
        return methodoTest;
    }

    private String generarContenidoMetodoPrueba(Metodo metodo, Clase clase) {
        StringBuilder contenido = new StringBuilder();
        String conten = clase.getUseMock()? generateContentMock(metodo, clase):generarContenidoSinMock(metodo);
        contenido.append(conten);
        return contenido.toString();
    }
//===============================================================================================================================================================================
    private String generarContenidoSinMock(Metodo metodo) {
        StringBuilder contenido = new StringBuilder();
        String methodName = metodo.getNombre();
        List<ParametroMetodo> parametros = metodo.getParametros();
        contenido.append(generateCallMethod(methodName, parametros));
        return contenido.toString();
    }

    private String generateCallMethod(String methodName, List<ParametroMetodo> parametros) {
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

    private Boolean methodIsVoid(String methodName, String className, Project project){

        if (methodName == null || methodName.isEmpty() || className == null || className.isEmpty() || project == null) {
            return false;
        }
        if (methodName != null && !methodName.isEmpty() && className != null && !className.isEmpty() && project != null) {
            Clase clasS = project.getClass(className);
            boolean result = this.isMethodVoidOrNoExistInClassOrExistInInterface(clasS.getMetodos(), methodName);
           return result;
        }
        return false;
    }


    private boolean isMethodVoidOrNoExistInClassOrExistInInterface(List<Metodo> methods, String methodName) {
        boolean result = false;
        boolean noExistInClassOrExistInInterface = true;
        boolean isMethodVoid = true;

        if (methods != null && !methods.isEmpty() && methodName != null && !methods.isEmpty()) {
            if (methods.stream().anyMatch(method -> method.getNombre().toLowerCase().equals(methodName.toLowerCase()))) {
                if (methods.stream().filter(method -> method.getNombre().toLowerCase().equals(methodName.toLowerCase()))
                        .anyMatch(method -> "void".equals(method.getTipoRetorno()))) {
                    return isMethodVoid;
                } else {
                    return result;
                }
            }
        }
        return result;
    }



//    private String generateContentMock2(Metodo method, Clase clase) {
//        StringBuilder content = new StringBuilder("\n");
//        String classNameCamelCase = stringEnsamble(clase.getNombre().substring(0, 1).toLowerCase(), clase.getNombre().substring(1));
//        String parametrosMethodTest =  this.addStringParametes(method.getParametros());
//        String testMethod = String.format("%s.%s(%s)" ,classNameCamelCase,method.getNombre(), parametrosMethodTest);
//        String verifyMock ="";
//
//        if(method.getInstanceMethodCalls() !=null && !method.getInstanceMethodCalls().isEmpty() ){
//            // Recorrer las instancias de métodos
//            for (InstanceMethodCall instanceMethodCall : method.getInstanceMethodCalls()) {
//
//                String[] parts = instanceMethodCall.getOperation().split("\\.");
//                String classNameInstanceMethodCall = parts[0];
//                String parametersMethodInstanceMethodCall = parts[1];
//                int indexPositionFirstParentesis = parametersMethodInstanceMethodCall.indexOf('(');
//                String methodNameParts = parametersMethodInstanceMethodCall.substring(0, indexPositionFirstParentesis);
//                String parametersParts = parametersMethodInstanceMethodCall.substring(indexPositionFirstParentesis + 1, parametersMethodInstanceMethodCall.length() - 1);
//
//                if (methodIsVoid(methodNameParts, classNameInstanceMethodCall, this.project)) {
//                    content.append("\t").append(this.generateCallMethodMockDoNothing(methodNameParts, classNameInstanceMethodCall, parametersParts));
//                } else {
//
//                    Clase clasS = project.getClass(classNameInstanceMethodCall);
//                    if (COMMON_METHODS.contains(methodNameParts)) {
//                        content.append("\t").append(this.generateCallMethodMock(instanceMethodCall.getOperation(), method, this.project, parametersParts));
//
//                        if (instanceMethodCall.getParametros().isEmpty()) {
//                            List<ParametroMetodo> parametersList = new ArrayList<>();
//                            if (parametersParts.contains(",")) {
//                                List<String> ListTempral = Arrays.asList(parametersParts.split(","));
//                                ListTempral.forEach(s -> parametersList.add(ParametroMetodo.builder().nombre(s).build()));
//                            } else {
//                                parametersList.add(ParametroMetodo.builder().nombre(parametersParts).build());
//                            }
//                            verifyMock = verificarMock(instanceMethodCall.getVariableInstace(), instanceMethodCall.getMethod(), parametersList);
//
//                        } else {
//                            verifyMock = verificarMock(instanceMethodCall.getVariableInstace(), instanceMethodCall.getMethod(), instanceMethodCall.getParametros());
//                        }
//
//                    } else {
//                        Metodo methodInClass = clasS.getMetodos().stream().filter(metodo -> method.getNombre().toLowerCase().equals(methodNameParts.toLowerCase())).findFirst().get();
//                        content.append("\t").append(this.generateCallMethodMock(instanceMethodCall.getOperation(), methodInClass, this.project, null));
//                    }
//                }
//            }
//        }
//
//        String resultValueName = String.format("%s%s" ,method.getNombre() ,"Result");
//       String operation =  String.format("%s %s = %s;" ,method.getTipoRetorno() ,resultValueName,testMethod);
//        content.append("\n").append("\t").append(operation).append("\n");
//        content.append("\n").append("\t").append(verifyMock);
//        content.append("\t").append(generateCallAssertType(method, this.project, resultValueName)).append("\n");
//        return content.toString();
//    }


    private String addStringParametes(List<ParametroMetodo> parametros){
        StringBuilder contenido = new StringBuilder();
        for (int i = 0; i < parametros.size(); i++) {
            ParametroMetodo parametro = parametros.get(i);
            contenido.append(parametro.getNombre());

            if (i < parametros.size() - 1) {
                contenido.append(", ");
            }
    }
        return contenido.toString();
}

private String methodParameterObject(Metodo method, Project project){

    List<Clase> clasesParameters = new ArrayList<>();
    if (method.getParametros() != null && !method.getParametros().isEmpty()) {
        method.getParametros().stream()
                .map(parametroMethod -> project.getClass(parametroMethod.getTipo()))
                .filter(foundClass -> foundClass != null)
                .forEach(clasesParameters::add);
    }
    //TODO: LLEVAR LA LISTA A sTRING PERO PRIMERO POR QUE SE CREO ESTE METODO
        return null;
}

//    public Clase getClass(String className, Project project){
//        Clase foundClass = null;
//        if(project.getClaseList() != null && !project.getClaseList().isEmpty() && className !=null && !className.equals("")){
//            for(Clase cla : project.getClaseList()){
//                if(cla.getNombre()!=null && cla.getNombre().equals(className)){
//                    foundClass = cla;
//                }
//            }
//        }
//        return foundClass;
//    }




    private String generateContentMock(Metodo method, Clase clase) {
        StringBuilder content = new StringBuilder("\n");
        String classNameCamelCase = StringUtils.uncapitalize(clase.getNombre()); // Use StringUtils if available
        String parametersMethodTest = this.addStringParametes(method.getParametros());
        String testMethodCall = String.format("%s.%s(%s)", classNameCamelCase, method.getNombre(), parametersMethodTest);

        // Process instance method calls if any
        String mockCalls = processInstanceMethodCalls(method);
        content.append(mockCalls);

        String resultValueName = method.getNombre() + "Result";
        String operation = String.format("%s %s = %s;", method.getTipoRetorno(), resultValueName, testMethodCall);
        content.append("\n\t").append(operation).append("\n");

        // Extract verifyMock logic to a dedicated method
        String verifyMock = extractVerifyMock(method);
        content.append("\n\t").append(verifyMock);

        content.append("\t").append(generateCallAssertType(method, this.project, resultValueName)).append("\n");
        return content.toString();
    }

    private String processInstanceMethodCalls(Metodo method) {
        if (method.getInstanceMethodCalls() == null || method.getInstanceMethodCalls().isEmpty()) {
            return "";
        }

        StringBuilder mockCalls = new StringBuilder();
        for (InstanceMethodCall instanceMethodCall : method.getInstanceMethodCalls()) {
            String[] parts = instanceMethodCall.getOperation().split("\\.");
            String classNameInstanceMethodCall = parts[0];
            String methodCallWithParams = parts[1];

            int parenthesisIndex = methodCallWithParams.indexOf('(');
            String methodName = methodCallWithParams.substring(0, parenthesisIndex);
            String parameters = methodCallWithParams.substring(parenthesisIndex + 1, methodCallWithParams.length() - 1);

            if (methodIsVoid(methodName, classNameInstanceMethodCall, this.project)) {
                mockCalls.append("\t").append(generateCallMethodMockDoNothing(methodName, classNameInstanceMethodCall, parameters));
            } else {
                Clase calledClass = project.getClass(classNameInstanceMethodCall);
                if (COMMON_METHODS.contains(methodName)) {
                    mockCalls.append("\t").append(generateCallMethodMock(instanceMethodCall.getOperation(), method, this.project, parameters));
                } else if (calledClass != null) { //Check if calledClass is not null
                    Optional<Metodo> methodInClass = calledClass.getMetodos().stream()
                            .filter(m -> m.getNombre().equalsIgnoreCase(methodName))
                            .findFirst();
                    if(methodInClass.isPresent()){
                        mockCalls.append("\t").append(generateCallMethodMock(instanceMethodCall.getOperation(), methodInClass.get(), this.project, null));
                    } else{
                        //Handle the case where the method is not found in the class. Throw an exception or log a warning.
                        System.err.println("Method " + methodName + " not found in class " + classNameInstanceMethodCall);
                        //Or throw an exception:
                        //throw new RuntimeException("Method not found");
                    }
                }
            }
        }
        return mockCalls.toString();
    }

    private String extractVerifyMock(Metodo method) {
        if (method.getInstanceMethodCalls() == null || method.getInstanceMethodCalls().isEmpty()) {
            return "";
        }

        return method.getInstanceMethodCalls().stream()
                .filter(instanceMethodCall -> COMMON_METHODS.contains(instanceMethodCall.getMethod()))
                .map(instanceMethodCall -> {
                    String parametersParts = instanceMethodCall.getOperation().substring(instanceMethodCall.getOperation().indexOf('(') + 1, instanceMethodCall.getOperation().length() - 1);
                    List<ParametroMetodo> parametersList = instanceMethodCall.getParametros().isEmpty()
                            ? Arrays.stream(parametersParts.split(","))
                            .filter(s -> !s.isEmpty()) // filter out empty strings
                            .map(s -> ParametroMetodo.builder().nombre(s.trim()).build()) // trim white space
                            .collect(Collectors.toList())
                            : instanceMethodCall.getParametros();
                    return verificarMock(instanceMethodCall.getVariableInstace(), instanceMethodCall.getMethod(), parametersList);
                })
                .collect(Collectors.joining());
    }

}
