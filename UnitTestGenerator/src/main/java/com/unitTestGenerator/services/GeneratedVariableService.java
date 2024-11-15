package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Variable;

public class GeneratedVariableService implements IBaseModel {

    private static GeneratedVariableService instance = null;

    private GeneratedVariableService(){
    }

    public static GeneratedVariableService getInstance(){
        if(instance == null ){
            instance = new GeneratedVariableService();
        }
        return instance;
    }

    //TODO: NO SE ESTA GENERANDO LA VARIABLE DE LA CLASE A TESTEAR.
    //TODO: TENDRA QUE RESCIBIR UNA CLASE PARA TRABAJAR DENTRO DE LA CLASE NUEVA LA VARIABLE.
    //TODO; ESTE METODO TAMBIEN VA ANALISAR OTRAS CLASES PARA USAR EN LA GENERACION DE LA PRUEBA.
    private  String generarPruebaVariable(Clase clase) {
        StringBuilder content = new StringBuilder("\n");

        String classNameCamelCase = stringEnsamble(clase.getNombre().substring(0, 1).toLowerCase(), clase.getNombre().substring(1));
        content.append(generateVariable(clase.getNombre(), classNameCamelCase)).append("\n");

        for (Variable variable : clase.getVariables()) {
            if(variable.getMock()){
                content.append(generateVariable(variable.getTipo(), variable.getNombre())).append("\n");
            }
        }
        return null;
    }

    //TODO:ANOTATION?
    private  String generateVariable(String type, String name) {
        String anotation = "\n@Test\n";
        String nameVariable = stringEnsamble(  name.substring(0, 1).toLowerCase(), name.substring(1), "; \n");
        String text = stringEnsamble( "\n pivate "+ type," ", nameVariable, "; \n");
        return text;
    }




}
