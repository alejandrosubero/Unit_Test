package com.unitTestGenerator.analyzers.services;

import com.unitTestGenerator.pojos.*;
import com.unitTestGenerator.services.MethodService;
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
        analyzeMethods(content, clase);
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
        clase.setRawClass(content);
        analyzePackage(content, clase);
        analyzeNamesAndTypeOfClass(content, clase);
        analyzeConstructors(content, clase);
        analyzeMethods(content, clase);
        analyzeVariables(content, clase);
        analyzeControlStructure(content, clase);
        return postClassMethodAnalysis(clase);
    }

    private void analyzePackage(String contenido, Clase clase) {
        Pattern patronPaquete = Pattern.compile("package (.*?);");
        Matcher matcherPaquete = patronPaquete.matcher(contenido);

        while (matcherPaquete.find()) {
            clase.setPaquete(matcherPaquete.group(1));
        }
    }

    private void analyzeNamesAndTypeOfClass(String contenido, Clase clase) {
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

    private void analyzeConstructors(String contenido, Clase clase) {
        Pattern patronConstructor = Pattern.compile("public (\\w+)\\((.*?)\\)\\s*\\{(.*?)\\}", Pattern.DOTALL);
        Matcher matcherConstructor = patronConstructor.matcher(contenido);

        while (matcherConstructor.find()) {
            Constructor constructor = new Constructor();
            constructor.setCostructorSignature(matcherConstructor.group(0));

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

            String contenidoEntreLlaves = matcherConstructor.group(3).trim();
            if (contenidoEntreLlaves.isEmpty()) {
                constructor.setCostructorContent("{}");
            } else {
                constructor.setCostructorContent(contenidoEntreLlaves);
            }
            clase.addConstructor(constructor);
        }
    }

    private void analyzeMethods(String contenido, Clase clase){
    if (clase != null) {
        if ("class".equals(clase.getTypeClass())) {
            Pattern patronMetodo = Pattern.compile("(?:@[^\\n]*)*(public|protected|private|void) (\\w+) (\\w+)\\((.*?)\\)", Pattern.DOTALL);
            Matcher matcherMetodo = patronMetodo.matcher(contenido);

            while (matcherMetodo.find()) {
                Metodo metodo = new Metodo();
                this.analyzeMethodBasic( metodo, matcherMetodo);
                this.analyzeMethodParameters( metodo, matcherMetodo);
                this.analyzeMethodContent(contenido, metodo, matcherMetodo);
                metodo.setMethodSignature(matcherMetodo.group(0).trim());
                this.analyzeMethodAnotations(contenido,metodo, metodo.getMethodSignature());
                clase.addMetodo(metodo);
            }
        }

        if ("interface".equals(clase.getTypeClass())) {
            Pattern patronMetodoInterface = Pattern.compile("(?:@[^\\n]*)*(public|protected|private) (\\w+(?:<.*?>)?) (\\w+)\\s*\\((.*?)\\)\\s*;", Pattern.DOTALL);
            Matcher matcherMetodoInterface = patronMetodoInterface.matcher(contenido);

            while (matcherMetodoInterface.find()) {
                Metodo metodo = new Metodo();
                this.analyzeMethodBasic( metodo, matcherMetodoInterface);
                this.analyzeMethodParameters( metodo, matcherMetodoInterface);
                metodo.setMethodSignature(matcherMetodoInterface.group(0).trim());
                this.analyzeMethodAnotations(contenido,metodo, metodo.getMethodSignature());
                clase.addMetodo(metodo);
            }
        }
    }
}

    private void analyzeMethodBasic(Metodo metodo, Matcher matcherMetodo) {
        metodo.setNombre(matcherMetodo.group(3));
        metodo.setTipoRetorno(matcherMetodo.group(2));
        metodo.setAccessModifier(matcherMetodo.group(1));
    }

    private void analyzeMethodAnotations(String contenido, Metodo metodo, String methodSignature) {
        String[] lineas = contenido.split("\n");
        for (int i = 0; i < lineas.length - 1; i++) {
            if (lineas[i].trim().startsWith("@") && lineas[i + 1].trim().startsWith(methodSignature)) {
                metodo.setAnotation(lineas[i].trim());
            }
        }
    }

    private void analyzeMethodContent(String contenido, Metodo metodo, Matcher matcherMetodo) {
        String patronContenidoMetodo = matcherMetodo.group(1) + " " + metodo.getTipoRetorno() + " " + metodo.getNombre() + "\\((.*?)\\)\\s*\\{([^}]*)\\}";
        Pattern pattern = Pattern.compile(patronContenidoMetodo, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcherContenidoMetodo = pattern.matcher(contenido);
        if (matcherContenidoMetodo.find()) {
            String contenidoMetodo = matcherContenidoMetodo.group(2).trim();
            metodo.setContenido(contenidoMetodo);
        }
    }

    private void analyzeMethodParameters(Metodo metodo, Matcher matcherMetodo) {
        String[] parametros = matcherMetodo.group(4).split(",");
        for (String parametro : parametros) {
            if (!parametro.trim().isEmpty()) {
                String[] partes = parametro.trim().split("\\s+");
                ParametroMetodo parametroMetodo = new ParametroMetodo(partes[1], partes[0]);
                metodo.agregarParametro(parametroMetodo);
            }
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

    private void analyzeControlStructure(String contenido, Clase clase) {
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
