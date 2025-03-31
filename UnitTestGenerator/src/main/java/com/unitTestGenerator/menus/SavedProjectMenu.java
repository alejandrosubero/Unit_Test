package com.unitTestGenerator.menus;

import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.ioc.anotations.Component;
import com.unitTestGenerator.persistence.model.Data;
import com.unitTestGenerator.persistence.model.DataPojo;
import com.unitTestGenerator.persistence.repositories.DataDAOServicesImplement;
import com.unitTestGenerator.persistence.repositories.IDaoService;
import com.unitTestGenerator.pojos.Project;
import com.unitTestGenerator.printers.IPrintService;
import com.unitTestGenerator.printers.PrintProjectAnalyzers;
import com.unitTestGenerator.test.AppTestDatabase;
import com.unitTestGenerator.util.ObjectTransformer;

import java.util.ArrayList;
import java.util.List;

@Component
public class SavedProjectMenu implements IPrintService {

//    V1.1.0.0  In progress....

    private ObjectTransformer objectTransformer;
    public static IDaoService dao;

    public SavedProjectMenu() {
        this.dao =  ContextIOC.getInstance(AppTestDatabase.class).getClassInstance(DataDAOServicesImplement.class);
    }

    public Boolean saveData(Project project){
        try {
            this.dao.save(projectToDataPojo(project));
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public DataPojo projectToDataPojo(Project project){
        return new DataPojo(project.getName(), project.getDescription(), objectTransformer.objectToString(project));
    }


    public List<String> listProject(){
        List<String> listProject = new ArrayList<>();
        try {
            List<DataPojo> dataList = this.dao.findAll();
            dataList.forEach(dataPojo -> listProject.add(dataPojo.toString()));
            return listProject;
        }catch (Exception e){
            e.printStackTrace();
            return listProject;
        }
    }

    public void printList(){
        List<String> list = this.listProject();
        if(list != null && !list.isEmpty()){
            int i = 1;
            for (String element: list){
                this.service().print_DARKGREEN(i+") "+element);
                i++;
            }
        }else {
            this.service().print_RED("");
        }
    }

    public Project getProjectById(Long id){
        Project project = null;
        try {
            DataPojo data = this.dao.findById(id);
            if(data != null && data.getDatos() != null && !data.getDatos().equals("")){
                project =  this.objectTransformer.stringToObject(data.getDatos());
            }
            return project;
        }catch (Exception e){
            e.printStackTrace();
            return project;
        }
    }


}
