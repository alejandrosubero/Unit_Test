package com.unitTestGenerator.services;

import com.unitTestGenerator.pojos.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeClassService {

    public static AnalyzeClassService instance;

    public static AnalyzeClassService getInstance() {
        if (instance == null){
            instance = new AnalyzeClassService();
        }
        return instance;
    }

    private AnalyzeClassService() {
    }

    public Clase getAnalisisOfVariables(String content){
        Clase clase = new Clase();
        analizarVariables( content, clase);
        return clase;
    }

    public Clase getAnalisisOfContenidoMetodo(String content){
        Clase clase = new Clase();
        analizarMetodos(content, clase);
        return clase;
    }


    public Clase analyzeClase(File archivo) {
        Clase clase = new Clase();

        try {
            String contenido = FileUtils.readFileToString(archivo, "UTF-8");

            analizarPaquete(contenido, clase);
            analizarNombreYTipoClase(contenido, clase);
            analizarConstructores(contenido, clase);
            analizarMetodos(contenido, clase);
            analizarVariables(contenido, clase);
            analizarEstructurasControl(contenido, clase);

        } catch (Exception e) {
            System.out.println("Error al analizar archivo: " + archivo.getName());
        }

        return postClassMethodAnalysis(clase);
    }

    private void analizarPaquete(String contenido, Clase clase) {
        Pattern patronPaquete = Pattern.compile("package (.*?);");
        Matcher matcherPaquete = patronPaquete.matcher(contenido);

        while (matcherPaquete.find()) {
            clase.setPaquete(matcherPaquete.group(1));
        }
    }

    private void analizarNombreYTipoClase(String contenido, Clase clase) {
        Pattern patronClase = Pattern.compile("public class (\\w+)");
        Matcher matcherClase = patronClase.matcher(contenido);

        if (matcherClase.find()) {
            clase.setNombre(matcherClase.group(1));
            clase.setTypeClass("class");
        } else {
            Pattern patronInterface = Pattern.compile("public interface (\\w+)");
            Matcher matcherInterface = patronInterface.matcher(contenido);
            if (matcherInterface.find()) {
                clase.setNombre(matcherInterface.group(1));
                clase.setTypeClass("interface");
            }
        }
    }

    private void analizarConstructores(String contenido, Clase clase) {
        Pattern patronConstructor = Pattern.compile("public (\\w+)\\((.*?)\\)", Pattern.DOTALL);
        Matcher matcherConstructor = patronConstructor.matcher(contenido);

        while (matcherConstructor.find()) {
            Constructor constructor = new Constructor();

            // Analizar parámetros
            String[] parametros = matcherConstructor.group(2).split(",");
            List<ParametroMetodo> parametroMetodos = new ArrayList<>();
            for (String parametro : parametros) {
                if (!parametro.trim().isEmpty()) {
                    String[] partes = parametro.trim().split("\\s+");
                    ParametroMetodo parametroMetodo = new ParametroMetodo(partes[1], partes[0]);
                    parametroMetodos.add(parametroMetodo);
                }
            }

            constructor.setParametros(parametroMetodos);
            constructor.setIsNoneParam(parametroMetodos.isEmpty());
            clase.addConstructor(constructor);
        }
    }

    private void analizarMetodos(String contenido, Clase clase) {
        List<Metodo> metodoList = new ArrayList<>();

        if ("class".equals(clase.getTypeClass())) {
            Pattern patronMetodo = Pattern.compile("public (\\w+) (\\w+)\\((.*?)\\)", Pattern.DOTALL);
            Matcher matcherMetodo = patronMetodo.matcher(contenido);

            while (matcherMetodo.find()) {
                Metodo metodo = new Metodo();
                metodo.setNombre(matcherMetodo.group(2));
                metodo.setTipoRetorno(matcherMetodo.group(1));

                // Analizar parámetros
                String[] parametros = matcherMetodo.group(3).split(",");
                for (String parametro : parametros) {
                    if (!parametro.trim().isEmpty()) {
                        String[] partes = parametro.trim().split("\\s+");
                        ParametroMetodo parametroMetodo = new ParametroMetodo(partes[1], partes[0]);
                        metodo.agregarParametro(parametroMetodo);
                    }
                }

                // Obtener contenido del método
                analizarContenidoMetodo(contenido, metodo);

                metodoList.add(metodo);
            }
        } else if ("interface".equals(clase.getTypeClass())) {
            Pattern patronMetodoInterface = Pattern.compile("\\s*public\\s+(\\w+(?:<.*?>)?)\\s+(\\w+)\\s*\\((.*?)\\)\\s*;", Pattern.DOTALL);
            Matcher matcherMetodoInterface = patronMetodoInterface.matcher(contenido);

            while (matcherMetodoInterface.find()) {
                Metodo metodo = new Metodo();
                metodo.setNombre(matcherMetodoInterface.group(2));
                metodo.setTipoRetorno(matcherMetodoInterface.group(1));

                // Analizar parámetros
                String[] parametros = matcherMetodoInterface.group(3).split(",");
                for (String parametro : parametros) {
                    if (!parametro.trim().isEmpty()) {
                        String[] partes = parametro.trim().split("\\s+");
                        ParametroMetodo parametroMetodo = new ParametroMetodo(partes[1], partes[0]);
                        metodo.agregarParametro(parametroMetodo);
                    }
                }
                metodoList.add(metodo);
            }
        }

        clase.addMetodos(metodoList);
    }



    private void analizarContenidoMetodo(String contenido, Metodo metodo) {
        String patronContenidoMetodo = "public " + metodo.getTipoRetorno() + " " + metodo.getNombre() + "\\((.*?)\\)\\s*\\{([^}]*)\\}";
        Pattern pattern = Pattern.compile(patronContenidoMetodo, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcherContenidoMetodo = pattern.matcher(contenido);

        if (matcherContenidoMetodo.find()) {
            String contenidoMetodo = matcherContenidoMetodo.group(2).trim();
            metodo.setContenido(contenidoMetodo);
        }
    }




    private void analizarVariables(String contenido, Clase clase) {
        Pattern patronVariable = Pattern.compile("private (\\w+) (\\w+);");
        Matcher matcherVariable = patronVariable.matcher(contenido);

        while (matcherVariable.find()) {
            Variable variable = new Variable();
            variable.setTipo(matcherVariable.group(1));
            variable.setNombre(matcherVariable.group(2));
            clase.addVariable(variable);
        }
    }

    private void analizarEstructurasControl(String contenido, Clase clase) {
        Pattern patronEstructuras = Pattern.compile("if|else|for|while|try|catch");
        Matcher matcherEstructuras = patronEstructuras.matcher(contenido);

        while (matcherEstructuras.find()) {
            EstructuraControl estructura = new EstructuraControl();
            estructura.setTipo(matcherEstructuras.group());
            clase.addEstructura(estructura);
        }
    }

    private  Clase postClassMethodAnalysis(Clase classIn) {
        if (classIn.getVariables() != null && !classIn.getVariables().isEmpty() && classIn.getMetodos() != null && !classIn.getMetodos().isEmpty()) {
            try {
                classIn.getVariables().stream().forEach(variable -> {
                    classIn.getMetodos().stream().forEach(method -> {
                        if (method != null && variable != null && variable.getNombre() != null && method.getContenido() != null) {
                            method.addInstanceMethodCallAll(
                                    MethodService.getInstance().findOperationPerformedInMethod(
                                            method.getContenido(),
                                            variable.getNombre())
                            );
                        }
                    });
                });
            } catch (Exception e) {
                System.err.println("Error... : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return classIn;
    }


}
