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



    //TODO; ESTE METODO TAMBIEN VA ANALISAR OTRAS CLASES PARA USAR EN LA GENERACION DE LA PRUEBA.

    public String generateVariable(Clase clase) {
        StringBuilder content = new StringBuilder("\n");

        String classNameCamelCase = stringEnsamble(clase.getNombre().substring(0, 1).toLowerCase(), clase.getNombre().substring(1));
        content.append(generateVariable(clase.getNombre(), classNameCamelCase, true)).append("\n");

        for (Variable variable : clase.getVariables()) {
            if(variable.getMock()){
                content.append(generateVariable(variable.getTipo(), variable.getNombre(), false)).append("\n");
            }
        }
        return content.toString();
    }

    private  String generateVariable(String type, String name, boolean isClass) {
        StringBuilder content = new StringBuilder();

      if(type != null && name != null) {
          String nameVariable = stringEnsamble(name.substring(0, 1).toLowerCase(), name.substring(1));
          String text = stringEnsamble("private " + type, " ", nameVariable, ";");
          String notation = isClass ? "@InjectMocks" : "@Mock";
          content.append("\t").append(notation).append(("\n"));
          content.append("\t").append(text).append(("\n"));
      }
        return content.toString();
    }

}
