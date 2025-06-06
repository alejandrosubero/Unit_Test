package com.unitTestGenerator.analyzers;

public enum TypeClass {

    CLASS("class"), INTEFACE("interface"), ENUM("enum");

    private final String value;

    TypeClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
