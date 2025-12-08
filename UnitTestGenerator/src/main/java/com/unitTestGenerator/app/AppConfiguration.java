package com.unitTestGenerator.app;

import com.unitTestGenerator.App;
import com.unitTestGenerator.core.AppProjectStarted;
import com.unitTestGenerator.exception.GlobalExceptionHandler;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.EndebleIOC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@EndebleIOC("com.unitTestGenerator")
public class AppConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String goodBye = " \uD83D\uDD25 \uD83D\uDD25 \uD83D\uDD25 🚀 Good bye CLI... 🚀 \uD83D\uDD25 \uD83D\uDD25 \uD83D\uDD25 ";
    private static final String started ="🚀 \uD83D\uDD25 starting the aplicaction CLI...🚀";
    private static final String startedStep =" \uD83D\uDE80 Starting the app \uD83D\uDE80...";
    private static final String globalIntersector = "\uD83D\uDD25 Global exception interceptor - Active";


public static void setStarted(){
    GlobalExceptionInterceptorActive();
    try {
        run();
    } catch (Exception e) {
        logger.error("Error en main: {}", e.getMessage());
        throw e;
    }
}

    private static void run() {
        System.out.println(startedStep);
        ContextIOC.getInstance(AppConfiguration.class).getClassInstance(AppProjectStarted.class).start();
        logger.info(goodBye);
    }

    private static void GlobalExceptionInterceptorActive(){
        logger.info(globalIntersector);
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
        logger.info(started);
    }

}
