package com.example.grafo;

import com.example.grafo.pojos.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.util.List;

public class MainGrafo {

    public static void main(String[] args) {

        Map<String, Set<String>> grafoUses = new HashMap<>();
        Map<String, Set<String>> grafoDependency = new HashMap<>();

        grafoDependency.put("A", new HashSet<>(Arrays.asList("B", "C", "C")));
        grafoDependency.put("B", new HashSet<>());
        grafoDependency.put("C", new HashSet<>());

        grafoDependency.put("h", new HashSet<>(Arrays.asList("B", "C")));
        grafoDependency.put("w", new HashSet<>(Arrays.asList("q")));
        grafoDependency.put("q", new HashSet<>(Arrays.asList("w")));

        grafoDependency.put("Car", new HashSet<>(Arrays.asList("Engine", "Cost")));
        grafoDependency.put("Engine", new HashSet<>());
        grafoDependency.put("Cost", new HashSet<>());
        grafoDependency.put("Deller", new HashSet<>(Arrays.asList("Car")));


        GrafoInteractivo.start(grafoDependency,  grafoUses);

    }







    public static void pet() {
        Map<String, Node> nodeSources = new HashMap<>();

        Node node1 = new Node("1", "Car", Arrays.asList("Engine", "Cost"), Arrays.asList("Deller"));
        Node node2 = new Node("2", "Engine", Arrays.asList("Cost"), Arrays.asList("Car"));
        Node node3 = new Node("3", "Cost", null , Arrays.asList("Engine", "Car"));
        Node node4 = new Node("4", "Deller", Arrays.asList("Car"), Arrays.asList("Cient"));
        Node node5 = new Node("5", "Cient", Arrays.asList("Car","Money"), Arrays.asList("Deller"));
        Node node6 = new Node("6", "Money", null, Arrays.asList("Cient"));

        nodeSources.put(node1.getName(), node1);
        nodeSources.put(node2.getName(), node2);
        nodeSources.put(node3.getName(), node3);
        nodeSources.put(node4.getName(), node4);
        nodeSources.put(node5.getName(), node5);
        nodeSources.put(node6.getName(), node6);


}

}












