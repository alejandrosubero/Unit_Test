package com.unitTestGenerator.printers;

import com.unitTestGenerator.analyzers.services.ImportAnalize;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrintAnalizeImports  {


   public String getClassOfImport(String importText){
        //import com.bpm.employee.pojo.EmpleadoPojo
        int lastDotIndex = importText.lastIndexOf('.');
        // Obtener el valor después del último punto
        String valueAfterLastDot = importText.substring(lastDotIndex + 1);
        return valueAfterLastDot;
    }


    // a trabajar metodos modificarlos para usarlos:

    public Project analizeExtens(Project project){
        if(project != null && project.getClaseList() != null && !project.getClaseList().isEmpty()){
            project.getClaseList().forEach(clase -> {
                if(clase.getClassRelations() != null && isExtents(clase)){
                    StringBuffer buffer = new StringBuffer();
                    String nameClass = clase.getClassRelations().getClassExtends().trim();
                    buffer.append("- ").append(nameClass).append("\n");
                    Clase implementClass = project.getClass(nameClass);
                    String treeString = this.extendsLoop(project, implementClass);
                    if (!treeString.isEmpty()) {
                        String indentedTree = treeString.replaceAll("(?m)^", "   ");
                        buffer.append(indentedTree);
                    }
                    clase.setStructureExtends(buffer.toString());
                }
            });
        }
        return project;
    }


    private Boolean isExtents(Clase loop){
        return loop != null && loop.getClassRelations() != null && loop.getClassRelations().getClassExtends() != null && !loop.getClassRelations().getClassExtends().equals("");
    }

    private String extendsLoop(Project project, Clase classRelation) {
        List<String> tree = new ArrayList<>();

        if(isExtents(classRelation)){
            Clase loop = classRelation;

            while (isExtents(loop)) {
                String extTemp = loop.getClassRelations().getClassExtends().trim();
                tree.add(extTemp);
                Optional<Clase> c = project.getClaseList().stream().filter(clase -> clase.getNombre().equals(extTemp)).findFirst();

                loop = project.getClass(extTemp);
            }
        }
        String structure = this.getTreeString(tree);
        return structure;
    }


    private String getTreeString(List<String> tree) {
        StringBuilder sb = new StringBuilder();
        if(!tree.isEmpty()){
            for(String leaf : tree){
                sb.append("|__ ").append(leaf).append("\n");
            }
            return sb.toString();
        }
        return "";
    }


}
