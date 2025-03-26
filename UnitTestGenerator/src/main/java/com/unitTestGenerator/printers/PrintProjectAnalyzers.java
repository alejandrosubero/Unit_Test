package com.unitTestGenerator.printers;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;

public interface PrintProjectAnalyzers {


    default void printProjectAnalizeResult(Project projectP) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_BLUE + projectP.getPrinterProject().getProjectUml()+ ANSI_RESET);
    }


    default void printClassList(Project projectP ) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";
        projectP.getPrinterProject().getClaseList().forEach(className -> System.out.println(ANSI_YELLOW + className+ ANSI_RESET));
    }

    default void printMethodsOfClass(Project projectP, String className ) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";
        Clase classs = projectP.getClass(className);
        if(classs != null && classs.getMetodos() != null && !classs.getMetodos().isEmpty()){
            classs.getMetodos().forEach(metodo -> {
                System.out.println(ANSI_RED + metodo.getMethodSignature()+ ANSI_RESET);
            });
        }
    }

    default void printProjectClassTree(Project projectP ) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";
        projectP.getPrinterProject().getClaseList().forEach(className -> System.out.println(ANSI_YELLOW + projectP.getPrinterProject().getProjectClassTree()+ ANSI_RESET));
    }


    default void printProjectFileTree(Project projectP ) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";
        projectP.getPrinterProject().getClaseList().forEach(className -> System.out.println(ANSI_YELLOW + projectP.getPrinterProject().getProjectDirectoryTree()+ ANSI_RESET));
    }

}
