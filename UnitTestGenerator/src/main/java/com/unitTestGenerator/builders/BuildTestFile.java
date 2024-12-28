package com.unitTestGenerator.builders;

import com.unitTestGenerator.interfaces.IFileContentEditor;
import com.unitTestGenerator.interfaces.IFileManager;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Metodo;
import com.unitTestGenerator.pojos.TestFileContent;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.services.AnalyzeClassService;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.stream.*;
import java.io.FileWriter;
import java.io.IOException;


import org.apache.commons.io.FileUtils;

public class BuildTestFile implements IFileContentEditor, IFileManager {

    public BuildTestFile() {
    }

    public static BuildTestFile getInstance() {
        return new BuildTestFile();
    }


    public void createTestFile(String ruta, TestFileContent content) {
        File file = new File(ruta);
        String oldVlue = this.fileExist(file);

        if (oldVlue != null && !oldVlue.equals("")) {
            this.startWritefiles(oldVlue, content, file);
        } else {
            this.writefilesI(file, content.toString());
        }
    }


    private void startWritefiles(String oldVlue, TestFileContent content, File archivo) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {

            System.out.println("The file exists.");
            System.out.println("Do you want me to analyze it and add only the methods and variables that do not exist,");
            System.out.println(" or do you want me to overwrite the entire file?");
            System.out.println("__________________________________________________________________________________________");
            System.out.println("Choose an option:");
            System.out.println("1. Analyze and add");
            System.out.println("2. Overwrite everything");
            System.out.println("__________________________________________________________________________________________");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    String newContentFile = this.mixFileContentOrAddContent(oldVlue, content);
                    if (newContentFile != null && !newContentFile.equals("")) {
                        this.writefilesI(archivo, newContentFile);
                    } else {
                        System.out.println("Error: Something happened in file Analyze, the file will be Overwrite everything...");
                        this.writefilesI(archivo, content.toString());
                    }
                    continuar = false;
                    break;
                case 2:
                    this.writefilesI(archivo, content.toString());
                    continuar = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println("The file will be Overwrite everything...");
                    this.writefilesI(archivo, content.toString());
                    continuar = false;
                    break;
            }
        }
    }


}
