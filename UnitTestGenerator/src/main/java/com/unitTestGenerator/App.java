package com.unitTestGenerator;

import com.unitTestGenerator.app.AppConfiguration;
import com.unitTestGenerator.app.ErrorManagerApp;

// /Users/alejandrosubero/Documents/TESTREPOSITORY/testPatterbuild
// testService

public class App{

    public static void main(String[] args) {

        if (args.length > 0) {
            ErrorManagerApp.processCliArguments(args);
        } else {
            AppConfiguration.setStarted();
        }
    }
}


//TODO: esta fallando cuando se le pide todos los metodos de una clase no hace el test para cada metodo
// hay que colocar que reconosca que el proyecto no tiene sprint boot para que no coloque las anotaciones e imports
// cuando no lo tiene




