package com.unitTestGenerator.test;


import com.unitTestGenerator.ioc.*;
import com.unitTestGenerator.ioc.anotations.EndebleIOC;
import com.unitTestGenerator.test.ioc.IocConected;

@EndebleIOC("com.unitTestGenerator.test.ioc")
public class testIOC {

    public static void main(String[] args) {
        ContextIOC.getInstance(testIOC.class).getClassInstance(IocConected.class).ejecute();
    }

}



