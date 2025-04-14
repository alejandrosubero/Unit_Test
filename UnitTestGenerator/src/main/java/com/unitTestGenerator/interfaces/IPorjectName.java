package com.unitTestGenerator.interfaces;

import com.unitTestGenerator.core.ProjectHolder;
import com.unitTestGenerator.ioc.ContextIOC;
import com.unitTestGenerator.test.ioc.IocConected;
import com.unitTestGenerator.util.IConstantModel;

import java.util.ArrayList;
import java.util.List;

public interface IPorjectName {

    default String getArtifatOrFileName(){
        ProjectHolder projectHolder = ContextIOC.getInstance().getClassInstance(ProjectHolder.class);
            String[] projectName = projectHolder.getProject().getPathProject().split(IConstantModel.Separator);
            return projectName[projectName.length - 1];
    }


    default String getArtifatOrFileName(String pathProject){
        String[] projectName = pathProject.split(IConstantModel.Separator);
        return projectName[projectName.length - 1];
    }

}
