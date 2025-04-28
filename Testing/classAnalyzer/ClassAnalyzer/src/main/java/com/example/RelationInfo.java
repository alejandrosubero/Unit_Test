package com.example;

public class RelationInfo {
    String childClassName;
    RelationType relationType;

    RelationInfo(String childClassName, RelationType relationType) {
        this.childClassName = childClassName;
        this.relationType = relationType;
    }
}
