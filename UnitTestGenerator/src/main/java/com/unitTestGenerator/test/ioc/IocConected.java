package com.unitTestGenerator.test.ioc;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Inyect;

@Component
public class IocConected {

    @Inyect
    private Cliente cliente;

    public IocConected() {
    }

    public void ejecute(){
        cliente.ejecutarServicio();
    }
}

