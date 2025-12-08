package com.unitTestGenerator.exception;

import java.io.IOException;
//ExceptionHandler.handle(Throwable e);


public class ExceptionHandler {

    private static String lastStackTrace;

    public static void handle(Throwable e) {
        StringBuilder sb = new StringBuilder();

        // Manejo específico por tipo de excepción

        if (e instanceof NullPointerException) {
            sb.append("Se detectó un NullPointerException.\n");
        } else if (e instanceof IOException) {
            sb.append("Se detectó un IOException.\n");
        } else if (e instanceof IllegalArgumentException) {
            sb.append("Se detectó un IllegalArgumentException.\n");
        } else if (e instanceof IndexOutOfBoundsException) {
            sb.append("Se detectó un IndexOutOfBoundsException.\n");
        } else if (e instanceof RuntimeException) {
            sb.append("Se detectó un RuntimeException genérico.\n");
        } else {
            sb.append("Se detectó una excepción no categorizada.\n");
        }


        sb.append("Tipo de excepción: ")
                .append(e.getClass().getName())
                .append("\nMensaje: ")
                .append(e.getMessage())
                .append("\nStackTrace:\n");

        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("    at ").append(element.toString()).append("\n");
        }

        lastStackTrace = sb.toString();
    }

    public static String getLastStackTrace() {
        return lastStackTrace;
    }
}
