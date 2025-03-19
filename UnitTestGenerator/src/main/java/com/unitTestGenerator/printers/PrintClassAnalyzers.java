package com.unitTestGenerator.printers;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Project;

@Component
@Singleton
public class PrintClassAnalyzers {

    public  PrintClassAnalyzers(){}



    public void printClassDetail() {

        //        private String nombre;
//        private String paquete;
//        private String typeClass;
//        private String classSignatureLine;
//        private String classPath;
//        private String classUml;
//        private List<Metodo> metodos;
//        private List<Variable> variables;
//        private List<Constructor> constructores;
//        private ClassRelations classRelations = new ClassRelations();
//        private ImportAnalizePojo imports;
//        private String structureInterface;
//        private String structureExtends;
//        private String classAnotations;
        
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
