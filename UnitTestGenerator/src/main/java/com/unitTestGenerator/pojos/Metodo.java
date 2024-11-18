package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;

public class Metodo {

    private String nombre;
    private String tipoRetorno;
    private List<ParametroMetodo> parametros;

    public Metodo() {
        this.parametros = new ArrayList<>();
    }

    public void agregarParametro(ParametroMetodo parametro) {
        this.parametros.add(parametro);
    }

    public void setParametros(List<ParametroMetodo> parametros) {
        this.parametros = parametros;
    }

    public List<ParametroMetodo> getParametros() {
        return parametros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(String tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }
}
