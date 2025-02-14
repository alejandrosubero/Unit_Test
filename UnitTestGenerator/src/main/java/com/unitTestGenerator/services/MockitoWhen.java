package com.unitTestGenerator.services;

import com.unitTestGenerator.interfaces.IClassObject;
import com.unitTestGenerator.util.IBaseModel;
import com.unitTestGenerator.interfaces.IMethodServiceTools;
import com.unitTestGenerator.interfaces.IReturnType;
import com.unitTestGenerator.pojos.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockitoWhen implements IBaseModel, IMethodServiceTools, IClassObject, IReturnType {

    public MockitoWhen() {
    }

    public String generateCallMethodMock(String testMethod, Metodo metodo, Project project, String retrunValue, TestFileContent fileContent, String methodName) {

        StringBuilder content = new StringBuilder();
        String tipoRetorno = "";

        if(metodo != null && metodo.getTipoRetorno() != null){
             tipoRetorno = metodo.getTipoRetorno();
        }

        if (retrunValue == null && tipoRetorno !=null && tipoRetorno.contains("Optional")){
            content.append( this.optionalRetrunValue(testMethod, tipoRetorno, fileContent, metodo));
        } else if (methodName != null && metodo.getContenido().contains("Optional") && !tipoRetorno.contains("Optional")) {
            if (methodName.equals("findById")){
                content.append(this.methodNameEqualsFindById( testMethod,  metodo,  fileContent));
            }
        }else {
            content.append("\tMockito.when(").append(testMethod).append(")").append(".thenReturn(");
            String valueReturn = retrunValue != null ? retrunValue : generateRetrunValue(metodo, project);
            content.append(valueReturn).append(");");
        }
        return content.toString();
    }

    public String generateCallMethodMockDoNothing( String methodNameParts, String classNameCamelCase, String parametrosMethodTest) {
        return new StringBuilder("Mockito.doNothing().when(").append(classNameCamelCase).append(")").append(String.format(".%s(%s);" ,methodNameParts, parametrosMethodTest)).toString();
    }

    public String generateCallAssertType(Metodo metodo, Project project, String result) {
        StringBuilder contex = new StringBuilder();
        String valueReturn = generateRetrunValue(metodo, project);
        contex.append("\n").append("\t").append(getAssertType(metodo.getTipoRetorno(), valueReturn, result));
        return contex.toString();
    }

    public String generateRetrunValue (Metodo metodo, Project project) {
        String returnType = metodo.getTipoRetorno();
        Clase returnClass = null;

        if(!isValidTypeReturn(returnType)){
            returnClass = project.getClass(returnType);
        }

        if (returnClass != null) {
            // Generate a return class
            return generateNewClassObject(returnClass);
        } else {
            // return default value
            return getDefaultValue(returnType);
        }
    }

    public String verificarMock(String variableInstanceName, String methodName, List<ParametroMetodo> parametros) {
        StringBuilder contenido = new StringBuilder();

        contenido.append("Mockito.verify(").append(variableInstanceName).append(", Mockito.times(1)).").append(methodName).append("(");

        // Agregar los parámetros
        for (int i = 0; i < parametros.size(); i++) {
            ParametroMetodo parametro = parametros.get(i);
            contenido.append(parametro.getNombre());

            if (i < parametros.size() - 1) {
                contenido.append(", ");
            }
        }
        contenido.append(");");
        return contenido.toString();
    }

    public String getMokitoSetUpBeforeEach(boolean isAutoCloseable){
        StringBuilder mokitoSetUpBeforeEach = new StringBuilder("\n");
        if(isAutoCloseable) {
            mokitoSetUpBeforeEach.append("\t").append("private AutoCloseable closeable;").append("\n").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t@BeforeEach").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\tpublic void setUp() {").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t\tcloseable = MockitoAnnotations.openMocks(this);").append("\n").append("}").append("\n").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t@AfterEach").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\tpublic void tearDown() throws Exception {").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t\tcloseable.close();").append("\n").append("}").append("\n").append("\n");
        }else {
            mokitoSetUpBeforeEach.append("\t").append("\t@BeforeEach").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\tpublic void setUp() {").append("\n");
            mokitoSetUpBeforeEach.append("\t").append("\t\tMockitoAnnotations.openMocks(this);").append("\n").append("\t").append("}").append("\n").append("\n");
        }
        return mokitoSetUpBeforeEach.toString();
    }

    private String methodNameEqualsFindById(String testMethod, Metodo metodo, TestFileContent fileContent) {
        StringBuilder content = new StringBuilder();
      try{
          String data = "";
          String tipoRetorno ="";
          Pattern patron = Pattern.compile("\\((.*?)\\)");
          Matcher matcher = patron.matcher(testMethod);
          String textoModificado = fileContent.getTestsClassVariables().replaceAll("@Mock\\s+private\\s+Long\\s+id;", "");
          String typeOfOptionalName = metodo.getTipoRetorno().toLowerCase() + "Entity";

          if(metodo != null && metodo.getTipoRetorno() != null){
              tipoRetorno = "Optional<" + metodo.getTipoRetorno() + ">";
          }

          fileContent.setTestsClassImport(textoModificado);

          if (matcher.find()) {
              data = matcher.group(1);
          }
          String resultado = data != null ? testMethod.replace(data, "Mockito.any(Long.class)") : testMethod;

          content.append("Long id = 1L;").append("\n");
          content.append(typeOfOptionalName).append(".setId(id);").append("\n");
          content.append(this.optionalRetrunValue(resultado, tipoRetorno, fileContent, metodo));
      }catch (Exception e){
          System.err.println("Fail in mokitoWhen in method Name is Equals FindById...");
          System.err.println("Error: "+ e.getMessage());
          return content.toString();
      }
        return content.toString();
    }

    private String optionalRetrunValue(String testMethod, String tipoRetorno,  TestFileContent fileContent, Metodo metodo){

        StringBuilder optionalRetrunValue = new StringBuilder();
        String typeOfOptionalReturn="";
        String typeOfOptionalName ="";
        String typeOfOptional ="null";

        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(tipoRetorno);

        if (matcher.find()) {
            typeOfOptional = matcher.group(1);
        }

        if(!typeOfOptional.equals("null")){
            typeOfOptionalName =  typeOfOptional.toLowerCase()+"Entity";
            typeOfOptionalReturn = "optional"+typeOfOptional;
            fileContent.addVariable(GeneratedVariableService.getInstance().generateVariable(
                    typeOfOptional,
                    typeOfOptionalName,
                    false, true));
        }
        optionalRetrunValue.append("\n")
                .append(tipoRetorno).append(" ")
                .append(typeOfOptionalReturn).append(" = ")
                .append("Optional.of(").append(typeOfOptionalName).append(");")
                .append("\n");

        optionalRetrunValue.append("\tMockito.when(").append(testMethod).append(")").append(".thenReturn(");
        optionalRetrunValue.append(typeOfOptionalReturn).append(");");

        return optionalRetrunValue.toString();

    }

}

