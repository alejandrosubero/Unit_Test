package com.unitTestGenerator.services;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Node;

@Component
public class ClassNodeService {

    public Node generateNode(Clase classs){

        Node node = classs.updateNode(null);


    }

}
