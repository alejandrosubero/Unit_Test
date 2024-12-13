package com.unitTestGenerator.services;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Project;

import java.util.*;
import java.util.stream.Collectors;

public class MethodParameterObject implements IGenerateVariable, MockitoWhen {

    private Map<String, Clase> claseDirectory = new HashMap<>();
    private static MethodParameterObject instance;

    private MethodParameterObject(){}

    public static MethodParameterObject getInstance(){
        if(instance == null){
            instance = new MethodParameterObject();
        }
        return instance;
    }


    public String methodParameterObject(Metodo method, Project project, boolean isMock ){
        String response = "";
        if (isMock) {
            response = this.getMockOject(method);
        }else {
            List<Clase> clasesParameters = getParameterClassList( method, project);
            StringBuffer parametes = new StringBuffer();
            if(clasesParameters != null && !clasesParameters.isEmpty()){
                clasesParameters.forEach(clase ->
                        parametes.append(this.generateVariable(clase.getNombre(), clase.getNombre().toLowerCase(),true, false))
                );
              response = parametes.toString();
            }
        }
        return response;
    }

    public String getImportMethodParameterObject(Clase clase, Project project){
        List<Clase> clasesParameters = null;
         String response ="";
        Metodo method = null;
        for (Metodo metodo : clase.getMetodos()) {
            if (clase.getTestMethod().toLowerCase().equals(metodo.getNombre().toLowerCase())){
                method = metodo;
            }
        }

        if(method != null){
            StringBuilder importMethodParameterObject = new StringBuilder();
            clasesParameters = getParameterClassList( method, project);
            clasesParameters.stream().forEach(clase1 -> {
                importMethodParameterObject.append( this.stringEnsamble("import ", clase1.getPaquete(), ".",clase1.getNombre())).append(";").append("\n");
            });
            response = importMethodParameterObject.toString();
        }
        return response;
    }

    private String getMockOject(Metodo method){
            StringBuilder contentWentisMock = new StringBuilder();
            if (method.getParametros() != null && !method.getParametros().isEmpty()) {
                method.getParametros().forEach(parametroMethod -> {
                    contentWentisMock.append(
                            this.generateVariable(
                                    parametroMethod.getTipo(),
                                    parametroMethod.getNombre(),
                                    false, true)
                    ).append("\n");
                });
            }
            contentWentisMock.append(this.getMokitoSetUpBeforeEach(false));
        return contentWentisMock.toString();
    }

    public List<Clase> getParameterClassList(Metodo method, Project project) {
        List<Clase> clasesParameters = new ArrayList<>();
        this.claseDirectory = new HashMap<>();
        if (method.getParametros() != null && !method.getParametros().isEmpty()) {
            method.getParametros().forEach(parametroMethod -> {
                Clase found = project.getClass(parametroMethod.getTipo());
                if (found != null) {
                    clasesParameters.add(found);
                    this.claseDirectory.put(found.getNombre(), found);
                }
            });
        }
        return clasesParameters;
    }


}


//                response = clasesParameters.stream()
//                        .map(clase -> clase.getNombre() + " " + clase.getNombre().toLowerCase())
//                        .collect(Collectors.joining(";"));

//                Iterator<Clase> iterator = clasesParameters.iterator();
//                while (iterator.hasNext()) {
//                    Clase clase = iterator.next();
//                    parametes.append(clase.getNombre()).append(" ").append(clase.getNombre().toLowerCase());
//                    if(iterator.hasNext()){
//                        parametes.append(";");
//                    }
//                }