package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;

public class Metodo {

    private String nombre;
    private String tipoRetorno;
    private List<ParametroMetodo> parametros;
    private String contenido;
    private List<InstanceMethodCall> instanceMethodCalls;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

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

    public List<InstanceMethodCall> getInstanceMethodCalls() {
        return instanceMethodCalls;
    }

    public void setInstanceMethodCalls(List<InstanceMethodCall> instanceMethodCalls) {
        this.instanceMethodCalls = instanceMethodCalls;
    }

    public void addInstanceMethodCall(InstanceMethodCall instanceMethodCall){
        this.instanceMethodCalls.add(instanceMethodCall);
    }

    public void addInstanceMethodCallAll(List<InstanceMethodCall> insCalls){
        this.instanceMethodCalls.addAll(insCalls);
    }
}
