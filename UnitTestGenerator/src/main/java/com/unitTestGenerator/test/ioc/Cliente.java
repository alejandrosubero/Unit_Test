package com.unitTestGenerator.test.ioc;

import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.ioc.anotations.Inyect;

@Componente
public class Cliente {

    @Inyect
    private Servicio servicio;

    // Constructor con dependencias
    public Cliente() {
    }

    public void ejecutarServicio() {
        servicio.ejecutar();
    }

}
