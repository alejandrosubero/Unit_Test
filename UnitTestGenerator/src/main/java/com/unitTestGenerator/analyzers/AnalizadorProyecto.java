package com.unitTestGenerator.analyzers;

import java.io.File;
import java.util.*;


import com.unitTestGenerator.analyzers.services.ImportAnalizeService;
import com.unitTestGenerator.ioc.anotations.Componente;
import com.unitTestGenerator.ioc.anotations.Inyect;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.printers.DirectoryTreeBuilder;
import com.unitTestGenerator.analyzers.services.AnalyzeClassServiceService;
import com.unitTestGenerator.analyzers.services.ITodoDetectorService;
import com.unitTestGenerator.pojos.*;
import com.unitTestGenerator.printers.IPrintProjectStructure;

@Componente
@Singleton
public class AnalizadorProyecto implements ITodoDetectorService, IPrintProjectStructure {

    private  final String[] IGNORAR = {"target", "node_modules", ".git"};

    @Inyect
    private DirectoryTreeBuilder treeBuilder;

    @Inyect
    private AnalyzeClassServiceService analyzeClassServiceService;

    @Inyect
    private ImportAnalizeService importAnalizeService;

    public AnalizadorProyecto() {
    }

    public List<Clase> analizarProyecto(String rutaProyecto, Project project) {

        treeBuilder.setProjetName(rutaProyecto);
        List<Clase> clases = new ArrayList<>();
        Map<String, Clase> mapClass = new HashMap<>();
        File carpetaProyecto = new File(rutaProyecto);
        this.analizarProyectoRecursivo(carpetaProyecto, clases, mapClass, project);
        project.setMapClass(mapClass);
        String  classDirectoryTree = treeBuilder.getTreeString();
        project.setProjectClassTree(classDirectoryTree.replace(".java", " "));
        String projectDirectoryTree = getStructure(project.getPathProject());
        project.setProjectDirectoryTree(projectDirectoryTree);
        return clases;
    }



      private void analizarProyectoRecursivo(File dir, List<Clase> classList, Map<String, Clase> mapClass, Project project) {

        List<String> IGNORED_FOLDERS = Arrays.asList("target", ".idea", ".git");
          StringBuilder tree = new StringBuilder();
          File[] files = dir.listFiles();
          if (files != null) {
              for (File file : files) {
                  if (IGNORED_FOLDERS.contains(file.getName())) {
                      continue;
                  }

                  if (file.isDirectory()) {
                      this.analizarProyectoRecursivo(file, classList, mapClass, project);
                  }else {
                      if(file.getName().trim().contains(".java")) {
                          Clase clase = this.analyzeClassServiceService.analyzeClase(file);
                          if (clase != null) {
                              clase.setTodoNoteInClass(this.getTodo(clase.getRawClass()));
                              this.treeBuilder.addPath(clase.getClassPath());
                              this.importAnalizeService.importAnalize(clase);
                          }
                          this.setContainers(clase, classList, mapClass);
                      }
                  }
              }
          }
    }


    private  void analizarProyectoRecursivo2(File carpeta, List<Clase> classList, Map<String, Clase> mapClass, Project project) {

        if (carpeta.isDirectory()) {
            String nombreCarpeta = carpeta.getName();

            if (!Arrays.asList(IGNORAR).contains(nombreCarpeta)) {
                for (File archivo : carpeta.listFiles()) {
                    if (archivo.isDirectory()) {
                        this.analizarProyectoRecursivo(archivo, classList, mapClass, project);
                    } else {
                        if(archivo.getName().trim().endsWith(".java")) {
                            Clase clase = analyzeClassServiceService.analyzeClase(archivo);
                            if (clase != null) {
                                clase.setTodoNoteInClass(this.getTodo(clase.getRawClass()));
                                this.treeBuilder.addPath(clase.getClassPath());
                                this.importAnalizeService.importAnalize(clase);
                            }
                            this.setContainers(clase, classList, mapClass);
                        }
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
