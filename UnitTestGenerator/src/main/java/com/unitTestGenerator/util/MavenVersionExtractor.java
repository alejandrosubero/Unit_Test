package com.unitTestGenerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

public class MavenVersionExtractor {

    /**
     * Obtiene la versión desde el pom.xml en la raíz del proyecto.
     * Ideal para desarrollo y pruebas.
     *
     * @return String con la versión o "0.0.0-SNAPSHOT" si no puede leerse
     */
    public static String getVersion() {
        return getVersionFromPomFile("pom.xml");
    }

    /**
     * Obtiene la versión desde un archivo pom.xml específico.
     *
     * @param pomFilePath Ruta al archivo pom.xml
     * @return String con la versión o "0.0.0-SNAPSHOT" si no puede leerse
     */
    public static String getVersionFromPomFile(String pomFilePath) {
        try (FileInputStream fis = new FileInputStream(new File(pomFilePath))) {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(fis);

            return extractVersion(doc);
        } catch (Exception e) {
            System.err.println("Error leyendo " + pomFilePath + ": " + e.getMessage());
            return "0.0.0-SNAPSHOT";
        }
    }

    /**
     * Intenta obtener la versión desde el pom.xml en el classpath.
     * Útil cuando la aplicación se ejecuta desde un JAR empaquetado.
     * Reemplaza 'tu-grupo' y 'tu-artifacto' con los valores de tu proyecto.
     *
     * @return String con la versión o "0.0.0-SNAPSHOT" si no puede leerse
     */
    public static String getVersionFromClasspath() {
        String groupId = "com.unitTestGenerator";      // ← CAMBIA ESTO
        String artifactId = "unit"; // ← CAMBIA ESTO

        String pomPath = String.format("/META-INF/maven/%s/%s/pom.xml", groupId, artifactId);

        try (InputStream is = MavenVersionExtractor.class.getResourceAsStream(pomPath)) {
            if (is == null) {
                // Si no encuentra el pom.xml, intenta leer desde el MANIFEST.MF
                return getVersionFromManifest();
            }

            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(is);

            return extractVersion(doc);
        } catch (Exception e) {
            System.err.println("Error leyendo desde classpath: " + e.getMessage());
            return "0.0.0-SNAPSHOT";
        }
    }

    private static String getVersionFromManifest() {
        try {
            InputStream manifestStream = MavenVersionExtractor.class.getResourceAsStream("/META-INF/MANIFEST.MF");
            if (manifestStream != null) {
                java.util.jar.Manifest manifest = new java.util.jar.Manifest(manifestStream);
                String version = manifest.getMainAttributes().getValue("Implementation-Version");
                if (version != null && !version.isEmpty()) {
                    return version;
                }
            }
        } catch (Exception e) {
            // Ignorar errores
        }
        return "0.0.0-SNAPSHOT";
    }

    private static String extractVersion(Document doc) throws Exception {
        XPath xPath = XPathFactory.newInstance().newXPath();

        // Ignora namespaces para simplificar la búsqueda
        String version = (String) xPath.evaluate("/*[local-name()='project']/*[local-name()='version']/text()",
                doc, XPathConstants.STRING);

        // Si no hay version directa, busca en parent (para proyectos hijo)
        if (version == null || version.trim().isEmpty()) {
            version = (String) xPath.evaluate("/*[local-name()='project']/*[local-name()='parent']/*[local-name()='version']/text()",
                    doc, XPathConstants.STRING);
        }

        return version != null && !version.trim().isEmpty() ? version.trim() : "0.0.0-SNAPSHOT";
    }

//    public static void main(String[] args) {
//        System.out.println("Desde pom.xml: " + getVersion());
//        System.out.println("Desde classpath: " + getVersionFromClasspath());
//    }
}