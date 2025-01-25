package com.unitTestGenerator.interfaces;

import com.unitTestGenerator.pojos.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IClassObject extends IReturnType{


    public List<String> collectionListTypeNames = Arrays.asList("List","Set","ArrayList","LinkedList","Vector","Stack","HashSet","TreeSet","LinkedHashSet","Queue","LinkedList","PriorityQueue","Deque","ArrayDeque","LinkedList");
    public List<String> collectionMapTypeNames = Arrays.asList("Map","HashMap","TreeMap","LinkedHashMap","HashTable");

    default String generateNewClassObject(Clase clase) {
        StringBuilder contenido = new StringBuilder();

        contenido.append("new ").append(clase.getNombre()).append("(");

        if(clase.getConstructores() !=null && !clase.getConstructores().isEmpty() && clase.getConstructores().stream().anyMatch(Constructor::isNoneParam)){

            if(!clase.getConstructores().isEmpty()){
                List<ParametroMetodo> parametros = new ArrayList<>();
                for (Constructor constructor : clase.getConstructores()){
                    if( !constructor.isEmptyParameters()){
                        parametros.addAll(constructor.getParametros());
                        break;
                    }
                }

                for (int i = 0; i < parametros.size(); i++) {
                    ParametroMetodo parametro = parametros.get(i);
                    contenido.append(parametro.getNombre());

                    if (i < parametros.size() - 1) {
                        contenido.append(", ");
                    }
                }
            }
        }
        contenido.append(")");
        return contenido.toString();
    }


    default String generateNewClassObjectConstructoresIsEmpty(Clase clase) {
        StringBuilder contenido = new StringBuilder();
        if (clase.getConstructores() != null && !clase.getConstructores().isEmpty() && clase.getConstructores().stream().anyMatch(Constructor::isEmptyParameters)) {
            contenido.append("new ").append(clase.getNombre()).append("(");
            contenido.append(")");
        }else {
            contenido.append(this.generateNewClassObject(clase));
        }
        return contenido.toString();
    }



    default String buildObject(Clase cClass, Project project){
        StringBuilder content = new StringBuilder();
        content.append(cClass.getNombre()).append(".builder()");

        cClass.getVariables().forEach(variable -> {
            if(isValidTypeReturn(variable.getTipo())){
                content.append(".").append(variable.getNombre()).append(this.getDefaultValue(variable.getTipo())).append("\n");
            }
            if(variable.getTipo() != null && this.collectionListTypeNames.contains(variable.getTipo()) || this.collectionMapTypeNames.contains(variable.getTipo())){
                String objectClass = "";
                if (this.collectionListTypeNames.contains(variable.getTipo())) {
                    objectClass = extractListContent(variable.getTipo());
                }

                if (this.collectionMapTypeNames.contains(variable.getTipo())) {
                    String input = extractMapContent(variable.getTipo());
                    String key = getKey(input);
                    String value = getValue(input);
                    objectClass = value;
                }

                Clase classObjectVariable = project.getClass(objectClass);
                content.append(".").append(variable.getNombre()).append(this.buildObject(classObjectVariable,project)).append("\n");
                }
        });
        content.append(".build();");
        return content.toString();
    }






}
