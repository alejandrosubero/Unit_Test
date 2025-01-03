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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateMethodService implements IBaseModel {

    private Project project;
    private static GenerateMethodService instance;
    private MockitoWhen mockitoWhen;
    private GenerateContentMockService generateContentMockService;
    private GenerateContentWithoutMockService contentWithoutMockService;

    private GenerateMethodService(){
            this.mockitoWhen = new MockitoWhen();
            this.generateContentMockService = GenerateContentMockService.getInstance();
            this.contentWithoutMockService = GenerateContentWithoutMockService.getInstance();
    }

    public static GenerateMethodService getInstance(){
        if(instance == null){
            instance = new GenerateMethodService();
        }
        return instance;
    }

    private void setProject(Project project){
       try {
           this.project = project;
           if(this.generateContentMockService != null) {
               this.generateContentMockService.setProject(this.project);
           }
           if(this.contentWithoutMockService != null) {
               this.contentWithoutMockService.setProject(this.project);
           }
       }catch (Exception e){
           System.err.println("Error in Generate Method Service: Method: setProject...");
           System.err.println("Error: "+ e.getMessage());
       }

    }

    public TestFileContent generateMethods( Clase clase, Project project,  TestFileContent fileContent ){

        this.setProject(project);

        StringBuilder contex = new StringBuilder();

        if (clase.getTestMethod().toLowerCase().equals("all")) {
            clase.getMetodos().forEach(metodo -> {
                fileContent.update(this.setContent(fileContent, clase, project, metodo));
            });
        } else {
            for (Metodo metodo : clase.getMetodos()) {
                if (clase.getTestMethod().toLowerCase().equals(metodo.getNombre().toLowerCase())) {
                    fileContent.update(this.setContent(fileContent, clase, project, metodo));
                }
            }
        }
        contex.append("\n");

        if (clase.getUseMock()) {
            fileContent.addVariable("\n");
            fileContent.addVariable(this.mockitoWhen.getMokitoSetUpBeforeEach(false));
        }
        return fileContent;
    }

    private TestFileContent setContent(TestFileContent fileContent,  Clase clase, Project project,Metodo metodo){
        String variable = MethodParameterObject.getInstance().methodParameterObject(metodo, project, clase.getUseMock());
        if (variable != null) {
            List<String> mockDeclarations = new ArrayList<>();
            String regex = "(?s)@Mock\\s+private\\s+\\w+\\s+\\w+;";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(variable);

            while (matcher.find()) {
                mockDeclarations.add(matcher.group().trim());
            }
            for (String declaration : mockDeclarations) {
                if (!fileContent.getTestsClassVariables().contains(declaration)) {
                    fileContent.addVariable(new StringBuffer("\t").append(declaration).toString());
                    fileContent.addImport(this.generateContentMockService.getImportInMockObject(declaration, project));
                }
            }
        }
        fileContent.addMethod(this.obtenerContenidoMetodo(metodo, clase, fileContent));
        return fileContent;
    }

    private  String obtenerContenidoMetodo(Metodo metodo, Clase clase, TestFileContent fileContent) {
        String methodName = String.format("%s%s", metodo.getNombre().substring(0, 1).toUpperCase(), metodo.getNombre().substring(1));
        String content = generarContenidoMetodoPrueba(metodo, clase, fileContent);
        String methodoTest = String.format("\n \t@Test\n \tpublic void test%s() {\n  \t%s \n }\n", methodName,content);
        return methodoTest;
    }

    private String generarContenidoMetodoPrueba(Metodo metodo, Clase clase, TestFileContent fileContent) {
        StringBuffer contenido = new StringBuffer();
        String conten = clase.getUseMock()? this.generateContentMockService.generateContentMock(metodo, clase, fileContent): this.contentWithoutMockService.generarContenidoSinMock(metodo,clase, fileContent);
        contenido.append(conten);
        return contenido.toString();
    }


//    private String generateContentMock(Metodo method, Clase clase, TestFileContent fileContent) {
//        StringBuilder content = new StringBuilder("\n");
//        String classNameCamelCase = StringUtils.uncapitalize(clase.getNombre()); // Use StringUtils if available
//        String parametersMethodTest = this.addStringParametes(method.getParametros());
//        String testMethodCall = String.format("%s.%s(%s)", classNameCamelCase, method.getNombre(), parametersMethodTest);
//
//        String mockCalls = processInstanceMethodCalls(method, fileContent);
//        content.append(mockCalls);
//
//        String resultValueName = method.getNombre() + "Result";
//        String operation = String.format("%s %s = %s;", method.getTipoRetorno(), resultValueName, testMethodCall);
//        content.append("\n\t").append(operation).append("\n");
//        fileContent.addImport(this.getImportInObject(method.getTipoRetorno(), project));
//        String verifyMock = extractVerifyMock(method);
//        content.append("\n\t").append(verifyMock);
//
//        content.append("\t").append( this.mockitoWhen.generateCallAssertType(method, this.project, resultValueName)).append("\n");
//        return content.toString();
//    }
//
//    private String processInstanceMethodCalls(Metodo method, TestFileContent fileContent) {
//        if (method.getInstanceMethodCalls() == null || method.getInstanceMethodCalls().isEmpty()) {
//            return "";
//        }
//
//        StringBuffer mockCalls = new StringBuffer();
//        for (InstanceMethodCall instanceMethodCall : method.getInstanceMethodCalls()) {
//            String[] parts = instanceMethodCall.getOperation().split("\\.");
//            String classNameInstanceMethodCall = parts[0];
//            String methodCallWithParams = parts[1];
//
//            int parenthesisIndex = methodCallWithParams.indexOf('(');
//            String methodName = methodCallWithParams.substring(0, parenthesisIndex);
//            String parameters = methodCallWithParams.substring(parenthesisIndex + 1, methodCallWithParams.length() - 1);
//
//            if (methodIsVoid(methodName, classNameInstanceMethodCall, this.project)) {
//                mockCalls.append("\t").append( this.mockitoWhen.generateCallMethodMockDoNothing(methodName, classNameInstanceMethodCall, parameters));
//            } else {
//                Clase calledClass = project.getClass(classNameInstanceMethodCall);
//                if(instanceMethodCall.getParametros() !=null && !instanceMethodCall.getParametros().isEmpty()){
//                        instanceMethodCall.getParametros().forEach(parametroMetodo ->
//                                fileContent.addVariable(
//                                        GeneratedVariableService.getInstance().generateVariable(
//                                                parametroMetodo.getTipo(),
//                                                parametroMetodo.getNombre(),
//                                                false, true))
//                        );
//                    }
//
//                if (COMMON_METHODS.contains(methodName)) {
//                    mockCalls.append("\t").append( this.mockitoWhen.generateCallMethodMock(instanceMethodCall.getOperation(), method, this.project, null, fileContent,methodName));
//                } else if (calledClass != null) { //Check if calledClass is not null
//                    Optional<Metodo> methodInClass = calledClass.getMetodos().stream()
//                            .filter(m -> m.getNombre().equalsIgnoreCase(methodName))
//                            .findFirst();
//                    if(methodInClass.isPresent()){
//                        mockCalls.append("\t").append( this.mockitoWhen.generateCallMethodMock(instanceMethodCall.getOperation(), methodInClass.get(), this.project, null, fileContent, null));
//                    } else{
//                        System.err.println("Method " + methodName + " not found in class " + classNameInstanceMethodCall);
//                    }
//                }
//            }
//        }
//        return mockCalls.toString();
//    }
//
//    private String extractVerifyMock(Metodo method) {
//        if (method.getInstanceMethodCalls() == null || method.getInstanceMethodCalls().isEmpty()) {
//            return "";
//        }
//
//        return method.getInstanceMethodCalls().stream()
//                .filter(instanceMethodCall -> COMMON_METHODS.contains(instanceMethodCall.getMethod()))
//                .map(instanceMethodCall -> {
//                    String parametersParts = instanceMethodCall.getOperation().substring(instanceMethodCall.getOperation().indexOf('(') + 1, instanceMethodCall.getOperation().length() - 1);
//                    List<ParametroMetodo> parametersList = instanceMethodCall.getParametros().isEmpty()
//                            ? Arrays.stream(parametersParts.split(","))
//                            .filter(s -> !s.isEmpty()) // filter out empty strings
//                            .map(s -> ParametroMetodo.builder().nombre(s.trim()).build()) // trim white space
//                            .collect(Collectors.toList())
//                            : instanceMethodCall.getParametros();
//                    return  this.mockitoWhen.verificarMock(instanceMethodCall.getVariableInstace(), instanceMethodCall.getMethod(), parametersList);
//                })
//                .collect(Collectors.joining());
//    }
//
//    private String getImportInMockObject(String input, Project project){
//
//        String importName ="";
//        try {
//            String regex = "\\bprivate\\b\\s+(\\w+)";
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(input);
//
//            if (matcher.find()) {
//                String className = matcher.group(1);
//                Clase clase = project.getClass(className);
//                if(clase != null && clase.getNombre() !=null && clase.getPaquete() != null){
//                    importName = String.format("\nimport %s.%s;",clase.getPaquete(), clase.getNombre());
//                }else if(COMMON_IMPORTS.contains(className)){
//                    importName = String.format("\nimport %s.%s;","java.util", className);
//                }
//            }
//            return importName;
//        }catch (Exception e){
//            e.printStackTrace();
//            return importName;
//        }
//    }
//

    //tools:

//    private String getImportInObject(String input, Project project){
//        String importName ="";
//        try {
//            String className = input;
//            Clase clase = project.getClass(className);
//            if(clase != null && clase.getNombre() !=null && clase.getPaquete() != null){
//                importName = String.format("\nimport %s.%s;",clase.getPaquete(), clase.getNombre());
//            }
//            return importName;
//        }catch (Exception e){
//            e.printStackTrace();
//            return importName;
//        }
//    }
//
//    private Boolean methodIsVoid(String methodName, String className, Project project){
//
//        if (methodName == null || methodName.isEmpty() || className == null || className.isEmpty() || project == null) {
//            return false;
//        }
//        if (methodName != null && !methodName.isEmpty() && className != null && !className.isEmpty() && project != null) {
//            Clase clasS = project.getClass(className);
//            boolean result = this.isMethodVoidOrNoExistInClassOrExistInInterface(clasS.getMetodos(), methodName);
//            return result;
//        }
//        return false;
//    }
//
//    private boolean isMethodVoidOrNoExistInClassOrExistInInterface(List<Metodo> methods, String methodName) {
//        boolean result = false;
//        boolean noExistInClassOrExistInInterface = true;
//        boolean isMethodVoid = true;
//
//        if (methods != null && !methods.isEmpty() && methodName != null && !methods.isEmpty()) {
//            if (methods.stream().anyMatch(method -> method.getNombre().toLowerCase().equals(methodName.toLowerCase()))) {
//                if (methods.stream().filter(method -> method.getNombre().toLowerCase().equals(methodName.toLowerCase()))
//                        .anyMatch(method -> "void".equals(method.getTipoRetorno()))) {
//                    return isMethodVoid;
//                } else {
//                    return result;
//                }
//            }
//        }
//        return result;
//    }
//
//    private String addStringParametes(List<ParametroMetodo> parametros) {
//        StringBuilder contenido = new StringBuilder();
//        for (int i = 0; i < parametros.size(); i++) {
//            ParametroMetodo parametro = parametros.get(i);
//            contenido.append(parametro.getNombre());
//
//            if (i < parametros.size() - 1) {
//                contenido.append(", ");
//            }
//        }
//        return contenido.toString();
//    }
//
//    private String methodParameterObject(Metodo method, Project project) {
//
//        List<Clase> clasesParameters = new ArrayList<>();
//        if (method.getParametros() != null && !method.getParametros().isEmpty()) {
//            method.getParametros().stream()
//                    .map(parametroMethod -> project.getClass(parametroMethod.getTipo()))
//                    .filter(foundClass -> foundClass != null)
//                    .forEach(clasesParameters::add);
//        }
//        //TODO: LLEVAR LA LISTA A sTRING PERO PRIMERO POR QUE SE CREO ESTE METODO
//        return null;
//    }


}
