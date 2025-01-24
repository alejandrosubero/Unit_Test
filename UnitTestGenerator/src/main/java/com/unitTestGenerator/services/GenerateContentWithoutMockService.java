package com.unitTestGenerator.services;

import com.unitTestGenerator.builders.AddPatterBuilder;
import com.unitTestGenerator.interfaces.IManageMavenGadleAppProperties;
import com.unitTestGenerator.util.IBaseModel;
import com.unitTestGenerator.interfaces.IMethodServiceTools;
import com.unitTestGenerator.pojos.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateContentWithoutMockService implements IMethodServiceTools, IBaseModel, IManageMavenGadleAppProperties {

    public static GenerateContentWithoutMockService instance;
    private Project project;

    private GenerateContentWithoutMockService() {
    }

    public static GenerateContentWithoutMockService getInstance(){
        if(instance == null){
            instance = new GenerateContentWithoutMockService();
        }
        return instance;
    }

    public void setProject(Project project){
        this.project = project;
    }


    public String generarContenidoSinMock(Metodo metodo, Clase clase, TestFileContent fileContent) {

        StringBuilder contenido = new StringBuilder();
        if(metodo != null  && metodo.getNombre() != null){
            String methodName = metodo.getNombre();

            List<Clase> parametersClassList = new ArrayList<>();
            if( metodo.getParametros() !=null && !metodo.getParametros().isEmpty()){
                metodo.getParametros().forEach(parametroMetodo -> parametersClassList.add(this.project.getClass(parametroMetodo.getTipo())));
            }

            // ver si la clases de la lista de los parametros objetos tiene el patron build **** esto se hace en el analisis de las clases.
            parametersClassList.forEach(clase1 -> {

                if(!clase1.getUseLomboxBuild() && !clase1.getApplyBuildMethod()){
                    askForAddBuildPatterInClass(clase1);
                }

                if(clase1.getUseLomboxBuild() || clase1.getApplyBuildMethod()){
                    // ahora creamos el objeto con el patron
                }


            });



            //si lo tiene usarlo; si no lo tiene preguntar si quiere agregarlo:

//            si es si ; preguntar si desea agregar a la clase el patter build o usar lombox (en este punto una previa evaluacion si se cuenta con la dependencia).
            // si tiene la dependencia seria para indicar puesto que ya tiene la dependency en el proyecto.

                this.addLombokDependency(project);

            contenido.append(generateCallMethod(methodName, metodo.getParametros()));
        }

        return contenido.toString();
    }

    public String generateCallMethod(String methodName, List<ParametroMetodo> parametros) {
        StringBuilder contenido = new StringBuilder();
        contenido.append(methodName).append("(");

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

    private String buildObject(Clase clase1){
        // genera objetos atravez de new
        return "";
    }


    private void askForAddBuildPatterInClass(Clase clase1){
        Scanner scanner = new Scanner(System.in);
            System.out.println("You want to implement the build pattern in class "+clase1.getNombre()+" for object creation?");
            System.out.println("Choose an option:");
            System.out.println("1. yes");
            System.out.println("2. no");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                     this.buildObjectTypeBuild(clase1);
                default:
                    System.out.println("ok");
            }
    }


    private void buildObjectTypeBuild(Clase clase1){
        Scanner scanner = new Scanner(System.in);
            System.out.println("You want to implement lombok o patter build in class "+clase1.getNombre()+"?");
            System.out.println("Choose an option:");
            System.out.println("1.Lombok");
            System.out.println("2.Patter build in class");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    this.addLombokDependency(project);
                    break;
                case 2:
                    String filePath =  stringPaths(false, false,
                            this.project.getPathProject(),
                            packageToPaths(clase1.getPaquete()),
                            stringEnsamble( clase1.getNombre(),".java")
                    );
                    try {
                        AddPatterBuilder.getInstance().generateBuilderPatterFromClassFile(filePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println("No changes will be made to the class");
            }
    }



}
