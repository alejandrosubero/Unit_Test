package com.example.classrelationanalyzer;

public class Relation {
    RelationType type;
    ClassNode node;

    Relation(RelationType type, ClassNode node) {
        this.type = type;
        this.node = node;
    }
}
