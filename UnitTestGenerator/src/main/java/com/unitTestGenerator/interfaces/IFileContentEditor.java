package com.unitTestGenerator.interfaces;

import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.TestFileContent;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.services.AnalyzeClassService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IFileContentEditor {

    default String mixFileContentOrAddContent( String oldContentValue, TestFileContent newfileContent) {
        try {

            Integer lastBraceIndex = oldContentValue.lastIndexOf("}") -1;
            Integer firstBraceIndex = oldContentValue.indexOf("{")+1;

            if (lastBraceIndex == -1) {
                return "The file Don't have \" } \" ";
            }
            String contentWithVariables = "";
            String updatedContent = "";
            Clase newClassContent = AnalyzeClassService.getInstance().getAnalisisOfVariables(newfileContent.toString());
            Clase oldClassContent = AnalyzeClassService.getInstance().getAnalisisOfVariables(oldContentValue);

            if (newfileContent != null && newfileContent.getTestsClassVariables() != null) {

                for (Variable variable : newClassContent.getVariables()) {

                    Boolean isExistvariable = oldClassContent.getVariables().stream().anyMatch(val -> val.getNombre().toLowerCase().equals(variable.getNombre().toLowerCase()) && val.getTipo().toLowerCase().equals(variable.getTipo().toLowerCase()));
                    if (!isExistvariable) {
                        contentWithVariables = this.formatUpdatedContent(oldContentValue, firstBraceIndex, newfileContent.getTestsClassVariables());
                    }

                }
            }

            if (newfileContent != null && newfileContent.getTestsClassMethods() != null ) {
                for(Metodo method : newClassContent.getMetodos()){
                    Boolean isExistMethod = oldClassContent.getMetodos().stream().anyMatch(
                            met -> met.getNombre().toLowerCase().equals(method.getNombre().toLowerCase())
                                    && met.getTipoRetorno().toLowerCase().equals(method.getTipoRetorno().toLowerCase()));

                    if(!isExistMethod){
                        updatedContent = this.formatUpdatedContent(contentWithVariables, lastBraceIndex, newfileContent.getTestsClassMethods());
                    }
                }
            }
            return updatedContent;

        } catch (Exception e) {
            System.err.println("Error in processing File: " + e.getMessage());
            return "";
        }
    }



    default  String formatUpdatedContent(String content, Integer braceIndex, String newContent) {
        if( content != null &&  braceIndex != null && newContent != null) {
            return String.format("%s\n%s\n%s", content.substring(0, braceIndex), newContent, content.substring(braceIndex));
        }
        return "Null content...";
    }




}
