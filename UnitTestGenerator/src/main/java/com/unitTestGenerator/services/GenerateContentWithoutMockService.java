package com.unitTestGenerator.services;

import com.unitTestGenerator.util.IBaseModel;
import com.unitTestGenerator.interfaces.IMethodServiceTools;
import com.unitTestGenerator.pojos.*;

import java.util.List;

public class GenerateContentWithoutMockService implements IMethodServiceTools, IBaseModel {

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
        String methodName = metodo.getNombre();
        List<ParametroMetodo> parametros = metodo.getParametros();
        Clase clase1 = this.project.getClass(parametros.get(0).getTipo());
        contenido.append(generateCallMethod(methodName, parametros));
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
