package com.unitTestGenerator.analyzers.services;
import com.unitTestGenerator.pojos.Clase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface IAnalyzeCassMethodService {

   default void analyzeMethod(String content, Clase classs) {

       Pattern pattern = Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(([^)]*)\\)");
       Matcher matcher = pattern.matcher(content);

       while (matcher.find()) {
           String clase = matcher.group(1);
           String metodoEstatico = matcher.group(2);
           String Parametros = matcher.group(3);
           if(!classs.getClassRelations().getIdentifieresRelatedClasses().contains(clase)){
               classs.getClassRelations().addIdentifieres(clase);
           }
       }

       Pattern patternSingleton = Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\.getInstance\\(\\)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(([^)]*)\\)");
       Matcher matcherSingleton = patternSingleton.matcher(content);

       while (matcherSingleton.find()) {
           String clase = matcherSingleton.group(1);
           String metodoEstatico = matcherSingleton.group(2);
           String Parametros = matcherSingleton.group(3);
           if(!classs.getClassRelations().getIdentifieresRelatedClasses().contains(clase)){
               classs.getClassRelations().addIdentifieres(clase);
           }
       }



    }

}
