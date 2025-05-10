package com.example.grafo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;


public class GrafoInteractivo extends JPanel {
    private final Map<String, Point> posiciones = new HashMap<>();
    private final Map<String, Set<String>> grafo;
    private final int nodoSize = 50;
    private String nodoSeleccionado = null;
    private Point offset = new Point();
    private int nodoIdCounter = 1;

    public GrafoInteractivo(Map<String, Set<String>> grafo) {
        this.grafo = grafo;
        setLayout(null);
        setBackground(Color.BLACK);
        calcularDistribucion();

        // Mouse para mover nodos
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                nodoSeleccionado = obtenerNodoEn(e.getPoint());
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (nodoSeleccionado != null) {
                        Point p = posiciones.get(nodoSeleccionado);
                        offset.setLocation(e.getX() - p.x, e.getY() - p.y);
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                nodoSeleccionado = null;
            }

            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    String nodo = obtenerNodoEn(e.getPoint());
                    if (nodo != null) {
                        mostrarMenuNodo(e.getComponent(), e.getX(), e.getY(), nodo);
                    } else {
                        mostrarMenuFondo(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        // Mouse para arrastrar
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (nodoSeleccionado != null) {
                    posiciones.get(nodoSeleccionado).setLocation(e.getX() - offset.x, e.getY() - offset.y);
                    repaint();
                }
            }
        });

        setPreferredSize(new Dimension(2000, 2000));
    }
    private void calcularDistribucion() {
        ForceDirectedLayout layout = new ForceDirectedLayout(posiciones, grafo, 2000, 2000);
        layout.ejecutar();
    }


    private void calcularDistribucion_old() {
        int centerX = 1000, centerY = 1000;
        int radius = 600;
        int n = grafo.size();
        int i = 0;
        for (String nodo : grafo.keySet()) {
            if (!posiciones.containsKey(nodo)) {
                double angle = 2 * Math.PI * i / n;
                int x = centerX + (int) (radius * Math.cos(angle));
                int y = centerY + (int) (radius * Math.sin(angle));
                posiciones.put(nodo, new Point(x, y));
                i++;
            }
        }
    }

    private String obtenerNodoEn(Point p) {
        for (Map.Entry<String, Point> entry : posiciones.entrySet()) {
            Point centro = entry.getValue();
            Rectangle bounds = new Rectangle(centro.x - nodoSize / 2, centro.y - nodoSize / 2, nodoSize, nodoSize);
            if (bounds.contains(p)) return entry.getKey();
        }
        return null;
    }

    private void mostrarMenuFondo(Component comp, int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem item = new JMenuItem("Agregar nodo");
        item.addActionListener(e -> {
            String nuevoNombre = "N" + nodoIdCounter++;
            grafo.put(nuevoNombre, new HashSet<>());
            posiciones.put(nuevoNombre, new Point(x, y));
            repaint();
        });
        menu.add(item);
        menu.show(comp, x, y);
    }

    private void mostrarMenuNodo(Component comp, int x, int y, String origen) {
        JPopupMenu menu = new JPopupMenu();
        JMenu submenu = new JMenu("Agregar conexión a...");
        for (String destino : grafo.keySet()) {
            if (!destino.equals(origen)) {
                JMenuItem item = new JMenuItem(destino);
                item.addActionListener(e -> {
                    grafo.get(origen).add(destino);
                    repaint();
                });
                submenu.add(item);
            }
        }
        menu.add(submenu);
        menu.show(comp, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));

        // Dibujar líneas
        for (Map.Entry<String, Set<String>> entry : grafo.entrySet()) {
            Point from = posiciones.get(entry.getKey());
            for (String destino : entry.getValue()) {
                Point to = posiciones.get(destino);
//                g2.drawLine(from.x, from.y, to.x, to.y);
                drawArrow(g2, from.x, from.y, to.x, to.y);

            }
        }

        // Dibujar nodos
        for (Map.Entry<String, Point> entry : posiciones.entrySet()) {
            Point p = entry.getValue();
            g2.setColor(Color.WHITE);
            g2.fillOval(p.x - nodoSize / 2, p.y - nodoSize / 2, nodoSize, nodoSize);
            g2.setColor(Color.BLACK);
            g2.drawString(entry.getKey(), p.x - 10, p.y + 5);
        }
    }
    

    // Exportar a archivo .dot
    public void exportarDot(String rutaArchivo) {
        try (PrintWriter writer = new PrintWriter(rutaArchivo)) {
            writer.println("digraph G {");
            for (Map.Entry<String, Set<String>> entry : grafo.entrySet()) {
                String origen = entry.getKey();
                for (String destino : entry.getValue()) {
                    writer.println("  \"" + origen + "\" -> \"" + destino + "\";");
                }
                if (entry.getValue().isEmpty()) {
                    writer.println("  \"" + origen + "\";");
                }
            }
            writer.println("}");
            JOptionPane.showMessageDialog(this, "Grafo exportado a: " + rutaArchivo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error exportando DOT: " + ex.getMessage());
        }
    }

    // Importar desde archivo .dot
    public void importarDot(String rutaArchivo) {
        grafo.clear();
        posiciones.clear();
        try (Scanner scanner = new Scanner(new java.io.File(rutaArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.startsWith("digraph") || linea.equals("{") || linea.equals("}")) continue;
                if (linea.contains("->")) {
                    String[] partes = linea.replace(";", "").split("->");
                    String origen = partes[0].trim().replace("\"", "");
                    String destino = partes[1].trim().replace("\"", "");
                    grafo.putIfAbsent(origen, new HashSet<>());
                    grafo.putIfAbsent(destino, new HashSet<>());
                    grafo.get(origen).add(destino);
                } else if (!linea.isEmpty()) {
                    String nodo = linea.replace(";", "").replace("\"", "").trim();
                    grafo.putIfAbsent(nodo, new HashSet<>());
                }
            }
            calcularDistribucion();
            repaint();
            JOptionPane.showMessageDialog(this, "Grafo importado desde: " + rutaArchivo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error importando DOT: " + ex.getMessage());
        }
    }

    public void recalcularDistribucion() {
        calcularDistribucion();  // usa el nuevo layout force-directed
        repaint();
    }

//    private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
//        g2.drawLine(x1, y1, x2, y2);
//
//        double dx = x2 - x1, dy = y2 - y1;
//        double angle = Math.atan2(dy, dx);
//        int len = 35; // largo de la flecha
//        int width = 15; // ancho entre las "alas"
//
//        int xArrow1 = (int) (x2 - len * Math.cos(angle - Math.PI / 6));
//        int yArrow1 = (int) (y2 - len * Math.sin(angle - Math.PI / 6));
//        int xArrow2 = (int) (x2 - len * Math.cos(angle + Math.PI / 6));
//        int yArrow2 = (int) (y2 - len * Math.sin(angle + Math.PI / 6));
//
//        Polygon arrowHead = new Polygon();
//        arrowHead.addPoint(x2, y2);
//        arrowHead.addPoint(xArrow1, yArrow1);
//        arrowHead.addPoint(xArrow2, yArrow2);
//
//        g2.fill(arrowHead);
//    }


    private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        double distancia = Math.hypot(dx, dy);

        // Restar el radio del nodo al largo para detenerse en el borde del nodo
        int radioNodo = nodoSize / 2;
        double largoVisible = distancia - radioNodo;

        // Punto donde termina la línea (en el borde del nodo destino)
        int xt = x1 + (int) (largoVisible * Math.cos(angle));
        int yt = y1 + (int) (largoVisible * Math.sin(angle));

        // Dibujar línea
        g2.drawLine(x1, y1, xt, yt);

        // Dibujar flecha
        int flechaLargo = 25;
        int flechaAncho = 15;

        int xArrow1 = (int) (xt - flechaLargo * Math.cos(angle - Math.PI / 6));
        int yArrow1 = (int) (yt - flechaLargo * Math.sin(angle - Math.PI / 6));
        int xArrow2 = (int) (xt - flechaLargo * Math.cos(angle + Math.PI / 6));
        int yArrow2 = (int) (yt - flechaLargo * Math.sin(angle + Math.PI / 6));

        Polygon flecha = new Polygon();
        flecha.addPoint(xt, yt);
        flecha.addPoint(xArrow1, yArrow1);
        flecha.addPoint(xArrow2, yArrow2);

        g2.fill(flecha);
    }


    public static void start(Map<String, Set<String>> grafoDependency, Map<String, Set<String>> grafoUses){

        GrafoInteractivo panel = new GrafoInteractivo(grafoDependency);
        JScrollPane scrollPane = new JScrollPane(panel);

        JButton btnRedistribuir = new JButton("Redistribuir");
        btnRedistribuir.addActionListener(e -> {
            panel.recalcularDistribucion();
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnRedistribuir);

        JFrame frame = new JFrame("Grafo Interactivo - Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        Map<String, Set<String>> grafoUses = new HashMap<>();
        Map<String, Set<String>> grafoDependency = new HashMap<>();
        grafoDependency.put("A", new HashSet<>(Arrays.asList("B", "C", "C")));
        grafoDependency.put("B", new HashSet<>());
        grafoDependency.put("C", new HashSet<>());

        grafoDependency.put("h", new HashSet<>(Arrays.asList("B", "C")));
        grafoDependency.put("w", new HashSet<>(Arrays.asList("q")));
        grafoDependency.put("q", new HashSet<>(Arrays.asList("w")));

        GrafoInteractivo.start(grafoDependency,  grafoUses);

    }


}

