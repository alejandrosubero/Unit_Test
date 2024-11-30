package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.*;

import java.util.ArrayList;
import java.util.List;

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
        String conten = clase.getUseMock()? generarContenidoMock(metodo, clase):generarContenidoSinMock(metodo);
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

    private String generarContenidoMock(Metodo metodo, Clase clase) {
        StringBuilder contenido = new StringBuilder();
        String classNameCamelCase = stringEnsamble(clase.getNombre().substring(0, 1).toLowerCase(), clase.getNombre().substring(1));
        String parametrosMethodTest =  this.addStringParametes(metodo.getParametros());
        String testMethod = String.format("%s.%s(%s)" ,classNameCamelCase,metodo.getNombre(), parametrosMethodTest);
//        String toMock = methodParameterObject( metodo,  project);
        contenido.append(this.generateCallMethodMock(testMethod, metodo, this.project));

        if(metodo.getInstanceMethodCalls() !=null && !metodo.getInstanceMethodCalls().isEmpty() ){

            // Recorrer las instancias de métodos
            for (InstanceMethodCall instanceMethodCall : metodo.getInstanceMethodCalls()) {
                    String methodName = instanceMethodCall.getMethod();
                    String variableInstanceName = instanceMethodCall.getVariableInstace();
                    List<ParametroMetodo> parametros = instanceMethodCall.getParametros();

                // Generar la llamada al método mock
                contenido.append(generarLlamadaMetodoMock(methodName, variableInstanceName, parametros,  metodo,  this.project));

            }
        }
        return contenido.toString();
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
