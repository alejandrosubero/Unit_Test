package com.unitTestGenerator.analyzers.services;


import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.ClassRelations;
import com.unitTestGenerator.pojos.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalizeExtendsInInterfaces {

    private static AnalizeExtendsInInterfaces instance;

    public static AnalizeExtendsInInterfaces getInstance(){
        if(instance == null){
            instance = new AnalizeExtendsInInterfaces();
        }
        return instance;
    }

    private  AnalizeExtendsInInterfaces() {
    }

    public String analizeImplements( Project project, Character key){
        StringBuilder sb = new StringBuilder();


            project.getClaseList().forEach(clase ->{
                if (key.equals('I')){
                    sb.append(analizeExtendsofClassImplements(project, clase.getClassRelations().getImplementsList()));
                }else{
                    if(clase.getClassRelations().getClassExtends() != null
                            && !clase.getClassRelations().getClassExtends().equals("")
                            && !clase.getClassRelations().getClassExtends().equals(" ")){
                        List<String> elementsList = Arrays.asList(clase.getClassRelations().getClassExtends());
                        sb.append(analizeExtendsofClassImplements(project,elementsList));
                    }
                }

            });

        return sb.toString();
    }


    private String analizeExtendsofClassImplements( Project project, List<String> elementsList ){
        StringBuilder sb = new StringBuilder();
        for(String implemet :  elementsList){
            sb.append("-- ").append(analizeExtendsInInterfaces(project, implemet)).append("\n");
        }
        return sb.toString();
    }

    private String analizeExtendsInInterfaces(Project project, String classToAnalize){
        List<String> tree = new ArrayList<>();
        Clase classToAnalizeMaterial = project.getClass(classToAnalize);
        ClassRelations relationsClassToAnalize = classToAnalizeMaterial.getClassRelations();

        while (relationsClassToAnalize.getClassExtends() != null && !relationsClassToAnalize.getClassExtends().equals("") && !relationsClassToAnalize.getClassExtends().isEmpty()){
            tree.add(relationsClassToAnalize.getClassExtends());
            relationsClassToAnalize = project.getClass(relationsClassToAnalize.getClassExtends()).getClassRelations();
        }
        return getTreeString(tree, classToAnalize);
    }


    private String getTreeString(List<String> tree,  String classToAnalize) {
        StringBuilder sb = new StringBuilder();
        for(String leaf : tree){
            if(classToAnalize.equals(leaf)){
                sb.append(classToAnalize);
            }
            sb.append("|__ ").append(leaf).append("\n");
        }
        return sb.toString();
    }

}



