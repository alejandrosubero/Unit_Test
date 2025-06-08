package com.unitTestGenerator.analyzers.dependency.reverse;

import com.unitTestGenerator.ioc.ContextIOC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IReverseDependency {

    default List<ReverseDependencyNode> getReverseDependencys(String router){
        List<ReverseDependencyNode> list = new ArrayList<>();
        try {
            list =  ContextIOC.getInstance().getClassInstance(ReverseDependencyScanner.class).analizer(router);
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

}
