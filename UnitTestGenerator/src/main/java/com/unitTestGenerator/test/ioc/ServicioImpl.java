package com.unitTestGenerator.test.ioc;

import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.ioc.anotations.Singleton;

@Componente
@Singleton
public class ServicioImpl implements Servicio {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando servicio...");
    }
}