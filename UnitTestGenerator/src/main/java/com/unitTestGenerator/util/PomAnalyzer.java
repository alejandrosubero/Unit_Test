package com.unitTestGenerator.util;

import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class PomAnalyzer {

    private static PomAnalyzer instance;

    private PomAnalyzer(){

    }

    public static PomAnalyzer getInstance() {
        if(instance == null){
            instance = new PomAnalyzer();
        }
        return instance;
    }

    private  final String JUNIT_DEPENDENCY =
            "<dependency>\n" +
                    "    <groupId>org.junit.jupiter</groupId>\n" +
                    "    <artifactId>junit-jupiter-api</artifactId>\n" +
                    "    <version>5.8.2</version>\n" +
                    "    <scope>test</scope>\n" +
                    "</dependency>";

    private  final String MOCK_DEPENDENCY =
            "<dependency>\n" +
                    "    <groupId>org.mockito</groupId>\n" +
                    "    <artifactId>mockito-junit-jupiter</artifactId>\n" +
                    "    <version>4.11.0</version>\n" +
                    "    <scope>test</scope>\n" +
                    "</dependency>";

    private  final String MOCK_DEPENDENCY_core =" <dependency>\n" +
            "            <groupId>org.mockito</groupId>\n" +
            "            <artifactId>mockito-core</artifactId>\n" +
            "            <version>4.0.0</version>\n" +
            "            <scope>test</scope>\n" +
            "        </dependency>";

    private  final String JUNIT_DEPENDENCY_no_api = " <dependency>\n" +
            "            <groupId>org.junit.jupiter</groupId>\n" +
            "            <artifactId>junit-jupiter</artifactId>\n" +
            "            <version>5.8.2</version>\n" +
            "            <scope>test</scope>\n" +
            "        </dependency>"  ;


    public  void agregarDependencias(String rutaProyecto) {

        boolean existeDependency = false;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(rutaProyecto + "/pom.xml"));

            Element raiz = documento.getDocumentElement();
            NodeList dependencies = raiz.getElementsByTagName("dependencies");

            if (dependencies.getLength() == 0) {
                Element dependenciesElement = documento.createElement("dependencies");
                raiz.appendChild(dependenciesElement);
            }

            if (!existeDependencia(documento, JUNIT_DEPENDENCY)) {
                existeDependency = true;
                agregarDependencia(documento,
                        "org.junit.jupiter",
                        "junit-jupiter-api",
                        "5.8.2",
                        "test");

                agregarDependencia(documento,
                        "junit",
                        "junit",
                        "4.13.2",
                        "test");
            }

            if (!existeDependencia(documento, MOCK_DEPENDENCY)) {
                existeDependency = true;
                agregarDependencia(documento,
                        "org.mockito",
                        "mockito-junit-jupiter",
                        "4.11.0",
                        "test");
            }


            if (!existeDependencia(documento, MOCK_DEPENDENCY_core)) {
                existeDependency = true;
                agregarDependencia(documento,
                        "org.mockito",
                        "mockito-core",
                        "4.0.0",
                        "test");
            }


            if (!existeDependencia(documento, JUNIT_DEPENDENCY_no_api)) {
                existeDependency = true;
                agregarDependencia(documento,
                        "org.junit.jupiter",
                        "junit-jupiter",
                        "5.8.2",
                        "test");
            }


            if (existeDependency) {
                saveChanges(documento, rutaProyecto);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error al analizar el archivo pom.xml: " + e.getMessage());
        }
    }

    private  boolean existeDependencia(Document documento, String dependencia) {
        NodeList dependencies = documento.getElementsByTagName("dependency");

        for (int i = 0; i < dependencies.getLength(); i++) {
            Element dependency = (Element) dependencies.item(i);

            // Verificar si dependency es null
            if (dependency != null) {
                NodeList groupIdElements = dependency.getElementsByTagName("groupId");
                NodeList artifactIdElements = dependency.getElementsByTagName("artifactId");

                // Verificar si groupId y artifactId existen
                if (groupIdElements.getLength() > 0 && artifactIdElements.getLength() > 0) {
                    String groupId = groupIdElements.item(0).getTextContent();
                    String artifactId = artifactIdElements.item(0).getTextContent();

                    // Verificar si groupId y artifactId no son null
                    if (groupId != null && artifactId != null) {
                        if (groupId.equals(dependencia.split("<groupId>")[1].split("</groupId>")[0]) &&
                                artifactId.equals(dependencia.split("<artifactId>")[1].split("</artifactId>")[0])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private  void agregarDependencia(Document documento, String groupId, String artifactId, String version, String scope) {
        Element dependencies = (Element) documento.getElementsByTagName("dependencies").item(0);
        Element nuevaDependencia = documento.createElement("dependency");

        Element grupoId = documento.createElement("groupId");
        grupoId.setTextContent(groupId);
        nuevaDependencia.appendChild(grupoId);

        Element artifactIdElement = documento.createElement("artifactId");
        artifactIdElement.setTextContent(artifactId);
        nuevaDependencia.appendChild(artifactIdElement);

        Element versionElement = documento.createElement("version");
        versionElement.setTextContent(version);
        nuevaDependencia.appendChild(versionElement);

        Element scopeElement = documento.createElement("scope");
        scopeElement.setTextContent(scope);
        nuevaDependencia.appendChild(scopeElement);

        dependencies.appendChild(nuevaDependencia);
    }



    private  void saveChanges(Document documento, String rutaProyecto) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(rutaProyecto + "/pom.xml"));
            transformer.transform(source, result);
            System.out.println("Dependencias agregadas correctamente.");
        } catch (TransformerException e) {
            System.out.println("Error al guardar cambios en el archivo pom.xml: " + e.getMessage());
        }
    }
}
