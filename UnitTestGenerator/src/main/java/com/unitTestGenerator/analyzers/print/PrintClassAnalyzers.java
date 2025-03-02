package com.unitTestGenerator.analyzers.print;

import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Project;

public class PrintClassAnalyzers {

    private static PrintClassAnalyzers instance;

    public static PrintClassAnalyzers getInstance(){
        if(instance == null){
            instance = new PrintClassAnalyzers();
        }
        return instance;
    }

    private  PrintClassAnalyzers() {
    }


    public void printProjectAnalize(Project projectP, boolean isAnalisis){

        if(isAnalisis && projectP.getClaseList() != null) {
            System.out.println("Classes found:...");
            for (Clase clase : projectP.getClaseList()) {
                if(clase.getMetodos() != null && !clase.getMetodos().isEmpty()){
                    System.out.println(clase.getNombre() + "  package: " + clase.getPaquete());
                    for(Metodo metod : clase.getMetodos()){
                        if(metod.getInstanceMethodCalls() != null && !metod.getInstanceMethodCalls().isEmpty() ){
                            metod.getInstanceMethodCalls().forEach(instanceMethodCall -> {
                                System.out.println(  "CALL: -> Method: "+ metod.getNombre() + "| Call: "+instanceMethodCall.getOperation()+" |.");
                            });
                        }
                    }
                }
            }
        }

        if(isAnalisis && projectP.getClaseList() != null) {
            System.out.println("Classes found:");
            for (Clase clase : projectP.getClaseList()) {
                System.out.println(clase.getNombre() + "  package: " + clase.getPaquete());
            }
        }
    }



}
