package com.diagram;



import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DiagramElement {
    private final StringProperty name;
    private final StringProperty value;
    private final ObservableList<String> variables;
    private final ObservableList<String> methods;
    private final ObservableList<String> relations;
    private final ObservableList<ElementConnection> connections;
    private final DoubleProperty x;
    private final DoubleProperty y;
    private static long nextId = 0;
    private final long id;

    public DiagramElement(String name, String value, List<String> variables, List<String> methods, List<String> relations, double x, double y) {
        this.id = nextId++;
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
        this.variables = FXCollections.observableArrayList(variables);
        this.methods = FXCollections.observableArrayList(methods);
        this.relations = FXCollections.observableArrayList(relations);
        this.connections = FXCollections.observableArrayList();
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }


    public DiagramElement(String name, String value, List<String> variables, List<String> methods, List<String> relations, List<ElementConnection> connections, double x, double y) {
        this.id = nextId++;
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
        this.variables = FXCollections.observableArrayList(variables);
        this.methods = FXCollections.observableArrayList(methods);
        this.relations = FXCollections.observableArrayList(relations);
        this.connections = FXCollections.observableArrayList(connections);
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public ObservableList<String> getVariables() {
        return variables;
    }

    public ObservableList<String> getMethods() {
        return methods;
    }

    public ObservableList<String> getRelations() {
        return relations;
    }

    public ObservableList<ElementConnection> getConnections() {
        return connections;
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }
}
