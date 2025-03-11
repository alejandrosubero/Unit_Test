package com.unitTestGenerator.core;

import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.ioc.anotations.Inyect;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.services.GenerateMethodServiceI;
import com.unitTestGenerator.services.GeneratedVariableService;

@Componente
@Singleton
public class DependenceManager {

    @Inyect
	private GenerateMethodServiceI generateMethodService;

    @Inyect
    private GeneratedVariableService generatedVariableService;

    public DependenceManager() {
    }

    public GenerateMethodServiceI getGenerateMethodService() {
        return generateMethodService;
    }

    public GeneratedVariableService getGeneratedVariableService() {
        return generatedVariableService;
    }

}
