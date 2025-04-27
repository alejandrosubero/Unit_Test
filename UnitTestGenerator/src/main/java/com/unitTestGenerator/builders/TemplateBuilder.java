package com.unitTestGenerator.builders;

import com.unitTestGenerator.analyzers.services.interfaces.IClassDetailBuilder;
import com.unitTestGenerator.builders.interfaces.ITemplateBuilderRelation;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Constructor;
import com.unitTestGenerator.uml.sevices.PrintClassToUML;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class TemplateBuilder implements ITemplateBuilderRelation {

    public TemplateBuilder() {
    }

    private String templateName = "template1.html";


    public void buildClassDetailHtml(Clase classs) {
        String templete = ReadResourceFile();

        if (classs != null) {
            templete = this.getStructureBase(templete, classs);
            templete = this.umlData(templete, classs);
            templete = this.costructorElement(templete, classs);
            templete = this.getImports(templete, classs);
            templete = this.getStructureInterface(templete, classs);
            templete = this.getStructureExtends(templete, classs);
            templete = this.getClassAnotations(templete, classs);
            classs.setClassTemplate(templete);
        }
    }


    public static String elementSpecialChars(String element) {
        if(element.contains("&") || element.contains("<") || element.contains(">") ){
            return element
                    .replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;");
        }
        return element;
    }


    private String relationHtml(String retation){
        StringBuffer relationBuffer = new StringBuffer();
        String temp = retation;
        String[] partTemp = temp.split("\n");

        for(String attribute: partTemp){
            String tempA =  "<div class=\"uml-attributes\"> @aTemp@ </div>";
            tempA = tempA.replace("@aTemp@", elementSpecialChars(attribute));
            relationBuffer.append(tempA);
        }
        return relationBuffer.toString();
    }

    private String ReadResourceFile (){
        // Obtener el archivo como InputStream
        String contenido ="";
        try (InputStream inputStream = IClassDetailBuilder.class.getClassLoader().getResourceAsStream(templateName)) {

            if (inputStream == null) {
                throw new RuntimeException("Archivo no encontrado: "+templateName);
            }
            contenido = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contenido;
    }

    private String getStructureBase( String templete, Clase classs){
        templete = templete.replace("@NombreClaseTitle@", classs.getNombre());
        if(classs != null && (classs.getPaquete() !=null &&  !classs.getPaquete().equals(""))) {
            templete = templete.replace("@paquetico@", classs.getPaquete());
        }
        templete = templete.replace("@ClassType@", classs.getTypeClass());
        String signature = classs.getClassSignatureLine().replace("{","");
        templete = templete.replace("@classsignatureline@", this.elementSpecialChars(signature));
        templete = templete.replace("@classclasspath@", classs.getClassPath());
        templete = templete.replace("@NombreClase@", classs.getNombre());
        return templete;
    }

    private String umlData( String templete, Clase classs){
        PrintClassToUML printClassToUML =  ContextIOC.getInstance().getClassInstance(PrintClassToUML.class);
        templete = getAttributesUml(printClassToUML, templete,classs);
        templete = getMethodsUml(printClassToUML, templete,classs);
        templete = getClassRelationsUml(printClassToUML, templete,classs);
        return templete;
    }

    private String getAttributesUml( PrintClassToUML printClassToUML, String templete, Clase classs){

        String atributos = printClassToUML.getAttributes(classs);

        if( atributos != null && !atributos.equals("")){
            String temp = atributos;
            String [] partTemp = temp.split( "\n");
            StringBuffer bufferAttributes = new StringBuffer();

            for(String attribute: partTemp){
                String tempA =  "<div class=\"Element\"> @aTemp@ </div>";
                tempA = tempA.replace("@aTemp@", this.elementSpecialChars(attribute));
                bufferAttributes.append(tempA);
            }
            templete = templete.replace("@Atributos@", bufferAttributes.toString());
        } else {
            templete =  templete.replace("@Atributos@", "");
        }

        return templete;
    }

    private String getMethodsUml( PrintClassToUML printClassToUML, String templete, Clase classs){
        String metodos =  printClassToUML.getMethods(classs);

        if( metodos != null && !metodos.equals("")){
            String temp = metodos;
            String [] partTemp = temp.split( "\n");
            StringBuffer bufferAttributes = new StringBuffer();

            for(String attribute: partTemp){
                String tempA =  "<div class=\"Element\"> @aTemp@ </div>";
                tempA = tempA.replace("@aTemp@", this.elementSpecialChars(attribute));
                bufferAttributes.append(tempA);
            }
            templete = templete.replace("@Metodos@", bufferAttributes.toString());
        } else {
            templete =  templete.replace("@Metodos@", "");
        }

        return templete;
    }

    private String getClassRelationsUml( PrintClassToUML printClassToUML, String templete, Clase classs){

        if (classs.getClassRelations() != null && classs.getClassRelations().check()) {

            String getRelationsImplement = printClassToUML.getRelationsImplement( classs);
            String getRelationsExtends = printClassToUML.getRelationsExtends( classs);
            String getRelationsIoc = printClassToUML.getRelationsIoc( classs);
            String getRelationsStrongAssociation = printClassToUML.getRelationsStrongAssociation( classs);
            String getRelationsAssociationStaticOrPatterBuild = printClassToUML.getRelationsAssociationStaticOrPatterBuild( classs);

            templete = templete.replace("@RelationsTitle@", "Relations:");

            StringBuffer relationBuffer = new StringBuffer();

            if (getRelationsImplement != null && !getRelationsImplement.equals("")) {
                String temp = getRelationsImplement;
//                relationBuffer.append(relationHtml(temp));
                templete = relationImport(templete, "Implement", relationHtml(temp));
            }else {
                templete = relationImport(templete,null,null);
            }

            if (getRelationsExtends != null && !getRelationsExtends.equals("")) {
                String temp = getRelationsExtends;
//                relationBuffer.append(relationHtml(temp));
                templete = this.relationExtends(templete, "Extends",relationHtml(temp));

            }else {
                templete = this.relationExtends(templete, null,null);
            }

            if (getRelationsIoc != null && !getRelationsIoc.equals("")) {
                String temp = getRelationsIoc;
//                relationBuffer.append(relationHtml(temp));
                templete = this.relationIoc(templete,"Ioc",relationHtml(temp));
            }else {
                templete = this.relationIoc(templete,null, null);
            }

            if (getRelationsStrongAssociation != null && !getRelationsStrongAssociation.equals("")) {
                String temp = getRelationsStrongAssociation;
//                relationBuffer.append(relationHtml(temp));
                templete = this.relationStrongAssociation(templete, "Strong Association",relationHtml(temp));
            }else {
                templete = this.relationStrongAssociation(templete, null, null);
            }

            if (getRelationsAssociationStaticOrPatterBuild != null && !getRelationsAssociationStaticOrPatterBuild.equals("")) {
                String temp = getRelationsAssociationStaticOrPatterBuild;
//                relationBuffer.append(relationHtml(temp));
                templete = this.relationStaticPatterBuild(templete,"Association static or patter Build", relationHtml(temp));
            }else {
                templete = this.relationStaticPatterBuild(templete,null, null);
            }
        } else {
            templete = this.relationCleanAll(templete);
        }

        return templete;
    }

    private String costructorElement( String templete, Clase classs){
        String line = "<div class=\"uml-attributes\"> + @constructors@ </div>";
        if (classs.getConstructores() != null && !classs.getConstructores().isEmpty()) {
            StringBuffer bufferConstructors = new StringBuffer();
//            bufferConstructors.append("Class Constructors: ").append("\n");
            templete = templete.replace("@ConstructorsTitle@","Class Constructors");
            templete = templete.replace("@ConstructorsTitle1@","Constructors");
            for (Constructor constructor : classs.getConstructores()) {
                bufferConstructors.append("\t").append(constructor.getCostructorSignature()).append("\n");
            }
            line = line.replace("@constructors@", elementSpecialChars(bufferConstructors.toString()) );
            templete = templete.replace("@listConstructors@",line);

        }else {
            templete = templete.replace("@ConstructorsTitle@","");
            templete = templete.replace("@ConstructorsTitle1@","");
            templete = templete.replace("@listConstructors@","");
        }
        return templete;
    }

    private String getImports( String templete, Clase classs){

        if (classs.getImports() != null && !classs.getImports().getProjectImports().isEmpty()) {
            StringBuffer importsTemplate = new StringBuffer();
            if (!classs.getImports().getProjectImports().isEmpty()) {
                StringBuffer bufferProjectImportsTemp = new StringBuffer();
//                bufferProjectImportsTemp.append("\t").append("Project Imports: ").append("\n");

                for ( String imports : classs.getImports().getProjectImports()){
                    String externalImport =  "<div class=\"uml-attributes\"> @import@ </div>";

                    externalImport = externalImport.replace("@import@",elementSpecialChars(imports));
                    bufferProjectImportsTemp.append(externalImport).append("\n");
                }
                importsTemplate.append(bufferProjectImportsTemp);
            }

            if (!classs.getImports().getExternalImports().isEmpty()) {
                StringBuffer bufferExternalImportsTemp = new StringBuffer();
                bufferExternalImportsTemp.append("\t").append("External Imports: ").append("\n");

                for ( String imports : classs.getImports().getExternalImports()){
                    String externalImport =  "<div class=\"uml-attributes\"> @import@ </div>";
                    externalImport = externalImport.replace("@import@",elementSpecialChars(imports));
                    bufferExternalImportsTemp.append(externalImport).append("\n");
                }
                importsTemplate.append(bufferExternalImportsTemp);
            }

            templete = templete.replace("@ClassImportsTitle@","Project Imports");
            templete = templete.replace("@ClassImports@",importsTemplate);
        }else{
            templete = templete.replace("@ClassImports@","");
            templete = templete.replace("@ClassImportsTitle@","");
        }
        return templete;
    }

    private String getStructureInterface( String templete, Clase classs){
        if(classs.getStructureInterface() != null && !classs.getStructureInterface().equals("")){
            StringBuffer bufferInterface = new StringBuffer();
//            bufferInterface.append("Interface Structure: ").append("\n");
            bufferInterface.append("\t").append(classs.getStructureInterface()).append("\n");
            templete = templete.replace("@Structure-ImplementsTitle@","Interfaces Implements: ");
            templete = templete.replace("@Structure-Implements@",bufferInterface.toString());
        }else{
            templete = templete.replace("@Structure-Import@","");
            templete = templete.replace("@Structure-ExtendsTitle@","");
        }
        return templete;
    }

    private String getStructureExtends( String templete, Clase classs){
        if(classs.getStructureExtends() != null && !classs.getStructureExtends().equals("")) {
            StringBuffer bufferInterfaceExtends = new StringBuffer();
            templete = templete.replace("@Structure-ExtendsTitle@","Interface Extends");
            bufferInterfaceExtends.append("\t").append(classs.getStructureExtends()).append("\n");
            templete = templete.replace("@Structure-Extends@",bufferInterfaceExtends.toString());
        }else {
            templete = templete.replace("@Structure-Extends@","");
        }
        return templete;
    }

    private String getClassAnotations( String templete, Clase classs){
        if(classs.getClassAnotations() != null && !classs.getClassAnotations().equals("")){
            StringBuffer bufferAnotations = new StringBuffer();
//            bufferAnotations.append("Class Anotations: ").append("\n");
            bufferAnotations.append("\t").append(classs.getClassAnotations()).append("\n");
            templete = templete.replace("@anotationsTitle@","Class Anotations");
            templete = templete.replace("@anotations@",bufferAnotations.toString());
        }else {
            templete = templete.replace("@anotations@","");
            templete = templete.replace("@anotationsTitle@","");
        }
        return templete;
    }

}
