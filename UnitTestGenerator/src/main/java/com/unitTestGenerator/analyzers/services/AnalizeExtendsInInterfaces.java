package com.unitTestGenerator.analyzers.services;


import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.ClassRelations;
import com.unitTestGenerator.pojos.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public Project analizeImplements( Project project, Character key){
        if(project != null &&  key != null && project.getClaseList() != null && !project.getClaseList().isEmpty()){
            project.getClaseList().forEach(clase -> {
                if(clase.getClassRelations() != null && !clase.getClassRelations().getImplementsList().isEmpty()){
                    StringBuffer buffer = new StringBuffer();
                    clase.getClassRelations().getImplementsList().forEach(Implementation -> {
                        buffer.append("- ").append(Implementation.trim()).append("\n");
                        Clase implementClass = project.getClass(Implementation.trim());
                        this.extendsLoop(project, implementClass);

                    });
                    clase.setStructureInterface(buffer.toString());
                }
            });

        }
        return project;
    }


    private String extendsLoop(Project project, Clase classRelation) {
        List<String> tree = new ArrayList<>();
        if(isExtents(classRelation)){
            Clase loop = classRelation;

            while (isExtents(loop)) {
                String extTemp = loop.getClassRelations().getClassExtends().trim();
                tree.add(extTemp);
               Optional<Clase> c = project.getClaseList().stream().filter(clase -> clase.getNombre().equals(extTemp)).findFirst();

                loop = project.getClass(extTemp);
            }
        }
        String structure = this.getTreeString(tree);
        return structure;
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


    private Boolean isExtents(Clase loop){
        return loop != null && loop.getClassRelations() != null && loop.getClassRelations().getClassExtends() != null && !loop.getClassRelations().getClassExtends().equals("");
    }




    public String analizeImplement( Project project, Character key){

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








}



