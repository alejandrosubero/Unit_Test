package com.unitTestGenerator.builders;



import com.unitTestGenerator.analyzers.AnalizadorProyecto;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.ioc.anotations.Inyect;
import com.unitTestGenerator.ioc.anotations.Singleton;
import com.unitTestGenerator.pojos.Clase;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.pojos.Variable;
import com.unitTestGenerator.util.interfaces.IBaseModel;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Singleton
public class ManagerBuilderPattern implements IBaseModel{

    private Project project;

    @Inyect
    private AddPatterBuilder builderGenerator;


    public ManagerBuilderPattern() {
    }

    public void setProject(Project project){
        this.project = project;
    }

    public void generateFromPathClass(String classPath){
        String response = "";
        try {
            this.validFile(classPath);
            builderGenerator.generateBuilderPatterFromClassFile(classPath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void generateFromPathProject(String className) {
        Map<String, Clase> projectClassMap = new HashMap<>();
        if (this.project != null) {
            projectClassMap = project.getMapClass();

            if (!projectClassMap.isEmpty()) {
                List<Clase> classListFromtClassName = this.getClassListFromtClassName(projectClassMap, className);
                this.generateListclass(projectClassMap, classListFromtClassName);
            }
        }
    }

    private  void generateListclass(Map<String, Clase> projectClassMap, List<Clase> classListFromtClassName){
        List<Variable> variables = this.getVariablesList(classListFromtClassName);
        List<Clase> cls = this.loopRecoveryClass(variables, projectClassMap);
        cls.addAll(classListFromtClassName);
        this.processClass(cls);
    }


    private List<Clase> loopRecoveryClass(List<Variable> var, Map<String, Clase> projectClassMap) {
        Map<String, Set<Clase>> newClassMap = new HashMap<>();
        List<Variable> variablesActuales = new ArrayList<>(var);

        while (!variablesActuales.isEmpty()) {
            List<Clase> wallet = variableClassList(variablesActuales, projectClassMap);

            if (wallet == null || wallet.isEmpty()) {
                variablesActuales.clear();
            } else {
                updateMap(newClassMap, wallet);
                variablesActuales = getVariablesList(wallet);
                if (variablesActuales == null) variablesActuales = new ArrayList<>();
            }
        }
        return convertMapToList(newClassMap);
    }


    private List<Variable> getVariablesList(List<Clase> variableClassList) {
        List<Variable> variables = new ArrayList<>();
        variableClassList.forEach(clase -> {
            if(clase.getVariables() != null && clase.getVariables().size() > 0){
                variables.addAll(clase.getVariables());
            }
        });
        return variables;
    }

    private List<Clase> variableClassList( List<Variable> variables, Map<String, Clase> projectClassMap){
        List<Clase> variableClassList = new ArrayList<>();
        for (Variable var : variables ){
            if(projectClassMap.containsKey(var.getTipo())){
                variableClassList.add(projectClassMap.get(var.getTipo()));
            }
        }
        return variableClassList;
    }

    private List<Clase> getClassListFromtClassName(Map<String, Clase> projectClassMap, String className){
        List<Clase> classListFromtClassName = new ArrayList<>();
        if(projectClassMap.containsKey(className)){
            classListFromtClassName.add(projectClassMap.get(className));
        }
        return classListFromtClassName;
    }

    private void processClass(List<Clase> classToBuild) {
        List<String> results = new ArrayList<>();
        try {
            for (Clase cls : classToBuild) {

                if(!cls.getApplyBuildMethod()){

                    String filePath =  stringPaths(false, false,
                            this.project.getPathProject(),
                            "src","main","java",
                            packageToPaths(cls.getPaquete()),
                            stringEnsamble( cls.getNombre(),".java")
                    );

                  builderGenerator.generateBuilderPatterFromClassFile(filePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMap(Map<String, Set<Clase>> classMap, List<Clase> clases) {
        // 1. Validaciones de seguridad
        if (clases == null || clases.isEmpty() || classMap == null) return;
        clases.forEach(clase ->
                classMap.computeIfAbsent(clase.getNombre(), k -> new HashSet<>())
                        .add(clase) // El Set.add() ignora el objeto si ya existe
        );
    }

    private List<Clase> convertMapToList(Map<String, Set<Clase>> classMap) {

        if (classMap == null || classMap.isEmpty()) return new ArrayList<>();

        return classMap.values() // 1. Obtenemos Collection<Set<Clase>>
                .stream()        // 2. Abrimos el flujo
                .flatMap(Set::stream) // 3. "Aplanamos" cada Set en elementos sueltos
                .collect(Collectors.toList()); // 4. Agrupamos todo en una nueva lista
    }

    private void validFile(String classFilePath) throws IOException {
        File file = new File(classFilePath);
        if (!file.exists()) {
            throw new IOException("❌ Archivo no existe: " + classFilePath);
        }
    }
}
