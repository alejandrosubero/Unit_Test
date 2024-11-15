package com.unitTestGenerator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.EstructuraControl;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Variable;
import org.apache.commons.io.FileUtils;

public class AnalizadorProyecto {

private static final String[] IGNORAR = {"target", "node_modules", ".git"};

    public static List<Clase> analizarProyecto(String rutaProyecto) {
        List<Clase> clases = new ArrayList<>();

        File carpetaProyecto = new File(rutaProyecto);

        analizarProyectoRecursivo(carpetaProyecto, clases);

        return clases;
    }


    private static void analizarProyectoRecursivo(File carpeta, List<Clase> clases) {
        if (carpeta.isDirectory()) {
            String nombreCarpeta = carpeta.getName();

            if (!Arrays.asList(IGNORAR).contains(nombreCarpeta)) {
                for (File archivo : carpeta.listFiles()) {
                    if (archivo.isDirectory()) {
                        analizarProyectoRecursivo(archivo, clases);
                    } else if (archivo.getName().endsWith(".java")) {
                        clases.add(analizarClase(archivo));
                    }
                }
            }
        }
    }


    private static Clase analizarClase(File archivo) {
        Clase clase = new Clase();

        try {
            String contenido = FileUtils.readFileToString(archivo, "UTF-8");


            // Analizar paquetes
            Pattern patronPaquete = Pattern.compile("package (.*?);");
            Matcher matcherPaquete = patronPaquete.matcher(contenido);

            while (matcherPaquete.find()) {
               clase.setPaquete(matcherPaquete.group(1));
            }

            // Analizar contenido para extraer información de la clase
            Pattern patronClase = Pattern.compile("public class (\\w+)");
            Matcher matcherClase = patronClase.matcher(contenido);

            if (matcherClase.find()) {
                clase.setNombre(matcherClase.group(1));
            }

            // Analizar métodos
            Pattern patronMetodo = Pattern.compile("public (\\w+) (\\w+)\\((.*?)\\)");
            Matcher matcherMetodo = patronMetodo.matcher(contenido);

            while (matcherMetodo.find()) {
                Metodo metodo = new Metodo();
                metodo.setNombre(matcherMetodo.group(2));
                metodo.setTipoRetorno(matcherMetodo.group(1));
                clase.agregarMetodo(metodo);
            }

            // Analizar variables
            Pattern patronVariable = Pattern.compile("private (\\w+) (\\w+);");
            Matcher matcherVariable = patronVariable.matcher(contenido);

            while (matcherVariable.find()) {
                Variable variable = new Variable();
                variable.setTipo(matcherVariable.group(1));
                variable.setNombre(matcherVariable.group(2));
                clase.agregarVariable(variable);
            }

            // Análisis de estructuras de control
            Pattern patronEstructuras = Pattern.compile("if|else|for|while|try|catch");
            Matcher matcherEstructuras = patronEstructuras.matcher(contenido);

            while (matcherEstructuras.find()) {
                EstructuraControl estructura = new EstructuraControl();
                estructura.setTipo(matcherEstructuras.group());

                clase.agregarEstructura(estructura);
            }

        } catch (Exception e) {
            System.out.println("Error al analizar archivo: " + archivo.getName());
        }

        return clase;
    }
}
