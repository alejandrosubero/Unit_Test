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
    private List<Constructor> constructores;

    public Clase() {
        this.metodos = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.estructuras = new ArrayList<>();
        this.useMock = false;
        this.constructores = new ArrayList<>();
    }


    public List<Constructor> getConstructores() {
        return constructores;
    }

    public void setConstructores(List<Constructor> constructores) {
        this.constructores = constructores;
    }

    public void addConstructor(Constructor constructor) {
        this.constructores.add(constructor);
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

    public void addMetodo(Metodo metodo) {
        this.metodos.add(metodo);
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void addVariable(Variable variable) {
        this.variables.add(variable);
    }

    public List<EstructuraControl> getEstructuras() {
        return estructuras;
    }

    public void addEstructura(EstructuraControl estructura) {
        this.estructuras.add(estructura);
    }
}