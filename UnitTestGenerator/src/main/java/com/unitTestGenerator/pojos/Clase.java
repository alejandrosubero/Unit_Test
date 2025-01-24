package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clase {

    private String nombre;
    private String paquete;
    private List<Metodo> metodos;
    private List<Variable> variables;
    private List<EstructuraControl> estructuras;
    private Boolean useMock;
    private String testMethod;
    private List<Constructor> constructores;
    private String typeClass;
    private String rawClass;
    private Boolean useLomboxBuild;
    private Boolean applyBuildMethod;

    public Clase() {
        this.metodos = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.estructuras = new ArrayList<>();
        this.useMock = false;
        this.constructores = new ArrayList<>();
        this.useLomboxBuild = false;
        this.applyBuildMethod = false;
    }

    public Boolean getApplyBuildMethod() {
        return applyBuildMethod;
    }

    public void setApplyBuildMethod(Boolean applyBuildMethod) {
        this.applyBuildMethod = applyBuildMethod;
    }

    public Boolean getUseLomboxBuild() {
        return useLomboxBuild;
    }

    public void setUseLomboxBuild(Boolean useLomboxBuild) {
        this.useLomboxBuild = useLomboxBuild;
    }

    public String getRawClass() {
        return rawClass;
    }

    public void setRawClass(String rawClass) {
        this.rawClass = rawClass;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
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

    public List<Variable> getVariables() {
        return variables;
    }

    public List<EstructuraControl> getEstructuras() {
        return estructuras;
    }

    public void addEstructura(EstructuraControl estructura) {
        this.estructuras.add(estructura);
    }

    public void addMetodo(Metodo metodo) {
        this.metodos.add(metodo);
    }

    public void addMetodos(List<Metodo> metodos) {
        this.metodos.addAll(metodos);
    }

    public void addVariable(Variable variable) {
        this.variables.add(variable);
    }

    public void addVariableS(List<Variable> variable) {
        this.variables.addAll(variable);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clase clase = (Clase) o;
        return Objects.equals(nombre, clase.nombre) && Objects.equals(paquete, clase.paquete) && Objects.equals(metodos, clase.metodos) && Objects.equals(variables, clase.variables) && Objects.equals(estructuras, clase.estructuras) && Objects.equals(useMock, clase.useMock) && Objects.equals(testMethod, clase.testMethod) && Objects.equals(constructores, clase.constructores) && Objects.equals(typeClass, clase.typeClass) && Objects.equals(rawClass, clase.rawClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, paquete, metodos, variables, estructuras, useMock, testMethod, constructores, typeClass, rawClass);
    }
}