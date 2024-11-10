package com.unitTestGenerator;

import com.unitTestGenerator.util.AnalizadorProyecto;
import com.unitTestGenerator.util.Clase;
import com.unitTestGenerator.util.GeneradorPruebasUnitarias;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// https://www.meta.ai/c/44fd9b43-6c67-44ef-a208-8170eca6a0c2

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Bienvenido al generador de pruebas unitarias");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Analizar proyecto");
            System.out.println("2. Generar pruebas unitarias");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    analizarProyecto(scanner);
                    continuar = preguntarContinuar(scanner);
                    break;
                case 2:
                    generarPruebasUnitarias(scanner);
                    continuar = preguntarContinuar(scanner);
                    break;
                case 3:
                    System.out.println("Adiós");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    continuar = preguntarContinuar(scanner);
            }
        }
    }

    private static void analizarProyecto(Scanner scanner) {
        System.out.println("Ingrese la ruta del proyecto:");
        String rutaProyecto = scanner.next();

        List<Clase> clases = AnalizadorProyecto.analizarProyecto(rutaProyecto);

        System.out.println("Clases encontradas:");
        for (Clase clase : clases) {
            System.out.println(clase.getNombre() + "  package: "+ clase.getPaquete());
        }
    }

    private static void generarPruebasUnitarias(Scanner scanner) {
        System.out.println("Ingrese el nombre de la clase:");
        String nombreClase = scanner.next();

        System.out.println("Ingrese la ruta del proyecto:");
        String rutaProyecto = scanner.next();

        List<Clase> clases = AnalizadorProyecto.analizarProyecto(rutaProyecto);
        List<Clase> clasesTemporal = new ArrayList<>();


        clases.stream().forEach(clase -> {
            if (clase.getNombre() != null && clase.getNombre().equals(nombreClase)) {
                clasesTemporal.add(clase);
            }
        });

        Clase claseEncontrada =clasesTemporal.get(0);

//        Clase claseEncontrada = clases.stream()
//                .filter(clase -> clase.getNombre().equals(nombreClase))
//                .findFirst()
//                .orElse(null);

        if (claseEncontrada != null) {
            GeneradorPruebasUnitarias.generarPruebas(claseEncontrada, rutaProyecto);
        } else {
            System.out.println("Clase no encontrada");
        }
    }

    private static boolean preguntarContinuar(Scanner scanner) {
        System.out.println("¿Desea volver al menú inicial? (s/n)");
        String respuesta = scanner.next().toLowerCase();

        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.println("Respuesta inválida. Ingresa 's' para sí o 'n' para no.");
            respuesta = scanner.next().toLowerCase();
        }

        return respuesta.equals("s");
    }
}