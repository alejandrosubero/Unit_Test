package com.example.grafo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class ForceDirectedLayout {
    private final Map<String, Point> posiciones;
    private final Map<String, Set<String>> grafo;
    private final int width, height;
    private final int iterations = 500;
    private final double repulsion = 5000;
    private final double springLength = 150;
    private final double springStrength = 0.1;
    private final double damping = 0.85;

    private final Map<String, double[]> velocidades = new HashMap<>();

    public ForceDirectedLayout(Map<String, Point> posiciones, Map<String, Set<String>> grafo, int width, int height) {
        this.posiciones = posiciones;
        this.grafo = grafo;
        this.width = width;
        this.height = height;
    }

    public void ejecutar() {
        Random rand = new Random();

        // Inicializar posiciones si están vacías
        for (String nodo : grafo.keySet()) {
            posiciones.putIfAbsent(nodo, new Point(rand.nextInt(width), rand.nextInt(height)));
            velocidades.put(nodo, new double[]{0, 0});
        }

        for (int i = 0; i < iterations; i++) {
            Map<String, double[]> fuerzas = new HashMap<>();
            for (String a : grafo.keySet()) {
                double fx = 0, fy = 0;
                Point pa = posiciones.get(a);

                for (String b : grafo.keySet()) {
                    if (a.equals(b)) continue;
                    Point pb = posiciones.get(b);

                    double dx = pa.x - pb.x;
                    double dy = pa.y - pb.y;
                    double dist = Math.max(1, Math.sqrt(dx * dx + dy * dy));
                    double fuerzaRepulsion = repulsion / (dist * dist);

                    fx += (dx / dist) * fuerzaRepulsion;
                    fy += (dy / dist) * fuerzaRepulsion;
                }

                for (String b : grafo.get(a)) {
                    Point pb = posiciones.get(b);
                    double dx = pb.x - pa.x;
                    double dy = pb.y - pa.y;
                    double dist = Math.max(1, Math.sqrt(dx * dx + dy * dy));
                    double fuerzaAtraccion = springStrength * (dist - springLength);

                    fx += (dx / dist) * fuerzaAtraccion;
                    fy += (dy / dist) * fuerzaAtraccion;
                }

                fuerzas.put(a, new double[]{fx, fy});
            }

            for (String nodo : grafo.keySet()) {
                double[] v = velocidades.get(nodo);
                double[] f = fuerzas.get(nodo);
                v[0] = (v[0] + f[0]) * damping;
                v[1] = (v[1] + f[1]) * damping;

                Point p = posiciones.get(nodo);
                int x = Math.min(width - 50, Math.max(50, (int) (p.x + v[0])));
                int y = Math.min(height - 50, Math.max(50, (int) (p.y + v[1])));
                p.setLocation(x, y);
            }
        }
    }
}

