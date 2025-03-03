package com.unitTestGenerator.printers;
import java.util.List;
import java.util.Map;

public class PrintClassToUML {

    private String nombre;
    private List<String> atributos;
    private List<String> metodos;
    private Map<String, String> relaciones; // {Relacion: Clase}

    public PrintClassToUML(String nombre, List<String> atributos, List<String> metodos, Map<String, String> relaciones) {
        this.nombre = nombre;
        this.atributos = atributos;
        this.metodos = metodos;
        this.relaciones = relaciones;
    }

    public String generarDiagrama() {
        StringBuilder diagrama = new StringBuilder();

        // Encabezado (Nombre de la clase)
        diagrama.append("+------------------+\n");
        diagrama.append("| " + nombre + " |\n");
        diagrama.append("+------------------+\n");

        // Atributos
        for (String atributo : atributos) {
            diagrama.append("| - " + atributo + " |\n");
        }
        diagrama.append("+------------------+\n");

        // Métodos
        for (String metodo : metodos) {
            diagrama.append("| + " + metodo + "() |\n");
        }
        diagrama.append("+------------------+\n");

        // Relaciones con otras clases
        if (!relaciones.isEmpty()) {
            diagrama.append("Relaciones:\n");
            for (Map.Entry<String, String> relacion : relaciones.entrySet()) {
                diagrama.append(" - " + nombre + " " + relacion.getKey() + " " + relacion.getValue() + "\n");
            }
        }

        return diagrama.toString();
    }
}
