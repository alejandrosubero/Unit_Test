package com.example.maven;


import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PomParser {

    public static ProjectInfo parsePom(File pomFile) {
        ProjectInfo projectInfo = new ProjectInfo();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(pomFile);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            // Extract project name
            String name = getTextContent(root, "name");
            if (name == null || name.isEmpty()) {
                name = getTextContent(root, "artifactId");
            }
            projectInfo.setName(name);

            String projectVersion = getTextContent(root, "version");
            projectInfo.setVersion(projectVersion);

            // Extract description
            projectInfo.setDescription(getTextContent(root, "description"));

            // Extract Java version from <properties>
            Element properties = getChildElement(root, "properties");
            if (properties != null) {
                String javaVersion = getTextContent(properties, "maven.compiler.source");
                if (javaVersion == null) {
                    javaVersion = getTextContent(properties, "java.version");
                }
                projectInfo.setJavaVersion(javaVersion);
            }

            // Extract dependencies
            NodeList dependenciesNodes = root.getElementsByTagName("dependency");
            for (int i = 0; i < dependenciesNodes.getLength(); i++) {
                Node depNode = dependenciesNodes.item(i);
                if (depNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element depElement = (Element) depNode;

                    String groupId = getTextContent(depElement, "groupId");
                    String artifactId = getTextContent(depElement, "artifactId");
                    String version = getTextContent(depElement, "version");

                    DependencyInfo dependency = new DependencyInfo(groupId, artifactId, version);
                    projectInfo.getDependencies().add(dependency);
                }
            }

        } catch (Exception e) {
            System.err.println("Error parsing pom.xml: " + e.getMessage());
            e.printStackTrace();
        }

        return projectInfo;
    }

    // Utility method to get text content by tag name
    private static String getTextContent(Element parent, String tag) {
        NodeList list = parent.getElementsByTagName(tag);
        if (list.getLength() > 0) {
            Node node = list.item(0);
            return node.getTextContent().trim();
        }
        return null;
    }

    // Utility method to get direct child element
    private static Element getChildElement(Element parent, String tagName) {
        NodeList children = parent.getElementsByTagName(tagName);
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getParentNode().equals(parent)) {
                return (Element) children.item(i);
            }
        }
        return null;
    }
}
