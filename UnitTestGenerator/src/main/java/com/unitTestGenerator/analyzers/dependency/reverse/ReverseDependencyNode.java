package com.unitTestGenerator.analyzers.dependency.reverse;

import com.unitTestGenerator.ioc.anotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReverseDependencyNode {

    private String className;
    private String packague;
    private String title;
    private List<ReverseDependencyNode> listUsedBy = new ArrayList<>();

    public ReverseDependencyNode() {
    }

    public ReverseDependencyNode(String className, String packague) {
        this.className = className;
        this.packague = packague;
        this.title = packague.isEmpty() ? className : packague + "." + className;
    }

    public ReverseDependencyNode setReverseDependencyNode(String className, String packague) {
        this.className = className;
        this.packague = packague;
        this.title = packague.isEmpty() ? className : packague + "." + className;
        return this;
    }


    public Integer usedBy(){
        return this.listUsedBy.size();
    }

    public String getClassName() {
        return className;
    }

    public String getPackague() {
        return packague;
    }

    public String getTitle() {
        return title;
    }

    public List<ReverseDependencyNode> getListUsedBy() {
        return listUsedBy;
    }

    public void addUsedBy(ReverseDependencyNode user) {
        listUsedBy.add(user);
    }


    @Override
    public String toString() {
        return toFormattedString();
    }

    public String toFormattedString() {
        StringBuilder sb = new StringBuilder(256); // Capacidad inicial para evitar realocaciones
        sb.append("Class: ").append(className).append('\n');
        sb.append("Package: ").append(packague.isEmpty() ? "(default)" : packague).append('\n');
        sb.append("Full Name: ").append(title).append('\n');

        int usageCount = listUsedBy.size();
        sb.append("Used by ").append(usageCount).append(" class").append(usageCount == 1 ? "" : "es").append(":\n");

        for (int i = 0, len = listUsedBy.size(); i < len; i++) {
            sb.append("  - ").append(listUsedBy.get(i).getTitle()).append('\n');
        }

        return sb.toString();
    }



    public String toFlatString() {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(";");
        sb.append(packague).append(";");
        sb.append(title).append(";");
        sb.append(listUsedBy.size()).append(";");

        for (int i = 0; i < listUsedBy.size(); i++) {
            sb.append(listUsedBy.get(i).getTitle());
            if (i < listUsedBy.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }




}

