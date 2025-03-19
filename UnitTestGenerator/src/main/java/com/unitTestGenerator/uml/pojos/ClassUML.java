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

    private String uMLTemplate(String accessModifier, String name, String type) {
        StringBuilder bilder = new StringBuilder();
        bilder.append(umlAccessModifier(accessModifier)).append(" ").append(name).append(": ").append(type).append("\n");
        return bilder.toString();
    }

    private String umlAccessModifier(String accessModifier) {
        switch (accessModifier) {
            case "private":
                return "-";
            case "protected":
                return "#";
            default:
                return "+";
        }
    }

}

