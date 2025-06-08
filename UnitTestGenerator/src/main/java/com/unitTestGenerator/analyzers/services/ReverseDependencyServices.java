package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.analyzers.dependency.reverse.IReverseDependency;
import com.unitTestGenerator.analyzers.dependency.reverse.ReverseDependencyNode;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;

import java.util.List;

@Component
public class ReverseDependencyServices implements IReverseDependency {

    public void ejecute(Project project){
        List<ReverseDependencyNode> reverseDependencyList = this.getReverseDependencys(project.getPathProject());
        for( ReverseDependencyNode node : reverseDependencyList){
            Clase clazz = project.getClass(node.getClassName());
            if (clazz != null) {
                clazz.setReverseDependencyNode(node);
            }

        }
    }

}
