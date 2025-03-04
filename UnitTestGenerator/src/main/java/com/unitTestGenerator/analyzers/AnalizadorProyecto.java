package com.unitTestGenerator.analyzers;

import java.io.File;
import java.util.*;


import com.unitTestGenerator.analyzers.services.ImportAnalize;
import com.unitTestGenerator.printers.DirectoryTreeBuilder;
import com.unitTestGenerator.analyzers.services.AnalyzeClassService;
import com.unitTestGenerator.analyzers.services.ITodoDetector;
import com.unitTestGenerator.pojos.*;
import com.unitTestGenerator.printers.IPrintProjectStructure;


public class AnalizadorProyecto implements ITodoDetector, IPrintProjectStructure {

    private  final String[] IGNORAR = {"target", "node_modules", ".git"};
    private static AnalizadorProyecto instance;
    private DirectoryTreeBuilder treeBuilder;

    private AnalizadorProyecto() {
    }

    public static AnalizadorProyecto getInstance() {
        if (instance == null) {
            instance = new AnalizadorProyecto();
        }
        return instance;
    }


    public List<Clase> analizarProyecto(String rutaProyecto, Project project) {

        treeBuilder = DirectoryTreeBuilder.getInstance();
        treeBuilder.setProjetName(rutaProyecto);
        List<Clase> clases = new ArrayList<>();
        Map<String, Clase> mapClass = new HashMap<>();
        File carpetaProyecto = new File(rutaProyecto);
        this.analizarProyectoRecursivo(carpetaProyecto, clases, mapClass);
        project.setMapClass(mapClass);
        String  classDirectoryTree = treeBuilder.getTreeString();
        project.setProjectClassTree(classDirectoryTree.replace(".java", " "));
        String projectDirectoryTree = getStructure(project.getPathProject());
        project.setProjectDirectoryTree(projectDirectoryTree);
        return clases;
    }


    private  void analizarProyectoRecursivo(File carpeta, List<Clase> classList, Map<String, Clase> mapClass) {
        if (carpeta.isDirectory()) {
            String nombreCarpeta = carpeta.getName();

            if (!Arrays.asList(IGNORAR).contains(nombreCarpeta)) {
                for (File archivo : carpeta.listFiles()) {
                    if (archivo.isDirectory()) {
                        analizarProyectoRecursivo(archivo, classList, mapClass);
                    } else if (archivo.getName().endsWith(".java")) {
                        Clase clase = AnalyzeClassService.getInstance().analyzeClase(archivo);

                        if(clase !=null){
                            clase.setTodoNoteInClass(this.getTodo(clase.getRawClass()));
                            treeBuilder.addPath(clase.getClassPath());
                            ImportAnalize.importAnalize(clase);
                        }
                        this.setContainers(clase, classList, mapClass);
                    }
                }
            }
        }
    }

    private void setContainers(Clase clase, List<Clase> classList, Map<String, Clase> mapClass){
        if(classList != null && clase !=null){
            classList.add(clase);
        }
        if(mapClass !=null && clase!=null){
            mapClass.put(clase.getNombre(), clase);
        }
    }



}
