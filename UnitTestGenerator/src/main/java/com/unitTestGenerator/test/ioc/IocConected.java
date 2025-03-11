package com.unitTestGenerator.test.ioc;

import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.ioc.anotations.Inyect;

@Componente
public class IocConected {

    @Inyect
    private Cliente cliente;

    public IocConected() {
    }

    public void ejecute(){
        cliente.ejecutarServicio();
    }
}

