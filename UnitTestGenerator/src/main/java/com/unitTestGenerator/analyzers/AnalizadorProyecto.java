package com.unitTestGenerator.analyzers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.unitTestGenerator.analyzers.services.AnalyzeClassService;
import com.unitTestGenerator.pojos.*;


public class AnalizadorProyecto {

    private  final String[] IGNORAR = {"target", "node_modules", ".git"};
    private static AnalizadorProyecto instance;

    private AnalizadorProyecto() {
    }

    public static AnalizadorProyecto getInstance() {
        if (instance == null) {
            instance = new AnalizadorProyecto();
        }
        return instance;
    }


    public  List<Clase> analizarProyecto(String rutaProyecto) {
        List<Clase> clases = new ArrayList<>();
        File carpetaProyecto = new File(rutaProyecto);
        analizarProyectoRecursivo(carpetaProyecto, clases);
        return clases;
    }


    private  void analizarProyectoRecursivo(File carpeta, List<Clase> clases) {
        if (carpeta.isDirectory()) {
            String nombreCarpeta = carpeta.getName();

            if (!Arrays.asList(IGNORAR).contains(nombreCarpeta)) {
                for (File archivo : carpeta.listFiles()) {
                    if (archivo.isDirectory()) {
                        analizarProyectoRecursivo(archivo, clases);
                    } else if (archivo.getName().endsWith(".java")) {
                        Clase clase = AnalyzeClassService.getInstance().analyzeClase(archivo);
                        if(clase !=null) {
                            clases.add(clase);
                        }
                    }
                }
            }
        }
    }




}
