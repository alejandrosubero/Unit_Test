package com.unitTestGenerator.printers;
import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.pojos.ClassRelations;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Variable;

import java.util.List;
import java.util.Map;

@Componente
public class PrintClassToUML {

    private String nombre;
    private List<Variable> atributos;
    private List<Metodo> metodos;
    private ClassRelations relations; // {Relacion: Clase}

    public PrintClassToUML(String nombre, List<Variable> atributos, List<Metodo> metodos, ClassRelations relaciones) {
        this.nombre = nombre;
        this.atributos = atributos;
        this.metodos = metodos;
        this.relations = relaciones;
    }

    public String generarDiagrama() {
        StringBuilder diagrama = new StringBuilder();
        int maxLength = calcularAnchoMaximo();
        String border = "+" + repeat("-", maxLength + 2) + "+\n";

        // Encabezado
        diagrama.append(border);
        diagrama.append(formatLine(nombre, maxLength));
        diagrama.append(border);

        // Atributos
        for (Variable variable : atributos) {
            diagrama.append(formatLine("- " + variable.getTipo() + " " + variable.getNombre(), maxLength));
        }
        diagrama.append(border);

        // Métodos
        for (Metodo metodo : metodos) {
            diagrama.append(formatLine("+ " + metodo.getMethodSignature(), maxLength));
        }
        diagrama.append(border);

        // Relaciones
        if (relations != null) {
            diagrama.append("Relations:\n");
            diagrama.append(border);
            //TODO: HAY QUE COLOCAR EL LISTADO DE LAS RELACIONES PERO
            diagrama.append("" + this.relations.toString() + "\n");
            diagrama.append(border);
        }

        return diagrama.toString();
    }

    private int calcularAnchoMaximo() {
        int maxLength = nombre.length();

        for (Variable variable : atributos) {
            maxLength = Math.max(maxLength, ("- " + variable.getTipo() + " " + variable.getNombre()).length());
        }

        for (Metodo metodo : metodos) {
            maxLength = Math.max(maxLength, ("+ " + metodo.getMethodSignature()).length());
        }


            maxLength = Math.max(maxLength, ("+ " + this.relations.toString()).length());

        return maxLength;
    }

    private String formatLine(String content, int width) {
        int padding = width - content.length();
        return "| " + content + repeat(" ", padding) + " |\n";
    }

    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }




}
