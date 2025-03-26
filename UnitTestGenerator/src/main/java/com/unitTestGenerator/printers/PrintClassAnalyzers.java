package com.unitTestGenerator.printers;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.pojos.*;

@Component
@Singleton
public class PrintClassAnalyzers {

    public  PrintClassAnalyzers(){}

    public void printClassDetail(Clase classs) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_YELLOW + classs.getClassDetail()+ ANSI_RESET);
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

    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

}
