package com.unitTestGenerator;

import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.EndebleIOC;
import com.unitTestGenerator.test.ioc.IocConected;
import com.unitTestGenerator.test.testIOC;

@EndebleIOC("com.unitTestGenerator")
public class App {
    public static void main(String[] args) {
        ContextIOC.getInstance(App.class).getClassInstance(AppProjectStarted.class).start();
    }
}





