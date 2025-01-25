package com.unitTestGenerator.interfaces;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Constructor;
import com.unitTestGenerator.pojos.InstanceMethodCall;
import com.unitTestGenerator.pojos.ParametroMetodo;

import java.util.ArrayList;
import java.util.List;

public interface IClassObject {

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
        StringBuilder contenido = new StringBuilder();

        cClass.getNombre()
                ".builder()"
                 ".build();"

        InstanceMethodCall.builder().build();


        // genera objetos atravez de new
        return "";
    }

}
