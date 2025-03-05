package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.pojos.Project;

public interface ExtendsInInterfacesService {

    default String getInterfaceStructure(Project project){
      return   AnalizeExtendsInInterfaces.getInstance().analizeImplements(project,'I');
    }


    default String getExtendsStructure(Project project){
        return   AnalizeExtendsInInterfaces.getInstance().analizeImplements(project,'E');
    }
}
