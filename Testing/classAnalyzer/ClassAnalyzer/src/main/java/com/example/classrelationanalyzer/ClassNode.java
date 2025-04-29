package com.example.classrelationanalyzer;

import java.util.ArrayList;
import java.util.List;

public class ClassNode {
    String className;
//    List<ClassNode> children = new ArrayList<>();

    ClassNode(String className) {
        this.className = className;
    }

//    void addChild(ClassNode child) {
//        children.add(child);
//    }

    List<Relation> children = new ArrayList<>();


}


