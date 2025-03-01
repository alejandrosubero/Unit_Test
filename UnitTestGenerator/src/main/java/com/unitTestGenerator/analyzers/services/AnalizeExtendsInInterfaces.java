package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.analyzers.print.DirectoryTreeBuilder;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.ClassRelations;
import com.unitTestGenerator.pojos.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface AnalizeExtendsInInterfaces {

    default String analizeExtendsofClassImplements( Project project, ClassRelations classRelations ){
        StringBuilder sb = new StringBuilder();
        for(String implemet : classRelations.getImplementsList()){
            sb.append("-- ").append(analizeExtendsInInterfaces(project, implemet)).append("\n");
        }
        return sb.toString();
    }

    default String analizeExtendsInInterfaces(Project project, String classToAnalize){
        List<String> tree = new ArrayList<>();
        Clase classToAnalizeMaterial = project.getClass(classToAnalize);
        ClassRelations relationsClassToAnalize = classToAnalizeMaterial.getClassRelations();

        while (relationsClassToAnalize.getClassExtends() != null && !relationsClassToAnalize.getClassExtends().equals("") && !relationsClassToAnalize.getClassExtends().isEmpty()){
            tree.add(relationsClassToAnalize.getClassExtends());
            relationsClassToAnalize = project.getClass(relationsClassToAnalize.getClassExtends()).getClassRelations();
        }
        return getTreeString(tree, classToAnalize);
    }


    default String getTreeString(List<String> tree,  String classToAnalize) {
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
