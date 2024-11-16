package com.unitTestGenerator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
        this.agregarDependencia("org.junit.jupiter", "junit-jupiter-api", "5.8.2");
        this.agregarDependencia("junit", "junit", "4.13.2");
        this.agregarDependencia(   "org.mockito", "mockito-junit-jupiter", "4.11.0");
        this.analizarEstructura();
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

    ..fail
    // Método para agregar dependencias
    //dependencies { }
    public void agregarDependencia(String grupo, String artefacto, String version) {
        try {
            String contenido = FileUtils.readFileToString(archivoGradle, "UTF-8");

            if(dependenciaExiste( contenido , grupo, artefacto, version)){
                String dependencia = String.format("implementation '%s:%s:%s'", grupo, artefacto, version);
                contenido += stringEnsamble("\ndependencies {\n",
                        dependencia,"\n",
                        "}");
                FileWriter escritor = new FileWriter(archivoGradle);
                escritor.write(contenido);
                escritor.close();
                System.out.println("Dependencia agregada con éxito");
            }
        } catch (IOException e) {
            System.out.println("Error al agregar dependencia: " + e.getMessage());
        }
    }

    // Método para eliminar dependencias
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

    // Método para actualizar dependencias
    public void actualizarDependencia(String grupo, String artefacto, String versión) {
        eliminarDependencia(grupo, artefacto);
        agregarDependencia(grupo, artefacto, versión);
    }

    // Método para analizar estructura del archivo build.gradle
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
}
