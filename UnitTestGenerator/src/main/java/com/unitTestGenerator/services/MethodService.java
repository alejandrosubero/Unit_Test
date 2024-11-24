package com.unitTestGenerator.services;

import com.unitTestGenerator.pojos.InstanceMethodCall;
import com.unitTestGenerator.pojos.ParametroMetodo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodService {

    private static MethodService instance;
    private MethodService() {
    }

    public static MethodService getInstance(){
        if(instance == null){
            instance = new MethodService();
        }
        return instance;
    }


    //    encontrar las operaciones que se efectúan  InstanceMethodCall
    public  List<InstanceMethodCall> findOperationPerformed(String contentMethod, String variable) {

        List<InstanceMethodCall> operationsInstanceMethodCall = null;

        if(contentMethod != null && !contentMethod.equals("") && variable != null && !variable.equals("") &&
                contentMethod.contains(variable)) {

            List<ParametroMetodo> parameters = new ArrayList<>();
            operationsInstanceMethodCall = new ArrayList<>();

            Pattern patronOperaciones = Pattern.compile("\\b" + variable + "\\.(\\w+)\\((.*?)\\)");
            Matcher matcherOperaciones = patronOperaciones.matcher(contentMethod);

            while (matcherOperaciones.find()) {
                String nombreMetodo = matcherOperaciones.group(1);
                String parametros = matcherOperaciones.group(2);

                String operation = String.format("%s.%s(%s)",
                        variable != null ? variable : "",
                        nombreMetodo != null ? nombreMetodo : "",
                        parametros != null ? parametros : ""
                );

                List<String> parametrosList = new ArrayList<>(Arrays.asList(parametros.split("\\s*,\\s*")));

                for (String param : parametrosList) {
                    param = param.trim();
                    Pattern patterVariable = Pattern.compile("(\\w+)\\s+" + param + "\\s*=");
                    Matcher matcherVariable = patterVariable.matcher(contentMethod);
                    if (matcherVariable.find()) {
                        String typeVariable = matcherVariable.group(1);
                        parameters.add(ParametroMetodo.builder().nombre(param).tipo(typeVariable).build());
                    }
                }

                operationsInstanceMethodCall.add(
                        InstanceMethodCall.builder() .variableInstace(variable).method(nombreMetodo)
                                .parametros(parameters).operation(operation) .build()
                );
            }
        }
        return operationsInstanceMethodCall;
    }
}
