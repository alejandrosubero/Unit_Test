package com.unitTestGenerator.pojos;

public class Variable {

    private String tipo;
    private String nombre;
    private Boolean isMock;

    public Boolean getMock() {
        return isMock;
    }

    public void setMock(Boolean mock) {
        isMock = mock;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
