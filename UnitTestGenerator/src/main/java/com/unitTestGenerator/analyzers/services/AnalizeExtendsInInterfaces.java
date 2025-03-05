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

        if(project != null &&  key != null && project.getClaseList() != null && !project.getClaseList().isEmpty()){
            List<Clase> list =  project.getClaseList();
            for (Clase clase : list){
//                CrudRepository, JpaRepository
                if(clase.getClassRelations() != null ){
                    StringBuilder sb = new StringBuilder();

                    if (key.equals('I') && !clase.getClassRelations().getImplementsList().isEmpty()){
                        for (String interfaceI : clase.getClassRelations().getImplementsList()){
                            String structure = analizeExtendsofClassImplements(project, interfaceI);
                            sb.append(structure);
                        }
                        clase.setStructureInterface(sb.toString());
                        return sb.toString();

                    }else {
                        if(clase.getClassRelations().getClassExtends() != null && !clase.getClassRelations().getClassExtends().equals("") && !clase.getClassRelations().getClassExtends().equals(" ")){
                                String structure = analizeExtendsofClassImplements(project, clase.getClassRelations().getClassExtends());
                                sb.append(structure);
                                 clase.setStructureInterface(sb.toString());
                            }
                            return sb.toString();
                        }
                    }
                }
            }
        return "";
    }


    private String analizeExtendsofClassImplements( Project project, String element){
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(element).append("\n");
        Clase classToAnalizeMaterial = project.getClass(element);
        String elementloop = classToAnalizeMaterial != null ? extendsLoop(project, classToAnalizeMaterial) : "";
        sb.append(elementloop).append("\n");
        return sb.toString();
    }


    private String extendsLoop(Project project, Clase classRelation) {
        List<String> tree = new ArrayList<>();
        if(classRelation.getClassRelations() != null){
            Clase loop = classRelation;
            while (loop != null && loop.getClassRelations().getClassExtends() != null && !loop.getClassRelations().getClassExtends().equals("")) {
                tree.add(loop.getClassRelations().getClassExtends());
                loop = project.getClass(loop.getClassRelations().getClassExtends());
            }
        }
        return getTreeString(tree);
    }



    private String getTreeString(List<String> tree) {
        StringBuilder sb = new StringBuilder();
        if(!tree.isEmpty()){
            for(String leaf : tree){
                sb.append("|__ ").append(leaf).append("\n");
            }
            return sb.toString();
        }
       return "";
    }

}



