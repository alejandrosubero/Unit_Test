package com.unitTestGenerator.printers;

import com.unitTestGenerator.util.DetectSO;
import com.unitTestGenerator.util.IBaseModel;

import java.util.*;

public class DirectoryTreeBuilder implements IBaseModel {

    public static DirectoryTreeBuilder instance;
    private final TreeNode root = new TreeNode("");
    private String projetName;

    public static DirectoryTreeBuilder getInstance() {
        if (instance == null){
            instance = new DirectoryTreeBuilder();
        }
        return instance;
    }

    private DirectoryTreeBuilder() {
    }

    public void setProjetName(String projetPath){
        if(projetPath != null && !projetPath.equals("")){
//            String separador = DetectSO.os().equals("win")? "\\\\":this.Separator;
            String[] parts = projetPath.split(this.Separator);
            String lastElement = parts[parts.length - 1];
            if(lastElement != null && !lastElement.isEmpty()){
                this.projetName = lastElement;
            }
        }
    }

    public String getProjetName(){
        if(this.projetName  != null && !this.projetName.equals("")){
            return this.projetName;
        }else {
            return "";
        }

    }

    private String pathImproved(String path){
        String[] parts = path.split(this.projetName);
        return parts[parts.length-1];
    }

    public void addPath(String path) {
        String pathImproved = stringPaths(false, false,this.projetName , pathImproved(path));
        String[] parts = pathImproved.split("/");
        TreeNode current = root;
        for (String part : parts) {
            current = current.children.computeIfAbsent(part, TreeNode::new);
        }
    }

    public String getTreeString() {
        return getTreeString(root, 0);
    }

    private String getTreeString(TreeNode node, int level) {
        StringBuilder sb = new StringBuilder();
        if (!node.name.isEmpty()) {
            for (int i = 0; i < level; i++) {
                sb.append("  ");
            }
            sb.append("|__ ").append(node.name).append("\n");
        }
        List<TreeNode> sortedChildren = new ArrayList<>(node.children.values());
        sortedChildren.sort(Comparator.comparing(n -> n.name));
        for (TreeNode child : sortedChildren) {
            sb.append(getTreeString(child, level + 1));
        }
        return sb.toString();
    }

    private static class TreeNode {
        String name;
        Map<String, TreeNode> children = new HashMap<>();
        TreeNode(String name) {
            this.name = name;
        }
    }
}
