package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateMethodService implements IBaseModel, MockitoWhen {

    private Project project;
    private static GenerateMethodService instance;

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
        String methodoTest = String.format("\n@Test\npublic void test%s() {\n  %s \n }\n", methodName,content);
        return methodoTest;
    }

    private String generarContenidoMetodoPrueba(Metodo metodo, Clase clase) {
        StringBuilder contenido = new StringBuilder();
        String conten = clase.getUseMock()? generateContentMock(metodo, clase):generarContenidoSinMock(metodo);
        contenido.append(conten);
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


    private String generateContentMock(Metodo method, Clase clase) {
        StringBuilder content = new StringBuilder();
        String classNameCamelCase = stringEnsamble(clase.getNombre().substring(0, 1).toLowerCase(), clase.getNombre().substring(1));
        String parametrosMethodTest =  this.addStringParametes(method.getParametros());
        String testMethod = String.format("%s.%s(%s)" ,classNameCamelCase,method.getNombre(), parametrosMethodTest);
//        String toMock = methodParameterObject( metodo,  project);
        content.append(this.generateCallMethodMock(testMethod, method, this.project, null));

        if(method.getInstanceMethodCalls() !=null && !method.getInstanceMethodCalls().isEmpty() ){
            // Recorrer las instancias de métodos
            for (InstanceMethodCall instanceMethodCall : method.getInstanceMethodCalls()) {
                    String methodName = instanceMethodCall.getMethod();
                    String variableInstanceName = instanceMethodCall.getVariableInstace();
                    List<ParametroMetodo> parametros = instanceMethodCall.getParametros();
//                ..fallloooooooooooo
                // Generar la llamada al metodos internos del metodo en operacion mock
                content.append("\n");
                    if(parametros == null || parametros.isEmpty()){
                        String[] parts = instanceMethodCall.getOperation().split("\\.");
                        String classNameInstanceMethodCall = parts[0];
                        String parametersMethodInstanceMethodCall = parts[1];
                        int indexPositionFirstParentesis = parametersMethodInstanceMethodCall.indexOf('(');
                        String methodNameParts = parametersMethodInstanceMethodCall.substring(0, indexPositionFirstParentesis);
                        String parametersParts = parametersMethodInstanceMethodCall.substring(indexPositionFirstParentesis + 1, parametersMethodInstanceMethodCall.length() - 1);

                        if(methodIsVoid(methodNameParts, classNameInstanceMethodCall,  this.project)){
                            content.append(this.generateCallMethodMockDoNothing(methodNameParts, classNameInstanceMethodCall,parametersParts));
                        }else {
                            Clase clasS = project.getClass(classNameInstanceMethodCall);

                            if (methodNameParts.equals("save") ||
                                    methodNameParts.equals("findAllById") ||
                                    methodNameParts.equals("findById") ||
                                    methodNameParts.equals("delete") ||
                                    methodNameParts.equals("deleteAll") ||
                                    methodNameParts.equals("deleteById")
                            ) {
                                content.append(this.generateCallMethodMock(instanceMethodCall.getOperation(), method, this.project, parametersParts));
                            }else {
                                Metodo methodInClass = clasS.getMetodos().stream().filter(metodo -> method.getNombre().toLowerCase().equals(methodNameParts.toLowerCase())).findFirst().get();
                                content.append(this.generateCallMethodMock(instanceMethodCall.getOperation(), methodInClass, this.project, null));
                            }
                        }
                    }else{
                        content.append(generarLlamadaMetodoMock(methodName, variableInstanceName, parametros,  method,  this.project));
                    }
            }
        }

        // operacion de prueba llamada al metodo y obtener resultado.



        content.append(generateCallAssertType(method, this.project));
        return content.toString();
    }


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
//        for (ParametroMetodo parametroMethod : method.getParametros()) {
//            Clase found = project.getClass(parametroMethod.getTipo());
//            if (found != null) {
//                clasesParameters.add(found);
//            }
//        }

//        method.getParametros().forEach(parametroMethod -> {
//            Clase found = project.getClass(parametroMethod.getTipo());
//            if (found != null) {
//                clasesParameters.add(found);
//            }
//        });

        method.getParametros().stream()
                .map(parametroMethod -> project.getClass(parametroMethod.getTipo()))
                .filter(foundClass -> foundClass != null)
                .forEach(clasesParameters::add);
    }




        return null;
}

    public Clase getClass(String className, Project project){
        Clase foundClass = null;
        if(project.getClaseList() != null && !project.getClaseList().isEmpty() && className !=null && !className.equals("")){
            for(Clase cla : project.getClaseList()){
                if(cla.getNombre()!=null && cla.getNombre().equals(className)){
                    foundClass = cla;
                }
            }

        }
        return foundClass;
    }


}
