package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.pojos.Project;

public interface ExtendsInInterfacesService {

    default Project getInterfaceStructure(Project project){
      return   AnalizeExtendsInInterfaces.getInstance().analizeImplements(project,'I');
    }


    default Project getExtendsStructure(Project project){
        return   AnalizeExtendsInInterfaces.getInstance().analizeImplements(project,'E');
    }
}
