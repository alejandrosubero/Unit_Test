package com.unitTestGenerator.analyzers.services.interfaces;

import com.unitTestGenerator.analyzers.AnalizadorProyecto;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.PrintClassToUML;

import java.util.List;


public interface IProjectAnalizeService {


    default Project executeProjectAnalize(String pathProject, boolean isAnalisis) {

        if (pathProject != null) {
            Project project = new Project(pathProject);
            List<Clase> clases = ContextIOC.getInstance().getClassInstance(AnalizadorProyecto.class).analizarProyecto(pathProject, project);
            if (clases != null) {
                project.setClaseList(clases);
            }
            return project;
        }
        return null;
    }

    default String printUMLClass(Project projectP) {
        StringBuilder buffer = new StringBuilder();
        for (Clase classs : projectP.getClaseList()) {
            PrintClassToUML umlClass = new PrintClassToUML(classs.getNombre(), classs.getVariables(), classs.getMetodos(), classs.getClassRelations());
            buffer.append(umlClass.generarDiagrama()).append("\n").append("\n");
        }
        return buffer.toString();
    }


    default void printProjectAnalize(Project projectP, boolean isAnalisis) {

        if (isAnalisis && projectP.getClaseList() != null) {
            System.out.println("Classes found:...");
            for (Clase clase : projectP.getClaseList()) {
                if (clase.getMetodos() != null && !clase.getMetodos().isEmpty()) {
                    System.out.println(clase.getNombre() + "  package: " + clase.getPaquete());
                    for (Metodo metod : clase.getMetodos()) {
                        if (metod.getInstanceMethodCalls() != null && !metod.getInstanceMethodCalls().isEmpty()) {
                            metod.getInstanceMethodCalls().forEach(instanceMethodCall -> {
                                System.out.println("CALL: -> Method: " + metod.getNombre() + "| Call: " + instanceMethodCall.getOperation() + " |.");
                            });
                        }
                    }
                }
            }
        }

        if (isAnalisis && projectP.getClaseList() != null) {
            System.out.println("Classes found:");
            for (Clase clase : projectP.getClaseList()) {
                System.out.println(clase.getNombre() + "  package: " + clase.getPaquete());
            }
        }
    }




}
