package com.unitTestGenerator.analyzers.print;

import com.unitTestGenerator.core.AppProjectStarted;

public class PrintClassAnalyzers {

    private static PrintClassAnalyzers instance;

    public static PrintClassAnalyzers getInstance(){
        if(instance == null){
            instance = new PrintClassAnalyzers();
        }
        return instance;
    }

    private  PrintClassAnalyzers() {
    }





}
