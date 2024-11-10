package com.unitTestGenerator.util;

import java.util.Objects;

public class EstructuraControl {

    private String tipo;

    public EstructuraControl() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstructuraControl that = (EstructuraControl) o;
        return Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tipo);
    }

}
