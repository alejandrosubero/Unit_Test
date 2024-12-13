package com.unitTestGenerator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.unitTestGenerator.builders.BuildTestFile;
import com.unitTestGenerator.interfaces.IBaseModel;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GradleAnalyzer implements IBaseModel {

    private File archivoGradle;

    public GradleAnalyzer(File archivoGradle) {
        this.archivoGradle = archivoGradle;
    }

    public void started(){

        try {
            String contenido = FileUtils.readFileToString(archivoGradle, "UTF-8");
            List<String> contenidoList = FileUtils.readLines(archivoGradle, "UTF-8");

            List<String> dependencesList1 =  this.addDependences("org.junit.jupiter", "junit-jupiter", "5.8.2",  contenidoList,  contenido);
            List<String> dependencesList3  =  this.addDependences(   "org.mockito", "mockito-junit-jupiter", "3.12.4", dependencesList1,  contenido);
            List<String> dependencesList4  =  this.addDependences(   "org.mockito", "mockito-core", "3.12.4", dependencesList3,  contenido);
            this.analizarEstructura();
            String newContenido =  this.listStringStructureToColummString(dependencesList3);

            BuildTestFile.getInstance().writefiles( archivoGradle,  newContenido);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean dependenciaExiste(String contenido , String grupo, String artefacto, String version) {
        try {
            String dependencia = String.format("implementation '%s:%s:%s'", grupo, artefacto, version);
            Boolean isPresent = contenido.contains(dependencia);
            return isPresent;
        } catch (Exception e) {
            System.out.println("Error al leer contenido: " + e.getMessage());
            return false;
        }
    }

    public  List<String> addDependences(String grupo, String artefacto, String version,  List<String> contenidoList,  String contenido) {
        try {
            if(contenido != null && !contenido.equals("") && contenidoList != null &&
                    !contenidoList.isEmpty() && !dependenciaExiste( contenido , grupo, artefacto, version)){

                String newDependencie = String.format("implementation '%s:%s:%s'", grupo, artefacto, version);

                Optional<Integer> indice = contenidoList.stream()
                        .map(String::toLowerCase)
                        .map(line -> line.contains("dependencies {") ? line : null)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .map(contenidoList::indexOf);

                String newDependencieQ = stringEnsamble(indentation(1), newDependencie);
                indice.ifPresent(i -> contenidoList.add(i + 1, newDependencieQ));
            }
        } catch (Exception e) {
            System.out.println("Error al agregar dependencia: " + e.getMessage());
        }
        return contenidoList;
    }



    public void analizarEstructura() {
        try {
            Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoGradle);
            Element raiz = documento.getDocumentElement();
            NodeList dependencias = raiz.getElementsByTagName("dependency");
            System.out.println("Dependencias:");

            for (int i = 0; i < dependencias.getLength(); i++) {
                Element dependencia = (Element) dependencias.item(i);
                System.out.println(dependencia.getAttribute("groupId") + ":" +
                        dependencia.getAttribute("artifactId") + ":" +
                        dependencia.getAttribute("version"));
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error al analizar estructura: " + e.getMessage());
        }
    }


    public void eliminarDependencia(String grupo, String artefacto) {
        try {
            String contenido = FileUtils.readFileToString(archivoGradle, "UTF-8");
            contenido = contenido.replaceAll("implementation '$grupo:$artefacto:[^']+'", "");
            FileWriter escritor = new FileWriter(archivoGradle);
            escritor.write(contenido);
            escritor.close();
            System.out.println("Dependencia eliminada con éxito");
        } catch (IOException e) {
            System.out.println("Error al eliminar dependencia: " + e.getMessage());
        }
    }

}
