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
        try {
            clase = analyzeClaseContentString(content);
        } catch (Exception e) {
            System.out.println("Error  analized the content of file...");
        }
        return clase;
    }

    public Clase getAnalisisOfContenidoMetodo(String content){
        Clase clase = new Clase();
        analizarMetodos(content, clase);
        return clase;
    }

    public Clase analyzeClase(File archivo) {
        try {
            String content = FileUtils.readFileToString(archivo, "UTF-8");
            return analyzeClaseContentString(content);

        } catch (Exception e) {
            System.out.println("Error al analizar archivo: " + archivo.getName());
        }
        return null;
    }


    private  Clase analyzeClaseContentString( String content) throws Exception {
        Clase clase = new Clase();
            analizarPaquete(content, clase);
            analizarNombreYTipoClase(content, clase);
            analizarConstructores(content, clase);
            analizarMetodos(content, clase);
            analyzeVariables(content, clase);
            analizarEstructurasControl(content, clase);
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

    private void analizarMetodos2(String contenido, Clase clase) {
        List<Metodo> metodoList = new ArrayList<>();

        if ("class".equals(clase.getTypeClass())) {
            Pattern patronMetodo = Pattern.compile("public (\\w+) (\\w+)\\((.*?)\\)", Pattern.DOTALL);
//            Pattern patronMetodo = Pattern.compile("(public|protected|private) (\\w+) (\\w+)\\((.*?)\\)", Pattern.DOTALL);
                Matcher matcherMetodo = patronMetodo.matcher(contenido);

            while (matcherMetodo.find()) {
                Metodo metodo = new Metodo();
//                metodo.setNombre(matcherMetodo.group(3));
//                metodo.setTipoRetorno(matcherMetodo.group(2));
//                metodo.setAccessModifier(matcherMetodo.group(1));

                metodo.setNombre(matcherMetodo.group(2));
                metodo.setTipoRetorno(matcherMetodo.group(1));


                // Analized parameters
                String[] parametros = matcherMetodo.group(3).split(",");
                for (String parametro : parametros) {
                    if (!parametro.trim().isEmpty()) {
                        String[] partes = parametro.trim().split("\\s+");
                        ParametroMetodo parametroMetodo = new ParametroMetodo(partes[1], partes[0]);
                        metodo.agregarParametro(parametroMetodo);
                    }
                }

//                // Analized Method Signature
//                String firmaMetodo = matcherMetodo.group(0).trim();
//                metodo.setMethodSignature(firmaMetodo);

//                // Analized Method anotation
//                Pattern patronAnotacion = Pattern.compile("@[^\\n]*");
//                Matcher matcherAnotacion = patronAnotacion.matcher(firmaMetodo);
//                StringBuilder anotaciones = new StringBuilder();
//                while (matcherAnotacion.find()) {
//                    anotaciones.append(matcherAnotacion.group()).append(" ");
//                }
//                metodo.setAnotation(anotaciones.toString().trim());

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

private void analizarMetodos(String contenido, Clase clase){
    // Analizar métodos
    if (clase != null) {
        if ("class".equals(clase.getTypeClass())) {
//            Pattern patronMetodo = Pattern.compile("(public|protected|private) (\\w+) (\\w+)\\((.*?)\\)", Pattern.DOTALL);
            Pattern patronMetodo = Pattern.compile("(?:@[^\\n]*)*(public|protected|private|void) (\\w+) (\\w+)\\((.*?)\\)", Pattern.DOTALL);
            Matcher matcherMetodo = patronMetodo.matcher(contenido);

            while (matcherMetodo.find()) {
                Metodo metodo = new Metodo(); // Declarar el objeto Metodo dentro del bucle

                metodo.setNombre(matcherMetodo.group(3));
                metodo.setTipoRetorno(matcherMetodo.group(2));
                metodo.setAccessModifier(matcherMetodo.group(1)); // Agregar modificador de acceso

                // Analizar parámetros
                String[] parametros = matcherMetodo.group(4).split(",");
                for (String parametro : parametros) {
                    if (!parametro.trim().isEmpty()) {
                        String[] partes = parametro.trim().split("\\s+");
                        ParametroMetodo parametroMetodo = new ParametroMetodo(partes[1], partes[0]);
                        metodo.agregarParametro(parametroMetodo);
                    }
                }

                // Analizar y Obtener contenido del metodo
                String patronContenidoMetodo = matcherMetodo.group(1) + " " + metodo.getTipoRetorno() + " " + metodo.getNombre() + "\\((.*?)\\)\\s*\\{([^}]*)\\}";
                Pattern pattern = Pattern.compile(patronContenidoMetodo, Pattern.DOTALL | Pattern.MULTILINE);
                Matcher matcherContenidoMetodo = pattern.matcher(contenido);
                if (matcherContenidoMetodo.find()) {
                    String contenidoMetodo = matcherContenidoMetodo.group(2).trim();
                    metodo.setContenido(contenidoMetodo);
                }

                // Analizar firma del método
                String methodSignature = matcherMetodo.group(0).trim();
                metodo.setMethodSignature(methodSignature);

                // Analizar anotaciones del método
                String[] lineas = contenido.split("\n");
                for (int i = 0; i < lineas.length - 1; i++) {
                    if (lineas[i].trim().startsWith("@") && lineas[i + 1].trim().startsWith(methodSignature)) {
                        metodo.setAnotation(lineas[i].trim());
                    }
                }
                clase.addMetodo(metodo);
            }
        }
//        ...
        if ("interface".equals(clase.getTypeClass())) {
            Pattern patronMetodoInterface = Pattern.compile("(?:@[^\\n]*)*(public|protected|private) (\\w+(?:<.*?>)?) (\\w+)\\s*\\((.*?)\\)\\s*;", Pattern.DOTALL);
            Matcher matcherMetodoInterface = patronMetodoInterface.matcher(contenido);

            while (matcherMetodoInterface.find()) {
                Metodo metodo = new Metodo(); // Declarar el objeto Metodo dentro del bucle

                metodo.setNombre(matcherMetodoInterface.group(3));
                metodo.setTipoRetorno(matcherMetodoInterface.group(2));
                metodo.setAccessModifier(matcherMetodoInterface.group(1)); // Agregar modificador de acceso

                // Analizar parámetros
                String[] parametros = matcherMetodoInterface.group(4).split(",");
                for (String parametro : parametros) {
                    if (!parametro.trim().isEmpty()) {
                        String[] partes = parametro.trim().split("\\s+");
                        ParametroMetodo parametroMetodo = new ParametroMetodo(partes[1], partes[0]);
                        metodo.agregarParametro(parametroMetodo);
                    }
                }

                // Analizar firma del método
                String firmaMetodo = matcherMetodoInterface.group(0).trim();
                metodo.setMethodSignature(firmaMetodo);

                // Analizar anotaciones del método
                String nameMethodSingnature = firmaMetodo.replaceAll("\\(.*?\\)", "");
                String patterAnotation = "(@[\\w]+)\\s+public\\s+void\\s+"+nameMethodSingnature+"\\(\\)\\s*\\{";
                Pattern pattern = Pattern.compile(patterAnotation, Pattern.DOTALL | Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(contenido);

                if (matcher.find()) {
                    String anotacion = matcher.group(1);
                    System.out.println("Se encontró la anotación: " + anotacion);
                    metodo.setAnotation(anotacion);
                }



                clase.addMetodo(metodo);
            }
        }
    }
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




    private void analyzeVariables(String content, Clase clase) {

        Pattern patronVariable = Pattern.compile("(?:@[^\\n]*)*\\s*(public|protected|private) (\\w+) (\\w+);");
        Matcher matcherVariable = patronVariable.matcher(content);

        while (matcherVariable.find()) {
            Variable variable = new Variable();
            variable.setTipo(matcherVariable.group(2));
            variable.setNombre(matcherVariable.group(3));
            variable.setAccessModifier(matcherVariable.group(1));

            Pattern patronAnotacion = Pattern.compile("@[^\\n]*");
            Matcher matcherAnotacion = patronAnotacion.matcher(matcherVariable.group(0));

            StringBuilder anotations = new StringBuilder();
            while (matcherAnotacion.find()) {
                anotations.append(matcherAnotacion.group()).append(" ");
            }
            variable.setAnotation(anotations.toString().trim());
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
