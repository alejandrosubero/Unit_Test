package com.unitTestGenerator.interfaces;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Constructor;
import com.unitTestGenerator.pojos.InstanceMethodCall;
import com.unitTestGenerator.pojos.ParametroMetodo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IClassObject extends IReturnType{


    public List<String> elements = Arrays.asList("List","Set","ArrayList","LinkedList","Vector","Stack","HashSet","TreeSet","LinkedHashSet","Queue","LinkedList","PriorityQueue","Deque","ArrayDeque","LinkedList","Map","HashMap","TreeMap","LinkedHashMap","HashTable");

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

    default String buildObject(Clase cClass){
        StringBuilder content = new StringBuilder();
        content.append(cClass.getNombre()).append(".builder()");
        cClass.getVariables().forEach(variable -> {
            if(isValidTypeReturn(variable.getTipo())){
                content.append(".").append(variable.getTipo()).append(this.getDefaultValue(variable.getTipo())).append("\n");
            }//TODO: ANALIZE IF isValidTypeReturn IS FALSE (IS OBJECT OR List)
            if(variable.getTipo() != null && variable.getTipo().contains("List") || variable.getTipo().contains("Set")){

            }
            //TODO: IN THE CASE IS A LIST WE NEED EXTRACT THE OBJECT IN THE LIST AND CREATE THE OBJECT LIAKE HERE ADD TO LIST SO ON...
        });
        content.append(".build();");

        return content.toString();
    }






}
