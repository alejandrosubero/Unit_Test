package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IGenerateVariable;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.core.AppProjectStarted;

public class GeneratedVariableService implements IGenerateVariable {

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

        content.append(this.generateVariable(clase.getNombre(), classNameCamelCase, true, clase.getUseMock())).append("\n");
        for (Variable variable : clase.getVariables()) {
            if (clase.getUseMock()) {
                content.append(this.generateVariable(variable.getTipo(), variable.getNombre(), false, clase.getUseMock())).append("\n");
            }
        }

        if (!clase.getUseMock()) {
                //TODO; ESTE METODO VA ANALISAR OTRAS CLASES PARA USAR EN LA GENERACION DE LAs variables de PRUEBA.
                //  @Autowired
            }

            return content.toString();
        }




}
