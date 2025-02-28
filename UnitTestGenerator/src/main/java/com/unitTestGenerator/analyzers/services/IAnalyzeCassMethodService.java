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
           String parametros = matcher.group(3);
           if(!classs.getClassRelations().getIdentifieresRelatedClasses().contains(clase)){
               classs.getClassRelations().addIdentifieres(clase);
           }
       }

       Pattern patternSingleton = Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\.getInstance\\(\\)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(([^)]*)\\)");
       Matcher matcherSingleton = patternSingleton.matcher(content);

       while (matcherSingleton.find()) {
           String clase = matcherSingleton.group(1);
           String metodoEstatico = matcherSingleton.group(2);
           String parametros = matcherSingleton.group(3);
           if(!classs.getClassRelations().getIdentifieresRelatedClasses().contains(clase)){
               classs.getClassRelations().addIdentifieres(clase);
           }
       }

     // Strong Dependency Association with direct instantiation
       Pattern patternNewObject = Pattern.compile("\\bnew\\s+([A-Z][a-zA-Z0-9_]*)\\s*\\(([^)]*)\\)");
       Matcher matcherNewObject = patternNewObject.matcher(content);

       while (matcherNewObject.find()) {
           String Clase = matcherNewObject.group(1);
           String parametros = matcherNewObject.group(2);
           // XXXXXXXXX
       }

       Pattern patternBuild = Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\.builder\\(\\)(?:\\.\\w+\\([^)]*\\))*\\.build\\(\\)");
       Matcher matcherBuild = patternBuild.matcher(content);

       while (matcherBuild.find()) {
           String Clase = matcherBuild.group(1);
           //xxxxxxxxxxx
       }

    }

}
