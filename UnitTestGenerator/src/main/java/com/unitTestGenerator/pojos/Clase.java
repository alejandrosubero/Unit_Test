package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;

public class Clase {

    private String nombre;
    private String paquete;
    private List<Metodo> metodos;
    private List<Variable> variables;
    private List<EstructuraControl> estructuras;
    private Boolean useMock;
    private String testMethod;


    public Clase() {
        this.metodos = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.estructuras = new ArrayList<>();
        this.useMock = false;
    }

    public Boolean getUseMock() {
        return useMock;
    }

    public void setUseMock(Boolean useMock) {
        this.useMock = useMock;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void agregarMetodo(Metodo metodo) {
        this.metodos.add(metodo);
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void agregarVariable(Variable variable) {
        this.variables.add(variable);
    }
    public List<EstructuraControl> getEstructuras() {
        return estructuras;
    }

    public void agregarEstructura(EstructuraControl estructura) {
        this.estructuras.add(estructura);
    }
}