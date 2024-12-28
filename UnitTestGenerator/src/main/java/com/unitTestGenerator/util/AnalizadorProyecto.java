package com.unitTestGenerator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.unitTestGenerator.pojos.*;
import com.unitTestGenerator.services.AnalyzeClassService;
import com.unitTestGenerator.services.MethodService;
import org.apache.commons.io.FileUtils;

public class AnalizadorProyecto {

    private  final String[] IGNORAR = {"target", "node_modules", ".git"};
    private static AnalizadorProyecto instance;

    private AnalizadorProyecto() {
    }

    public static AnalizadorProyecto getInstance() {
        if (instance == null) {
            instance = new AnalizadorProyecto();
        }
        return instance;
    }


    public  List<Clase> analizarProyecto(String rutaProyecto) {
        List<Clase> clases = new ArrayList<>();
        File carpetaProyecto = new File(rutaProyecto);
        analizarProyectoRecursivo(carpetaProyecto, clases);
        return clases;
    }


    private  void analizarProyectoRecursivo(File carpeta, List<Clase> clases) {
        if (carpeta.isDirectory()) {
            String nombreCarpeta = carpeta.getName();

            if (!Arrays.asList(IGNORAR).contains(nombreCarpeta)) {
                for (File archivo : carpeta.listFiles()) {
                    if (archivo.isDirectory()) {
                        analizarProyectoRecursivo(archivo, clases);
                    } else if (archivo.getName().endsWith(".java")) {
                        Clase clase = AnalyzeClassService.getInstance().analyzeClase(archivo);
                        if(clase !=null) {
                            clases.add(clase);
                        }
                    }
                }
            }
        }
    }


    private  Clase analizarClase(File archivo) {
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
//            Pattern patronClase = Pattern.compile("public class (\\w+)");
//            Matcher matcherClase = patronClase.matcher(contenido);
//            if (matcherClase.find()) {
//                clase.setNombre(matcherClase.group(1));
//            }
//            Pattern patronInterface = Pattern.compile("public interface (\\w+)");
//            Matcher matcherInterface = patronInterface.matcher(contenido);
//            if (matcherInterface.find()) {
//                clase.setNombre(matcherInterface.group(1));
//            }

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



            // Analizar constructores
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

            // Analizar métodos
            if (clase != null) {

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

                        // Analizar y Obtener contenido del metodo
                        String patronContenidoMetodo = "public " + metodo.getTipoRetorno() + " " + metodo.getNombre() + "\\((.*?)\\)\\s*\\{([^}]*)\\}";
                        Pattern pattern = Pattern.compile(patronContenidoMetodo, Pattern.DOTALL | Pattern.MULTILINE);
                        Matcher matcherContenidoMetodo = pattern.matcher(contenido);
                        if (matcherContenidoMetodo.find()) {
                            String contenidoMetodo = matcherContenidoMetodo.group(2).trim();
                            metodo.setContenido(contenidoMetodo);
                        }
                        metodoList.add(metodo);
                    }
                }

                if ("interface".equals(clase.getTypeClass())) {
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


            // Analizar variables
            Pattern patronVariable = Pattern.compile("private (\\w+) (\\w+);");
            Matcher matcherVariable = patronVariable.matcher(contenido);

            while (matcherVariable.find()) {
                Variable variable = new Variable();
                variable.setTipo(matcherVariable.group(1));
                variable.setNombre(matcherVariable.group(2));
                clase.addVariable(variable);
            }


            // Análisis de estructuras de control
            Pattern patronEstructuras = Pattern.compile("if|else|for|while|try|catch");
            Matcher matcherEstructuras = patronEstructuras.matcher(contenido);

            while (matcherEstructuras.find()) {
                EstructuraControl estructura = new EstructuraControl();
                estructura.setTipo(matcherEstructuras.group());
                clase.addEstructura(estructura);
            }

        } catch (Exception e) {
            System.out.println("Error al analizar archivo: " + archivo.getName());
        }
        return postClassMethodAnalysis(clase);
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


//    private Clase postClassMethodAnalysis2(Clase classIn) {
//        if (classIn != null) {
//            if (classIn.getVariables() != null && !classIn.getVariables().isEmpty() && classIn.getMetodos() != null && !classIn.getMetodos().isEmpty()) {
//
//                for (Variable variable : classIn.getVariables()) {
//
//                    if (variable != null && variable.getNombre() != null) {
//
//                        for (Metodo method : classIn.getMetodos()) {
//                            if (method != null && method.getContenido() != null) {
//
//                                try {
//                                    String contenido = method.getContenido();
//                                    String nombreVariable = variable.getNombre();
//
//                                    List<InstanceMethodCall> calls =
//                                            MethodService.getInstance()
//                                                    .findOperationPerformedInMethod(contenido, nombreVariable);
//
//                                    calls.forEach(call -> {
//                                        method.addInstanceMethodCall(call);
//                                    });
//
//                                } catch (Exception e) {
//                                    System.err.println("Ocurrió un error inesperado: " + e.getMessage());
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return classIn;
//    }

}
