package com.unitTestGenerator.analyzers;

import java.io.File;
import java.util.*;


import com.unitTestGenerator.analyzers.print.DirectoryTreeBuilder;
import com.unitTestGenerator.analyzers.services.AnalyzeClassService;
import com.unitTestGenerator.analyzers.services.ITodoDetector;
import com.unitTestGenerator.pojos.*;


public class AnalizadorProyecto implements ITodoDetector {

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
        project.setProjectDirectoryTree(treeBuilder.getTreeString());
        project.setProjectClassTree(project.getProjectDirectoryTree().replace(".java", " "));
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
