package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.util.AppProjectStarted;

public class GeneratedVariableService implements IBaseModel {

    private static GeneratedVariableService instance = null;
    private Project project;

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

        this.project = AppProjectStarted.getInstance().getProject();
        String classNameCamelCase = stringEnsamble(clase.getNombre().substring(0, 1).toLowerCase(), clase.getNombre().substring(1));

        content.append(generateVariable(clase.getNombre(), classNameCamelCase, true, clase.getUseMock())).append("\n");
        for (Variable variable : clase.getVariables()) {
            if (clase.getUseMock()) {
                content.append(generateVariable(variable.getTipo(), variable.getNombre(), false, clase.getUseMock())).append("\n");
            }
        }

        if (!clase.getUseMock()) {
                //TODO; ESTE METODO VA ANALISAR OTRAS CLASES PARA USAR EN LA GENERACION DE LAs variables de PRUEBA.
                //  @Autowired
            }

            return content.toString();
        }



    private  String generateVariable(String type, String name, boolean isClass, boolean useMock) {
        StringBuilder content = new StringBuilder();

        if(type != null && name != null) {
            String notation = useMock ? (isClass ? "@InjectMocks" : "@Mock") : (isClass ? "@Autowired" : "");
            String nameVariable = stringEnsamble(name.substring(0, 1).toLowerCase(), name.substring(1));
//            String variableDeclaration = stringEnsamble("private " + type, " ", nameVariable, ";");
            String variableDeclaration = String.format("private %s %s;", type, nameVariable);
            if (!notation.isEmpty()) {
                content.append("\t").append(notation).append("\n");
            }
            content.append("\t").append(variableDeclaration).append("\n");
        }
        return content.toString();
    }



}
