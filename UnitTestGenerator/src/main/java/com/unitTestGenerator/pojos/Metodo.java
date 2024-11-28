package com.unitTestGenerator.pojos;

import java.util.ArrayList;
import java.util.List;

public class Metodo {

    private String nombre;
    private String tipoRetorno;
    private List<ParametroMetodo> parametros= new ArrayList<>();
    private String contenido;
    private List<InstanceMethodCall> instanceMethodCalls = new ArrayList<>();


    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Metodo() {
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
        if(instanceMethodCalls != null && !instanceMethodCalls.isEmpty()) {
            this.instanceMethodCalls = instanceMethodCalls;
        }
    }

    public void addInstanceMethodCall(InstanceMethodCall instanceMethodCall){
        if(instanceMethodCalls != null) {
            this.instanceMethodCalls.add(instanceMethodCall);
        }
    }

    public void addInstanceMethodCallAll(List<InstanceMethodCall> insCalls){
        if(insCalls != null && !insCalls.isEmpty()){
            this.instanceMethodCalls.addAll(insCalls);
        }

    }
}
