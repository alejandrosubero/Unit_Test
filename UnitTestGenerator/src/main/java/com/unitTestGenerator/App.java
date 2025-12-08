package com.unitTestGenerator;

import com.unitTestGenerator.app.AppConfiguration;
import com.unitTestGenerator.app.ErrorManagerApp;


public class App{

    public static void main(String[] args) {

        if (args.length > 0) {
            ErrorManagerApp.processCliArguments(args);
        } else {
            AppConfiguration.setStarted();
        }
    }
}





