package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IBaseModel;
import com.unitTestGenerator.pojos.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateMethodService implements IBaseModel {

    private Project project;
    private static GenerateMethodService instance;
    private MockitoWhen mockitoWhen;
    private GenerateContentMockService generateContentMockService;
    private GenerateContentWithoutMockService contentWithoutMockService;

    private GenerateMethodService(){
            this.mockitoWhen = new MockitoWhen();
            this.generateContentMockService = GenerateContentMockService.getInstance();
            this.contentWithoutMockService = GenerateContentWithoutMockService.getInstance();
    }

    public static GenerateMethodService getInstance(){
        if(instance == null){
            instance = new GenerateMethodService();
        }
        return instance;
    }

    private void setProject(Project project){
       try {
           this.project = project;
           if(this.generateContentMockService != null) {
               this.generateContentMockService.setProject(this.project);
           }
           if(this.contentWithoutMockService != null) {
               this.contentWithoutMockService.setProject(this.project);
           }
       }catch (Exception e){
           System.err.println("Error in Generate Method Service: Method: setProject...");
           System.err.println("Error: "+ e.getMessage());
       }

    }

    public TestFileContent generateMethods( Clase clase, Project project,  TestFileContent fileContent ){

        this.setProject(project);

        StringBuilder contex = new StringBuilder();

        if (clase.getTestMethod().toLowerCase().equals("all")) {
            clase.getMetodos().forEach(metodo -> {
                fileContent.update(this.setContent(fileContent, clase, project, metodo));
            });
        } else {
            for (Metodo metodo : clase.getMetodos()) {
                if (clase.getTestMethod().toLowerCase().equals(metodo.getNombre().toLowerCase())) {
                    fileContent.update(this.setContent(fileContent, clase, project, metodo));
                }
            }
        }
        contex.append("\n");

        if (clase.getUseMock()) {
            fileContent.addVariable("\n");
            fileContent.addVariable(this.mockitoWhen.getMokitoSetUpBeforeEach(false));
        }
        return fileContent;
    }

    private TestFileContent setContent(TestFileContent fileContent,  Clase clase, Project project,Metodo metodo){
        String variable = MethodParameterObject.getInstance().methodParameterObject(metodo, project, clase.getUseMock());
        if (variable != null) {
            List<String> mockDeclarations = new ArrayList<>();
            String regex = "(?s)@Mock\\s+private\\s+\\w+\\s+\\w+;";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(variable);

            while (matcher.find()) {
                mockDeclarations.add(matcher.group().trim());
            }
            for (String declaration : mockDeclarations) {
                if (!fileContent.getTestsClassVariables().contains(declaration)) {
                    fileContent.addVariable(new StringBuffer("\t").append(declaration).toString());
                    fileContent.addImport(this.generateContentMockService.getImportInMockObject(declaration, project));
                }
            }
        }
        fileContent.addMethod(this.obtenerContenidoMetodo(metodo, clase, fileContent));
        return fileContent;
    }

    private  String obtenerContenidoMetodo(Metodo metodo, Clase clase, TestFileContent fileContent) {
        String methodName = String.format("%s%s", metodo.getNombre().substring(0, 1).toUpperCase(), metodo.getNombre().substring(1));
        String content = generarContenidoMetodoPrueba(metodo, clase, fileContent);
        String methodoTest = String.format("\n \t@Test\n \tpublic void test%s() {\n  \t%s \n }\n", methodName,content);
        return methodoTest;
    }

    private String generarContenidoMetodoPrueba(Metodo metodo, Clase clase, TestFileContent fileContent) {
        StringBuffer contenido = new StringBuffer();
        String conten = clase.getUseMock()? this.generateContentMockService.generateContentMock(metodo, clase, fileContent): this.contentWithoutMockService.generarContenidoSinMock(metodo,clase, fileContent);
        contenido.append(conten);
        return contenido.toString();
    }


}
