package com.example.grafo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class MainGrafo {

    public static void main(String[] args) {
        Map<String, Set<String>> grafo = new HashMap<>();

//        grafo.put("Car", new HashSet<>(Arrays.asList("Engine", "Cost")));
//        grafo.put("Engine", new HashSet<>());
//        grafo.put("Cost", new HashSet<>());
//        grafo.put("Deller", new HashSet<>(Arrays.asList("Car")));

        grafo.put("A", new HashSet<>(Arrays.asList("B", "C")));
        grafo.put("B", new HashSet<>());
        grafo.put("C", new HashSet<>());

        grafo.put("h", new HashSet<>(Arrays.asList("B", "C")));
        grafo.put("w", new HashSet<>(Arrays.asList("q")));
        grafo.put("q", new HashSet<>(Arrays.asList("w")));

        GrafoInteractivo panel = new GrafoInteractivo(grafo);
        JScrollPane scrollPane = new JScrollPane(panel);

        JFrame frame = new JFrame("Grafo Interactivo - Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}












