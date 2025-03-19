package com.unitTestGenerator.uml.sevices;

import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.ClassRelations;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.Variable;

import java.util.List;

@Component
public class PrintClassToUML {

    private String nombre;
    private List<Variable> atributos;
    private List<Metodo> metodos;
    private ClassRelations relations; // {Relacion: Clase}

    public PrintClassToUML() {
    }

    public PrintClassToUML(String nombre, List<Variable> atributos, List<Metodo> metodos, ClassRelations relaciones) {
        this.nombre = nombre;
        this.atributos = atributos;
        this.metodos = metodos;
        this.relations = relaciones;
    }


    public String generarDiagrama2(Clase classs) {

        StringBuffer buffer = new StringBuffer();
        if (classs != null) {
            int maxLength = calculateMaxLength(classs);
            String border = repeat("-", maxLength + 2) + "+\n";

            buffer.append(border).append("\n");
            buffer.append("Class Name: ").append(classs.getNombre()).append("\n");
            buffer.append(border).append("\n");

            buffer.append("Attributes: ").append("\n");
            if (classs.getVariables() != null && !classs.getVariables().isEmpty()) {
                for (Variable variable : classs.getVariables()) {
                    buffer.append(uMLTemplate(variable.getAccessModifier(), variable.getNombre(), variable.getTipo())).append("\n");
                }
            }
            buffer.append(border).append("\n");

            buffer.append("Methods: ").append(nombre).append("\n");
            if (classs.getMetodos() != null && !classs.getMetodos().isEmpty()) {
                for (Metodo metodo : classs.getMetodos()) {
                    buffer.append(uMLTemplate(metodo.getAccessModifier(), metodo.getMethodSignature(), metodo.getTipoRetorno())).append("\n");
                }
            }
            buffer.append(border).append("\n");

            if (classs.getClassRelations() != null && classs.getClassRelations().check()) {
                buffer.append("Relations: ").append(nombre).append("\n");
                if (classs.getClassRelations().getImplementsList() != null && !classs.getClassRelations().getImplementsList().isEmpty()) {
                    buffer.append("Implement: ").append(nombre).append("\n");
                    for (String impl : classs.getClassRelations().getImplementsList()) {
                        buffer.append(" -").append(impl).append("\n");
                    }
                }
                if (classs.getClassRelations().getClassExtends() != null && !classs.getClassRelations().getClassExtends().isEmpty()) {
                    buffer.append("Extends: ").append(nombre).append("\n");
                    buffer.append(" -").append(classs.getClassRelations().getClassExtends()).append("\n");
                }

                if (classs.getClassRelations().getDependencyInjectionIoC() != null && !classs.getClassRelations().getDependencyInjectionIoC().isEmpty()) {
                    buffer.append("Ioc: ").append(nombre).append("\n");
                    for (String ioc : classs.getClassRelations().getDependencyInjectionIoC()) {
                        buffer.append(" -").append(ioc).append("\n");
                    }
                }

                if (classs.getClassRelations().getStrongDependencyAssociation() != null && !classs.getClassRelations().getStrongDependencyAssociation().isEmpty()) {
                    buffer.append("Strong Association: ").append(nombre).append("\n");
                    for (String classAssociated : classs.getClassRelations().getStrongDependencyAssociation()) {
                        buffer.append(" -").append(classAssociated).append("\n");
                    }
                }
            }
            buffer.append(border).append("\n");
            classs.setClassUml(buffer.toString());
        }

        return  buffer.toString();
    }



    private int calculateMaxLength(Clase classs) {
        int maxLength = nombre.length();
        for (Variable variable : classs.getVariables()) {
            maxLength = Math.max(maxLength, (" " + variable.getTipo() + " " + variable.getNombre()).length());
        }
        for (Metodo metodo : classs.getMetodos()) {
            maxLength = Math.max(maxLength, (metodo.getMethodSignature().length()));
//            maxLength = Math.max(maxLength, (uMLTemplate(metodo.getAccessModifier(), metodo.getMethodSignature(), metodo.getTipoRetorno())).length());
        }
        maxLength = Math.max(maxLength, (" " + classs.getClassRelations().toString()).length());
        return maxLength;
    }



    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    private String uMLTemplate(String accessModifier, String name, String type) {
        StringBuilder bilder = new StringBuilder();
        bilder.append(umlAccessModifier(accessModifier)).append(" ").append(name).append(" : ").append(type).append("\n");
        return bilder.toString();
    }

    private String umlAccessModifier(String accessModifier) {
        switch (accessModifier) {
            case "private":
                return "-";
            case "protected":
                return "#";
            default:
                return "+";
        }
    }





    private String formatLine(String content, int width) {
        int padding = width - content.length();
        return "| " + content + repeat(" ", padding) + " |\n";
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


}
