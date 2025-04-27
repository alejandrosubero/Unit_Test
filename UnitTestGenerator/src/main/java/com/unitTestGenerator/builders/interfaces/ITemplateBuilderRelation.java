package com.unitTestGenerator.builders.interfaces;

public interface ITemplateBuilderRelation {


    default String relationCleanAll(String templete){
        templete = templete.replace("@ImplementTitle@", "");
        templete = templete.replace("@Implement@", "");
        templete = templete.replace("@ExtendsTitle@", "");
        templete = templete.replace("@Extends@", "");
        templete = templete.replace("@IocTitle@", "");
        templete = templete.replace("@Ioc@", "");
        templete = templete.replace("@StrongAssociationTitle@", "");
        templete = templete.replace("@StrongAssociation@", "");
        templete = templete.replace("@staticPatterBuildTitle@", "");
        templete = templete.replace("@staticPatterBuild@", "");
        return templete;
    }


    default String relationImport(String templete, String title, String content){
        if(title == null){ title = "";}
        if (content == null){ content = "";}
        templete = templete.replace("@ImplementTitle@", title);
        templete = templete.replace("@Implement@", content);
        return templete;

    }

    default String relationExtends(String templete, String title, String content){
        if(title == null){ title = "";}
        if (content == null){ content = "";}
        templete = templete.replace("@ExtendsTitle@", title);
        templete = templete.replace("@Extends@", content);
        return templete;
    }

    default String relationIoc(String templete, String title, String content){
        if(title == null){ title = "";}
        if (content == null){ content = "";}
        templete = templete.replace("@IocTitle@", title);
        templete = templete.replace("@Ioc@", content);
        return templete;
    }


    default String relationStrongAssociation(String templete, String title, String content){
        if(title == null){ title = "";}
        if (content == null){ content = "";}
        templete = templete.replace("@StrongAssociationTitle@", title);
        templete = templete.replace("@StrongAssociation@", content);
        return templete;
    }


    default String relationStaticPatterBuild(String templete, String title, String content){
        if(title == null){ title = "";}
        if (content == null){ content = "";}
        templete = templete.replace("@StaticPatterBuildTitle@", title);
        templete = templete.replace("@StaticPatterBuild@", content);
        return templete;
    }


}
