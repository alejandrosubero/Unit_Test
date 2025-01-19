package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IManageMavenGadleAppProperties;
import com.unitTestGenerator.util.IBaseModel;
import com.unitTestGenerator.interfaces.IMethodServiceTools;
import com.unitTestGenerator.pojos.*;

import java.util.ArrayList;
import java.util.List;

public class GenerateContentWithoutMockService implements IMethodServiceTools, IBaseModel, IManageMavenGadleAppProperties {

    public static GenerateContentWithoutMockService instance;
    private Project project;

    private GenerateContentWithoutMockService() {
    }

    public static GenerateContentWithoutMockService getInstance(){
        if(instance == null){
            instance = new GenerateContentWithoutMockService();
        }
        return instance;
    }

    public void setProject(Project project){
        this.project = project;
    }


    public String generarContenidoSinMock(Metodo metodo, Clase clase, TestFileContent fileContent) {

        StringBuilder contenido = new StringBuilder();
        if(metodo != null  && metodo.getNombre() != null){
            String methodName = metodo.getNombre();

            List<Clase> parametersClassList = new ArrayList<>();
            if( metodo.getParametros() !=null && !metodo.getParametros().isEmpty()){
                metodo.getParametros().forEach(parametroMetodo -> parametersClassList.add(this.project.getClass(parametroMetodo.getTipo())));
            }

            // ver si la clases de la lista de los parametros objetos tiene el patron build **** esto se hace en el analisis de las clases.




            //si lo tiene usarlo; si no lo tiene preguntar si quiere agregarlo:

//            si es si ; preguntar si desea agregar a la clase el patter build o usar lombox (en este punto una previa evaluacion si se cuenta con la dependencia).
            // si tiene la dependencia seria para indicar puesto que ya tiene la dependency en el proyecto.

                this.addLombokDependency(project);

            contenido.append(generateCallMethod(methodName, metodo.getParametros()));
        }

        return contenido.toString();
    }

    public String generateCallMethod(String methodName, List<ParametroMetodo> parametros) {
        StringBuilder contenido = new StringBuilder();
        contenido.append(methodName).append("(");

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
