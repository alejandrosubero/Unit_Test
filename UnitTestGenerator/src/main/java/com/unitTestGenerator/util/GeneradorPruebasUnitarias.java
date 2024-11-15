package com.unitTestGenerator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorPruebasUnitarias {

    public static void generarPruebas(Clase clase, String rutaProyecto) {
        PomAnalyzer.agregarDependencias(rutaProyecto);
        String nombreClase = clase.getNombre();
        String contenidoPrueba = obtenerContenidoPrueba(clase);
        String separador = File.separator;
        String basePath= separador+"src"+separador+"test"+separador+"java"+separador;//"/src/test/java/"
        String packagePath = clase.getPaquete().replace(".", separador);
        String rutaPrueba = rutaProyecto + basePath + packagePath + separador+nombreClase + "Test.java";
        crearArchivoPrueba(rutaPrueba, contenidoPrueba);
    }

    private static String obtenerContenidoPrueba(Clase clase) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("package ").append(clase.getPaquete()).append(";").append("\n");
        contenido.append("import org.junit.Test;\n");
        contenido.append("import org.junit.Assert;\n");
        contenido.append("public class " + clase.getNombre() + "Test {\n");

        for (Metodo metodo : clase.getMetodos()) {
            contenido.append(obtenerContenidoMetodo(metodo));
        }
            // Generar pruebas para variables
        for (Variable variable : clase.getVariables()) {
            contenido.append(generarPruebaVariable(variable));
        }
        contenido.append("}\n");

        return contenido.toString();
    }

    private static String obtenerContenidoMetodo(Metodo metodo) {
        return "\n@Test\npublic void test" + metodo.getNombre().substring(0, 1).toUpperCase() + metodo.getNombre().substring(1) + "() {\n" +
                "    // Implementar prueba\n" +
                "    Assert.assertTrue(true);\n" +
                "}\n";
    }

    //TODO: SEPARARLO EN UNA CLASE - USAR EL ISMOCK PARA DEFINIR SI SE MOCK O SE GENERA DATOS PARA SER PERSITIDOS Y USADOS
    //TODO: NO SE ESTA GENERANDO LA VARIABLE DE LA CLASE A TESTEAR.
    //TODO: TENDRA QUE RESCIBIR UNA CLASE PARA TRABAJAR DENTRO DE LA CLASE NUEVA LA VARIABLE.
    //TODO; ESTE METODO TAMBIEN VA ANALISAR OTRAS CLASES PARA USAR EN LA GENERACION DE LA PRUEBA.
    private static String generarPruebaVariable(Variable variable) {
        return "\n@Test\npublic void test" + variable.getNombre().substring(0, 1).toUpperCase() + variable.getNombre().substring(1) + "() {\n" +
                "    // Implementar prueba\n" +
                "    Assert.assertNotNull(" + variable.getNombre() + ");\n" +
                "}\n";
    }

    private static void crearArchivoPrueba(String ruta, String contenido) {
        File archivo = new File(ruta);
        fileExist(archivo);
        fileWriter( archivo,contenido,ruta);
    }

    private static void fileWriter(File archivo, String contenido, String ruta){
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
            System.out.println("Prueba generada con éxito en " + ruta);
        } catch (IOException e) {
            System.out.println("Error al crear archivo de prueba: " + e.getMessage());
        }
    }
    private static void fileExist(File archivo){
        if (!archivo.getParentFile().exists()) {
            // Crear la carpeta si no existe
            if (archivo.getParentFile().mkdirs()) {
                System.out.println("Carpeta creada con éxito: " + archivo.getParentFile());
            } else {
                System.out.println("Error al crear carpeta: " + archivo.getParentFile());
            }
        }
    }

}