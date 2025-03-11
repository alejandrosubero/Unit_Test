package com.unitTestGenerator.util;

public class DetectSO {

        public static String os() {
            String sistemaOperativo = System.getProperty("os.name").toLowerCase();
            String respuesta = "";
            if (sistemaOperativo.contains("win")) {
                respuesta = "win";
            } else if (sistemaOperativo.contains("mac")) {
                respuesta = "mac";
            } else if (sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux") || sistemaOperativo.contains("aix")) {
                respuesta = "Linux/Unix";
            } else {
                System.out.println("Operative system : " + sistemaOperativo);
            }
            return respuesta;
        }
    }
