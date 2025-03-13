package com.unitTestGenerator.test.ioc;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Singleton;

@Component
@Singleton
public class ServicioImpl implements Servicio {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando servicio...");
    }
}