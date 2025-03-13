package com.unitTestGenerator.test.ioc;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Inyect;

@Component
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
