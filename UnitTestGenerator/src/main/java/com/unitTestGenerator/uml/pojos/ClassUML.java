package com.unitTestGenerator.uml.pojos;

import com.unitTestGenerator.ioc.anotations.Component;

@Component
public class ClassUML {

    private String ClassToUML;

    public ClassUML() {
    }

    public String getClassToUML() {
        return ClassToUML;
    }

    public void setClassToUML(String classToUML) {
        ClassToUML = classToUML;
    }



}

