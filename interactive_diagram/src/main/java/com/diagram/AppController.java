package com.diagram;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.geometry.Bounds;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AppController {

    @FXML
    private Pane container;

    @FXML
    private Canvas linesCanvas;

    @FXML
    private Button addElementBtn;

    @FXML
    private ScrollPane scrollPane;

    private ObservableList<DiagramElement> elements = FXCollections.observableArrayList();
    private DiagramElement selectedElement;
    private double dragOffsetX;
    private double dragOffsetY;

    private final BooleanProperty isDragging = new SimpleBooleanProperty(false);
    private Map<DiagramElement, Pane> elementPaneMap = new HashMap<>();


    @FXML
    public void initialize() {
        // ==============================================
        // 1. Configuración Inicial del Contenedor y Canvas
        // ==============================================
        container.setStyle("-fx-background-color: black;");

        // Listener único para cambios de tamaño del contenedor
        container.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            linesCanvas.setWidth(newVal.getWidth());
            linesCanvas.setHeight(newVal.getHeight());
        });

        // ==============================================
        // 2. Configuración del ScrollPane y Desplazamiento
        // ==============================================
        // Listeners para desplazamiento y viewport (sin redibujado directo)
        scrollPane.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {});
        scrollPane.hvalueProperty().addListener((obs, o, n) -> {});
        scrollPane.vvalueProperty().addListener((obs, o, n) -> {});

        // ==============================================
        // 3. Carga de Elementos y Conexiones
        // ==============================================
        addElement(Element.getDiagramElementList());
        resolveConnections();
        elements.forEach(this::addDraggableElement);

        // ==============================================
        // 4. Optimización del Redibujado (60 FPS)
        // ==============================================
        AnimationTimer redrawTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 16_666_666) { // 60 FPS (~16.66 ms)
                    redrawConnections(); // Redibujar solo si es necesario
                    lastUpdate = now;
                }
            }
        };
        redrawTimer.start();

        // ==============================================
        // 5. Desplazamiento Automático al Arrastrar
        // ==============================================
        container.setOnMouseDragged(event -> {
            Bounds viewport = scrollPane.getViewportBounds();
            double margin = 50;

            // Desplazamiento horizontal controlado
            if (event.getX() < margin) {
                scrollPane.setHvalue(scrollPane.getHvalue() - 0.02);
            } else if (event.getX() > viewport.getWidth() - margin) {
                scrollPane.setHvalue(scrollPane.getHvalue() + 0.02);
            }

            // Desplazamiento vertical controlado
            if (event.getY() < margin) {
                scrollPane.setVvalue(scrollPane.getVvalue() - 0.02);
            } else if (event.getY() > viewport.getHeight() - margin) {
                scrollPane.setVvalue(scrollPane.getVvalue() + 0.02);
            }
        });

        // ==============================================
        // 6. Redibujado Inicial
        // ==============================================
        Platform.runLater(this::redrawConnections);
    }

    @FXML
    private void onAddElementButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("new_element_dialog.fxml"));
            Parent root = loader.load();
            NewElementDialog controller = loader.getController();
            controller.setAppController(this);

            Stage dialogStage = new Stage();
            dialogStage.initOwner(addElementBtn.getScene().getWindow()); // <--- Vincula al stage principal
            dialogStage.initModality(Modality.WINDOW_MODAL); // <--- Usa modalidad de ventana
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addElement(List<DiagramElement> diagramElements) {
        for(DiagramElement  newElement : diagramElements){
            this.elements.add(newElement);
        }
    }

    private void resolveConnections() {
        for (DiagramElement element : elements) {
            for (int i = 0; i < element.getConnections().size(); i++) {
                if (i < element.getRelations().size()) {
                    String targetName = element.getRelations().get(i);
                    DiagramElement targetElement = elements.stream().filter(e -> e.getName().equals(targetName)).findFirst().orElse(null);
                    element.getConnections().get(i).setTarget(targetElement);
                }
            }
        }
    }

    private void createAndAddElement(double x, double y, String name, String value, List<String> variables, List<String> methods, List<String> relations, List<ElementConnection> connectionsData) {
        DiagramElement newElement = new DiagramElement(name, value, variables, methods, relations, x, y);
        elements.add(newElement);
        // Las conexiones se resuelven en resolveConnections() después de que todos los elementos son creados
        for (int i = 0; i < Math.min(relations.size(), connectionsData.size()); i++) {
            newElement.getConnections().add(new ElementConnection(null, false)); // Inicialmente sin objetivo
        }
    }

    public ObservableList<DiagramElement> getElements() {
        return elements;
    }

    public void addElement(String name, String value, List<String> variables, List<String> methods, List<String> relations, List<ElementConnectionData> connectionsDataList, double x, double y) {
        DiagramElement newElement = new DiagramElement(name, value, variables, methods, relations, x, y);
        for (ElementConnectionData data : connectionsDataList) {
            DiagramElement target = elements.stream().filter(e -> e.getName().equals(data.getConnection())).findFirst().orElse(null);
            if (target != null) {
                newElement.getConnections().add(new ElementConnection(target, data.isMove()));
            }
        }
        elements.add(newElement);
        addDraggableElement(newElement);
        javafx.application.Platform.runLater(this::redrawConnections);
//        redrawConnections();
    }

    private void showEditDialog(DiagramElement elementToEdit) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("new_element_dialog.fxml"));
            Parent root = loader.load();
            NewElementDialog controller = loader.getController();
            controller.setAppController(this);
            controller.setElementToEdit(elementToEdit);
            controller.populateFields(elementToEdit);

            Stage dialogStage = new Stage();
            dialogStage.initOwner(container.getScene().getWindow()); // <--- Vincula al stage principal
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setTitle("Editar Elemento");
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateElement(DiagramElement elementToUpdate, String name, String value, List<String> variables, List<String> methods, List<String> relations, List<ElementConnectionData> connectionsDataList) {
        elementToUpdate.setName(name);
        elementToUpdate.setValue(value);
        elementToUpdate.getVariables().setAll(variables);
        elementToUpdate.getMethods().setAll(methods);
        elementToUpdate.getRelations().setAll(relations);
        elementToUpdate.getConnections().clear();
        for (ElementConnectionData data : connectionsDataList) {
            DiagramElement target = elements.stream().filter(e -> e.getName().equals(data.getConnection())).findFirst().orElse(null);
            if (target != null) {
                elementToUpdate.getConnections().add(new ElementConnection(target, data.isMove()));
            }
        }
        container.getChildren().stream()
                .filter(node -> node instanceof Pane && ((Pane) node).layoutXProperty().isEqualTo(elementToUpdate.xProperty()).get() && ((Pane) node).layoutYProperty().isEqualTo(elementToUpdate.yProperty()).get())
                .findFirst()
                .ifPresent(node -> {
                    Pane elementPane = (Pane) node;
                    ((javafx.scene.control.Label) elementPane.getChildren().get(0)).setText(elementToUpdate.getName());
                    ((javafx.scene.control.Label) elementPane.getChildren().get(1)).setText(elementToUpdate.getValue() != null ? "value: " + elementToUpdate.getValue() : "");
                });
        javafx.application.Platform.runLater(this::redrawConnections);
//        redrawConnections();
    }

    private void addDraggableElement(DiagramElement element) {
        Pane elementPane = new Pane();

        elementPane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10px; -fx-text-align: center;");
        elementPane.setPrefWidth(120);
        elementPane.setPrefHeight(80);

        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label(element.getName());
        javafx.scene.control.Label valueLabel = new javafx.scene.control.Label(element.getValue() != null ? "value: " + element.getValue() : "");
        valueLabel.setLayoutY(20);

        elementPane.getChildren().addAll(nameLabel, valueLabel);
        elementPane.layoutXProperty().bind(element.xProperty());
        elementPane.layoutYProperty().bind(element.yProperty());
        elementPane.setCursor(javafx.scene.Cursor.HAND);

        final AtomicReference<Double> mousePressX = new AtomicReference<>();
        final AtomicReference<Double> mousePressY = new AtomicReference<>();
        final List<ElementConnection> relevantConnections = new ArrayList<>();

        // Agregar al mapa aquí
        elementPaneMap.put(element, elementPane);

        elementPane.setOnMousePressed(event -> {
            selectedElement = element;
            dragOffsetX = event.getSceneX() - element.getX();
            dragOffsetY = event.getSceneY() - element.getY();
            mousePressX.set(event.getSceneX());
            mousePressY.set(event.getSceneY());

            relevantConnections.clear();
            // Recopilar las conexiones donde este elemento es la fuente
            relevantConnections.addAll(element.getConnections());
            // Recopilar las conexiones donde este elemento es el destino
            for (DiagramElement otherElement : elements) {
                for (ElementConnection conn : otherElement.getConnections()) {
                    if (conn.getTarget() == element) {
                        relevantConnections.add(conn);
                    }
                }
            }
            System.out.println("Pressed: SceneX=" + event.getSceneX() + ", SceneY=" + event.getSceneY());

        });

//        elementPane.setOnMouseDragged(event -> {
//            if (selectedElement == element) {
//                element.setX(event.getSceneX() - dragOffsetX);
//                element.setY(event.getSceneY() - dragOffsetY);
//                redrawRelevantConnections(relevantConnections); // Redibujar solo las relevantes
////                redrawConnections(); // Forzar redibujado en tiempo real
//            }
//        });


        elementPane.setOnMouseDragged(event -> {
            if (selectedElement == element) {
                // Actualizar posición del elemento
                double newX = event.getSceneX() - dragOffsetX;
                double newY = event.getSceneY() - dragOffsetY;
                element.setX(newX);
                element.setY(newY);

                // Expandir contenedor si el elemento está cerca del borde
                double expandMargin = 100;
                if (newX + elementPane.getWidth() > container.getWidth() - expandMargin) {
                    container.setPrefWidth(newX + elementPane.getWidth() + expandMargin);
                }
                if (newY + elementPane.getHeight() > container.getHeight() - expandMargin) {
                    container.setPrefHeight(newY + elementPane.getHeight() + expandMargin);
                }

//                redrawRelevantConnections(relevantConnections); // Redibujar solo las relevantes
                redrawConnections(); // Forzar redibujado en tiempo real
            }
        });



        elementPane.setOnMouseReleased(event -> {
            if (selectedElement == element) {
                double deltaX = Math.abs(event.getSceneX() - mousePressX.get());
                double deltaY = Math.abs(event.getSceneY() - mousePressY.get());
                double clickThreshold = 5.0;
//                double clickThreshold = 10.0; // Aumenta el umbral (ejemplo)
                if (deltaX < clickThreshold && deltaY < clickThreshold) {
                    showEditDialog(element);
                }
                selectedElement = null;
                javafx.application.Platform.runLater(this::redrawConnections);
//                redrawConnections(); // Volver a redibujar todas las conexiones al soltar
            }
        });


        container.getChildren().add(elementPane);
    }

    public void redrawConnections() {
        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);

        for (DiagramElement element : elements) {
            Pane sourcePane = findElementPane(element);
            if (sourcePane == null) continue;

            // Coordenadas absolutas en el contenedor
            double x1 = element.getX() + sourcePane.getWidth() / 2;
            double y1 = element.getY() + sourcePane.getHeight() / 2;

            for (ElementConnection connection : element.getConnections()) {
                DiagramElement target = connection.getTarget();
                if (target == null) continue;

                Pane targetPane = findElementPane(target);
                if (targetPane == null) continue;

                double x2 = target.getX() + targetPane.getWidth() / 2;
                double y2 = target.getY() + targetPane.getHeight() / 2;

                // Dibujar sin ajustar el desplazamiento
                gc.strokeLine(x1, y1, x2, y2);
                drawMarker(gc, x1, y1, x2, y2, connection.canMove());
            }
        }
    }

    private void drawMarker(GraphicsContext gc, double x1, double y1, double x2, double y2, boolean canMove) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double length = Math.sqrt(dx * dx + dy * dy);

        if (length > 0) {
            double markerX = x1 + dx * 0.5;
            double markerY = y1 + dy * 0.5;

            if (canMove) {
                // Dibujar flecha
                double angle = Math.atan2(dy, dx);
                double arrowSize = 10;
                gc.setFill(Color.BLUE);
                gc.setStroke(Color.WHITE);
                gc.setLineWidth(1);
                gc.beginPath();
                gc.moveTo(markerX + arrowSize * Math.cos(angle), markerY + arrowSize * Math.sin(angle));
                gc.lineTo(markerX - arrowSize * Math.cos(angle) - arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) + arrowSize * Math.cos(angle));
                gc.lineTo(markerX - arrowSize * Math.cos(angle) + arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) - arrowSize * Math.cos(angle));
                gc.closePath();
                gc.fill();
                gc.stroke();
            } else {
                // Dibujar cuadrado
                double squareSize = 7;
                gc.setFill(Color.RED);
                gc.setStroke(Color.WHITE);
                gc.fillRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
                gc.strokeRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
            }
        }
    }

    private Pane findElementPane(DiagramElement element) {
        return elementPaneMap.get(element);
    }

//    public static class Pair<K, V> {
//
//        private final K key;
//        private final V value;
//
//        public Pair(K key, V value) {
//            this.key = key;
//            this.value = value;
//        }
//
//        public K getKey() {
//            return key;
//        }
//
//        public V getValue() {
//            return value;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Pair<?, ?> pair = (Pair<?, ?>) o;
//            return (key == null ? pair.key == null : key.equals(pair.key)) &&
//                    (value == null ? pair.value == null : value.equals(pair.value));
//        }
//
//        @Override
//        public int hashCode() {
//            int result = key != null ? key.hashCode() : 0;
//            result = 31 * result + (value != null ? value.hashCode() : 0);
//            return result;
//        }
//
//        public static <K, V> Pair<K, V> of(K key, V value) {
//            return new Pair<>(key, value);
//        }
//    }


//
//    @FXML
//    public void initialize23() {
//
//
//        // Vincular tamaño del Canvas al contenedor
//        container.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
//            linesCanvas.setWidth(newVal.getWidth());
//            linesCanvas.setHeight(newVal.getHeight());
//            redrawConnections();
//        });
//
//
//        scrollPane.hvalueProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//        scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//
//        // Establecer el color de fondo del container a negro
//        container.setStyle("-fx-background-color: black;");
//
//        // Vincular el tamaño del Canvas al ScrollPane
//        scrollPane.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {
//            linesCanvas.setWidth(newVal.getWidth());
//            linesCanvas.setHeight(newVal.getHeight());
//            redrawConnections();
//        });
//
//        // Escuchar cambios de tamaño del contenedor
//        container.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
//            linesCanvas.setWidth(newVal.getWidth());
//            linesCanvas.setHeight(newVal.getHeight());
//            redrawConnections();
//        });
//
//        // Inicializar elementos predefinidos con sus relaciones
//        addElement(Element.getDiagramElementList());
//        resolveConnections(); // Llamar a resolveConnections para establecer los objetivos de las conexiones
//
//        elements.forEach(this::addDraggableElement);
//        container.widthProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//        container.heightProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
////        redrawConnections();
//        javafx.application.Platform.runLater(this::redrawConnections);
//    }
//
//    @FXML
//    public void initialize24ok() {
//        // ==============================================
//        // 1. Configuración Inicial del Contenedor y Canvas
//        // ==============================================
//        container.setStyle("-fx-background-color: black;");
//
//        // Listener único para cambios de tamaño del contenedor
//        container.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
//            linesCanvas.setWidth(newVal.getWidth());
//            linesCanvas.setHeight(newVal.getHeight());
//        });
//
//        // ==============================================
//        // 2. Configuración del ScrollPane y Desplazamiento
//        // ==============================================
//        // Listener para desplazamiento y viewport
//        scrollPane.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//        scrollPane.hvalueProperty().addListener((obs, o, n) -> redrawConnections());
//        scrollPane.vvalueProperty().addListener((obs, o, n) -> redrawConnections());
//
//        // ==============================================
//        // 3. Carga de Elementos y Conexiones
//        // ==============================================
//        addElement(Element.getDiagramElementList()); // Nombre corregido
//        resolveConnections();
//        elements.forEach(this::addDraggableElement);
//
//        // ==============================================
//        // 4. Optimización del Redibujado (60 FPS)
//        // ==============================================
//        AnimationTimer redrawTimer = new AnimationTimer() {
//            private long lastUpdate = 0;
//
//            @Override
//            public void handle(long now) {
//                if (now - lastUpdate >= 16_666_666) { // 60 FPS
//                    redrawConnections();
//                    lastUpdate = now;
//                }
//            }
//        };
//        redrawTimer.start();
//
//        // ==============================================
//        // 5. Desplazamiento Automático al Arrastrar
//        // ==============================================
//        container.setOnMouseDragged(event -> {
//            Bounds viewport = scrollPane.getViewportBounds();
//            double margin = 50; // Margen de desplazamiento
//
//            // Lógica de desplazamiento horizontal
//            if (event.getX() < margin) {
//                scrollPane.setHvalue(scrollPane.getHvalue() - 0.02);
//            } else if (event.getX() > viewport.getWidth() - margin) {
//                scrollPane.setHvalue(scrollPane.getHvalue() + 0.02);
//            }
//
//            // Lógica de desplazamiento vertical
//            if (event.getY() < margin) {
//                scrollPane.setVvalue(scrollPane.getVvalue() - 0.02);
//            } else if (event.getY() > viewport.getHeight() - margin) {
//                scrollPane.setVvalue(scrollPane.getVvalue() + 0.02);
//            }
//        });
//
//        // ==============================================
//        // 6. Redibujado Inicial
//        // ==============================================
//        Platform.runLater(this::redrawConnections);
//    }
//    @FXML
//    public void initialize2() {
//        // Establecer el color de fondo del container a negro
//        container.setStyle("-fx-background-color: black;");
//
//        // Inicializar elementos predefinidos (puedes ajustar la cantidad para pruebas)
//        for (int i = 0; i < 50; i++) {
//            createAndAddElement(100 + i * 50, 100, "Elemento " + i, String.valueOf(i), Arrays.asList("var"), Arrays.asList("method"), Arrays.asList(), new ArrayList<>());
//        }
//        resolveConnections();
//        elements.forEach(this::addDraggableElement);
//        container.widthProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//        container.heightProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//
//        // Iniciar un AnimationTimer para redibujar las conexiones solo cuando no se está arrastrando activamente
//        AnimationTimer redrawTimer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                if (!isDragging.get()) {
//
//                }
//            }
//        };
//        redrawTimer.start();
//    }
//
//    @FXML
//    public void initialize1() {
//        // Establecer el color de fondo del container a negro
//        container.setStyle("-fx-background-color: black;");
//
//        // Inicializar elementos predefinidos con sus relaciones
//        createAndAddElement(100, 100, "Inicio", "A", Arrays.asList("var1", "var2"), Arrays.asList("met1"), Arrays.asList("Proceso", "Fin"),
//                Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true), new ElementConnection(null, true)));
//        createAndAddElement(250, 100, "Proceso", "B", Arrays.asList("data", "count"), Arrays.asList("process", "validate"), Arrays.asList("Decisión", "Fin"),
//                Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, false)));
//        createAndAddElement(400, 100, "Decisión", "C", Arrays.asList("flag"), Arrays.asList("checkCondition", "method1()", "method2()"), Arrays.asList("Proceso", "Fin"),
//                Arrays.asList(new ElementConnection(null, false), new ElementConnection(null, true)));
//
//        resolveConnections(); // Llamar a resolveConnections para establecer los objetivos de las conexiones
//
//        elements.forEach(this::addDraggableElement);
//        container.widthProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//        container.heightProperty().addListener((obs, oldVal, newVal) -> redrawConnections());
//
//        // Iniciar un AnimationTimer para redibujar las conexiones solo cuando no se está arrastrando activamente
//        AnimationTimer redrawTimer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                if (!isDragging.get()) {
//                    redrawConnections();
//                }
//            }
//        };
//        redrawTimer.start();
//        redrawConnections();
//    }
//
//    private void createAndAddElement( DiagramElement recibeElement) {
//        DiagramElement newElement = new DiagramElement(recibeElement.getName(), recibeElement.getValue(),
//                recibeElement.getVariables(), recibeElement.getMethods(), recibeElement.getRelations(), recibeElement.getX(), recibeElement.getY());
//        elements.add(newElement);
//
//        // Las conexiones se resuelven en resolveConnections() después de que todos los elementos son creados
//        for (int i = 0; i < Math.min(recibeElement.getRelations().size(), recibeElement.getConnections().size()); i++) {
//            newElement.getConnections().add(new ElementConnection(null, false)); // Inicialmente sin objetivo
//        }
//    }
//
//    @FXML
//    private void onAddElementButtonClick1() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("new_element_dialog.fxml"));
//            Parent root = loader.load();
//            NewElementDialog controller = loader.getController();
//            controller.setAppController(this);
//
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Nuevo Elemento");
//            dialogStage.initModality(Modality.APPLICATION_MODAL);
//            dialogStage.setScene(new Scene(root));
//            dialogStage.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addDraggableElement1(DiagramElement element) {
//        Pane elementPane = new Pane();
//        elementPane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10px; -fx-text-align: center;");
//        elementPane.setPrefWidth(120);
//        elementPane.setPrefHeight(80);
//
//        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label(element.getName());
//        javafx.scene.control.Label valueLabel = new javafx.scene.control.Label(element.getValue() != null ? "value: " + element.getValue() : "");
//        valueLabel.setLayoutY(20);
//
//        elementPane.getChildren().addAll(nameLabel, valueLabel);
//        elementPane.layoutXProperty().bind(element.xProperty());
//        elementPane.layoutYProperty().bind(element.yProperty());
//        elementPane.setCursor(javafx.scene.Cursor.HAND);
//
//        final AtomicReference<Double> mousePressX = new AtomicReference<>();
//        final AtomicReference<Double> mousePressY = new AtomicReference<>();
//
//        elementPane.setOnMousePressed(event -> {
//            selectedElement = element;
//            dragOffsetX = event.getSceneX() - element.getX();
//            dragOffsetY = event.getSceneY() - element.getY();
//            mousePressX.set(event.getSceneX());
//            mousePressY.set(event.getSceneY());
//            isDragging.set(true); // Indicar que se está arrastrando
//        });
//
//
//
////        elementPane.setOnMouseDragged(event -> {
////            if (selectedElement == element) {
////                element.setX(event.getSceneX() - dragOffsetX);
////                element.setY(event.getSceneY() - dragOffsetY);
////                // No redibujar las conexiones aquí durante el arrastre intenso
////            }
////        });
//
////        elementPane.setOnMouseDragged(event -> {
////            if (selectedElement == element) {
////                // Actualizar posición del elemento
////                element.setX(event.getSceneX() - dragOffsetX);
////                element.setY(event.getSceneY() - dragOffsetY);
////
////                // Calcular márgenes para desplazamiento automático
////                double margin = 50; // Margen en píxeles
////                Bounds viewport = scrollPane.getViewportBounds();
////                double elemX = element.getX();
////                double elemY = element.getY();
////
////                // Desplazar horizontalmente
////                if (elemX < scrollPane.getHvalue() * (container.getWidth() - viewport.getWidth()) + margin) {
////                    scrollPane.setHvalue(Math.max(0, (elemX - margin) / container.getWidth()));
////                } else if (elemX > scrollPane.getHvalue() * (container.getWidth() - viewport.getWidth()) + viewport.getWidth() - margin) {
////                    scrollPane.setHvalue(Math.min(1, (elemX + margin) / container.getWidth()));
////                }
////
////                // Desplazar verticalmente
////                if (elemY < scrollPane.getVvalue() * (container.getHeight() - viewport.getHeight()) + margin) {
////                    scrollPane.setVvalue(Math.max(0, (elemY - margin) / container.getHeight()));
////                } else if (elemY > scrollPane.getVvalue() * (container.getHeight() - viewport.getHeight()) + viewport.getHeight() - margin) {
////                    scrollPane.setVvalue(Math.min(1, (elemY + margin) / container.getHeight()));
////                }
////
////                redrawConnections();
////            }
////        });
//
//        elementPane.setOnMouseDragged(event -> {
//            if (selectedElement == element) {
//                // Calcular nueva posición
//                double newX = event.getSceneX() - dragOffsetX;
//                double newY = event.getSceneY() - dragOffsetY;
//
//                // Limitar movimiento a los bordes actuales del contenedor
//                double maxX = container.getWidth() - elementPane.getWidth();
//                double maxY = container.getHeight() - elementPane.getHeight();
//                newX = Math.max(0, Math.min(newX, maxX));
//                newY = Math.max(0, Math.min(newY, maxY));
//
//                // Actualizar posición del elemento
//                element.setX(newX);
//                element.setY(newY);
//
//                // Desplazamiento automático solo si el elemento está en el borde
//                handleAutoScroll(event.getX(), event.getY());
//
//                redrawConnections();
//            }
//        });
//
//
//
//
//        elementPane.setOnMouseReleased(event -> {
//            if (selectedElement == element) {
//                double deltaX = Math.abs(event.getSceneX() - mousePressX.get());
//                double deltaY = Math.abs(event.getSceneY() - mousePressY.get());
//                double clickThreshold = 5.0;
//
//                if (deltaX < clickThreshold && deltaY < clickThreshold) {
//                    showEditDialog(element);
//                }
//                selectedElement = null;
//                isDragging.set(false); // Indicar que se ha terminado de arrastrar
////                redrawConnections(); // Redibujar las conexiones al final del arrastre
//                javafx.application.Platform.runLater(this::redrawConnections);
//            }
//        });
//
//        container.getChildren().add(elementPane);
//    }
//
//
//
//    private void handleContainerExpansion(double elementX, double elementY, Pane elementPane) {
//        double expandThreshold = 50; // Distancia al borde para expandir
//        double expandAmount = 100; // Cantidad de expansión
//
//        // Expandir a la derecha
//        if (elementX + elementPane.getWidth() > container.getWidth() - expandThreshold) {
//            container.setPrefWidth(container.getWidth() + expandAmount);
//        }
//
//        // Expandir hacia abajo
//        if (elementY + elementPane.getHeight() > container.getHeight() - expandThreshold) {
//            container.setPrefHeight(container.getHeight() + expandAmount);
//        }
//    }
//
//    private void addDraggableElement2(DiagramElement element) {
//        Pane elementPane = new Pane();
//        elementPane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10px; -fx-text-align: center;");
//        elementPane.setPrefWidth(120);
//        elementPane.setPrefHeight(80);
//
//        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label(element.getName());
//        javafx.scene.control.Label valueLabel = new javafx.scene.control.Label(element.getValue() != null ? "value: " + element.getValue() : "");
//        valueLabel.setLayoutY(20);
//
//        elementPane.getChildren().addAll(nameLabel, valueLabel);
//        elementPane.layoutXProperty().bind(element.xProperty());
//        elementPane.layoutYProperty().bind(element.yProperty());
//        elementPane.setCursor(javafx.scene.Cursor.HAND);
//
//        final AtomicReference<Double> mousePressX = new AtomicReference<>();
//        final AtomicReference<Double> mousePressY = new AtomicReference<>();
//
//        elementPane.setOnMousePressed(event -> {
//            selectedElement = element;
//            dragOffsetX = event.getSceneX() - element.getX();
//            dragOffsetY = event.getSceneY() - element.getY();
//            mousePressX.set(event.getSceneX());
//            mousePressY.set(event.getSceneY());
//        });
//
//        elementPane.setOnMouseDragged(event -> {
//            if (selectedElement == element) {
//                element.setX(event.getSceneX() - dragOffsetX);
//                element.setY(event.getSceneY() - dragOffsetY);
////                redrawConnections(); // Redibujar las conexiones en cada arrastre
//                javafx.application.Platform.runLater(this::redrawConnections);
//            }
//        });
//
//
//
//
//        elementPane.setOnMouseReleased(event -> {
//            if (selectedElement == element) {
//                double deltaX = Math.abs(event.getSceneX() - mousePressX.get());
//                double deltaY = Math.abs(event.getSceneY() - mousePressY.get());
//                double clickThreshold = 5.0;
//
//                if (deltaX < clickThreshold && deltaY < clickThreshold) {
//                    showEditDialog(element);
//                }
//                selectedElement = null;
//            }
//        });
//
//        container.getChildren().add(elementPane);
//    }
//
//    private void addDraggableElement3(DiagramElement element) {
//        Pane elementPane = new Pane();
//        elementPane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10px; -fx-text-align: center;");
//        elementPane.setPrefWidth(120);
//        elementPane.setPrefHeight(80);
//
//        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label(element.getName());
//        javafx.scene.control.Label valueLabel = new javafx.scene.control.Label(element.getValue() != null ? "value: " + element.getValue() : "");
//        valueLabel.setLayoutY(20);
//
//        elementPane.getChildren().addAll(nameLabel, valueLabel);
//        elementPane.layoutXProperty().bind(element.xProperty());
//        elementPane.layoutYProperty().bind(element.yProperty());
//        elementPane.setCursor(javafx.scene.Cursor.HAND);
//
//        final AtomicReference<Double> mousePressX = new AtomicReference<>();
//        final AtomicReference<Double> mousePressY = new AtomicReference<>();
//        final List<ElementConnection> relevantConnections = new ArrayList<>();
//
//        elementPane.setOnMousePressed(event -> {
//            selectedElement = element;
//            dragOffsetX = event.getSceneX() - element.getX();
//            dragOffsetY = event.getSceneY() - element.getY();
//            mousePressX.set(event.getSceneX());
//            mousePressY.set(event.getSceneY());
//
//            relevantConnections.clear();
//            // Recopilar las conexiones donde este elemento es la fuente
//            relevantConnections.addAll(element.getConnections());
//            // Recopilar las conexiones donde este elemento es el destino
//            for (DiagramElement otherElement : elements) {
//                for (ElementConnection conn : otherElement.getConnections()) {
//                    if (conn.getTarget() == element) {
//                        relevantConnections.add(conn);
//                    }
//                }
//            }
//        });
//
//
//
//        elementPane.setOnMouseDragged(event -> {
//            if (selectedElement == element) {
//                element.setX(event.getSceneX() - dragOffsetX);
//                element.setY(event.getSceneY() - dragOffsetY);
//                redrawRelevantConnections(relevantConnections); // Redibujar solo las relevantes
//            }
//        });
//
////        elementPane.setOnMouseReleased(event -> {
////            if (selectedElement == element) {
////                double deltaX = Math.abs(event.getSceneX() - mousePressX.get());
////                double deltaY = Math.abs(event.getSceneY() - mousePressY.get());
////                double clickThreshold = 5.0;
////
////                if (deltaX < clickThreshold && deltaY < clickThreshold) {
////                    showEditDialog(element);
////                }
////                selectedElement = null;
////                redrawConnections(); // Asegurar el redibujo completo al final
////            }
////        });
//
//        elementPane.setOnMouseReleased(event -> {
//            if (selectedElement == element) {
//                double deltaX = Math.abs(event.getSceneX() - mousePressX.get());
//                double deltaY = Math.abs(event.getSceneY() - mousePressY.get());
//                double clickThreshold = 5.0;
//
//                if (deltaX < clickThreshold && deltaY < clickThreshold) {
//                    showEditDialog(element);
//                }
//                selectedElement = null;
////                redrawConnections(); // Volver a redibujar todas las conexiones al soltar
//                javafx.application.Platform.runLater(this::redrawConnections);
//            }
//        });
//
//        container.getChildren().add(elementPane);
//    }
//    private void redrawConnections1() {
//        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(2);
//
//        for (DiagramElement element : elements) {
//            Pane sourcePane = findElementPane(element);
//            if (sourcePane == null) continue;
//            double x1 = element.getX() + sourcePane.getWidth() / 2;
//            double y1 = element.getY() + sourcePane.getHeight() / 2;
//
//            for (ElementConnection connection : element.getConnections()) {
//                DiagramElement targetElement = connection.getTarget();
//                if (targetElement != null) {
//                    Pane targetPane = findElementPane(targetElement);
//                    if (targetPane == null) continue;
//                    double x2 = targetElement.getX() + targetPane.getWidth() / 2;
//                    double y2 = targetElement.getY() + targetPane.getHeight() / 2;
//                    gc.strokeLine(x1, y1, x2, y2);
//
//                    // Dibujar marcadores (flechas o cuadrados)
//                    double dx = x2 - x1;
//                    double dy = y2 - y1;
//                    double length = Math.sqrt(dx * dx + dy * dy);
//                    if (length > 0) {
//                        double markerX = x1 + dx * 0.5;
//                        double markerY = y1 + dy * 0.5;
//
//                        if (connection.canMove()) {
//                            // Dibujar flecha
//                            double angle = Math.atan2(dy, dx);
//                            double arrowSize = 10;
//                            gc.setFill(Color.BLUE);
//                            gc.setStroke(Color.WHITE);
//                            gc.setLineWidth(1);
//                            gc.beginPath();
//                            gc.moveTo(markerX + arrowSize * Math.cos(angle), markerY + arrowSize * Math.sin(angle));
//                            gc.lineTo(markerX - arrowSize * Math.cos(angle) - arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) + arrowSize * Math.cos(angle));
//                            gc.lineTo(markerX - arrowSize * Math.cos(angle) + arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) - arrowSize * Math.cos(angle));
//                            gc.closePath();
//                            gc.fill();
//                            gc.stroke();
//                        } else {
//                            // Dibujar cuadrado
//                            double squareSize = 7;
//                            gc.setFill(Color.RED);
//                            gc.setStroke(Color.WHITE);
//                            gc.fillRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
//                            gc.strokeRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
//                        }
//                    }
//                }
//            }
//        }
//    }
//    private void showEditDialog1(DiagramElement elementToEdit) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("new_element_dialog.fxml"));
//            Parent root = loader.load();
//            NewElementDialog controller = loader.getController();
//            controller.setAppController(this);
//            controller.setElementToEdit(elementToEdit);
//            controller.populateFields(elementToEdit);
//
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Editar Elemento");
//            dialogStage.initModality(Modality.APPLICATION_MODAL);
//            dialogStage.setScene(new Scene(root));
//            dialogStage.showAndWait();
////            redrawConnections();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //    private Pane findElementPane(DiagramElement element) {
//        return container.getChildren().stream()
//                .filter(node -> node instanceof Pane && ((Pane) node).layoutXProperty().isEqualTo(element.xProperty()).get() && ((Pane) node).layoutYProperty().isEqualTo(element.yProperty()).get())
//                .findFirst()
//                .map(node -> (Pane) node)
//                .orElse(null);
//    }

    //    private void redrawConnections1() {
//        long startTime = System.nanoTime();
//        System.out.println("Redrawing connections START...");
//
//        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(2);
//
//        for (DiagramElement element : elements) {
//            Pane sourcePane = findElementPane(element);
//            if (sourcePane == null) continue;
//            double x1 = element.getX() + sourcePane.getWidth() / 2;
//            double y1 = element.getY() + sourcePane.getHeight() / 2;
//
//            for (ElementConnection connection : element.getConnections()) {
//                DiagramElement targetElement = connection.getTarget();
//                if (targetElement != null) {
//                    Pane targetPane = findElementPane(targetElement);
//                    if (targetPane == null) continue;
//                    double x2 = targetElement.getX() + targetPane.getWidth() / 2;
//                    double y2 = targetElement.getY() + targetPane.getHeight() / 2;
//                    gc.strokeLine(x1, y1, x2, y2);
//
//                    // Dibujar marcadores (flechas o cuadrados)
//                    double dx = x2 - x1;
//                    double dy = y2 - y1;
//                    double length = Math.sqrt(dx * dx + dy * dy);
//                    if (length > 0) {
//                        double markerX = x1 + dx * 0.5;
//                        double markerY = y1 + dy * 0.5;
//
//                        if (connection.canMove()) {
//                            // Dibujar flecha
//                            double angle = Math.atan2(dy, dx);
//                            double arrowSize = 10;
//                            gc.setFill(Color.BLUE);
//                            gc.setStroke(Color.WHITE);
//                            gc.setLineWidth(1);
//                            gc.beginPath();
//                            gc.moveTo(markerX + arrowSize * Math.cos(angle), markerY + arrowSize * Math.sin(angle));
//                            gc.lineTo(markerX - arrowSize * Math.cos(angle) - arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) + arrowSize * Math.cos(angle));
//                            gc.lineTo(markerX - arrowSize * Math.cos(angle) + arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) - arrowSize * Math.cos(angle));
//                            gc.closePath();
//                            gc.fill();
//                            gc.stroke();
//                        } else {
//                            // Dibujar cuadrado
//                            double squareSize = 7;
//                            gc.setFill(Color.RED);
//                            gc.setStroke(Color.WHITE);
//                            gc.fillRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
//                            gc.strokeRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println("Redrawing connections END.");
//        long endTime = System.nanoTime();
//        System.out.println("Redraw time: " + (endTime - startTime) / 1_000_000 + " ms");
//    }

//    private void redrawConnections39() {
//        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
//
//        // Dibujar todas las conexiones
//        for (DiagramElement element : elements) {
//            Pane sourcePane = findElementPane(element);
//            if (sourcePane == null) continue;
//
//            // Coordenadas relativas al contenedor (no a la pantalla)
//            double x1 = element.getX() + sourcePane.getWidth() / 2;
//            double y1 = element.getY() + sourcePane.getHeight() / 2;
//
//            for (ElementConnection connection : element.getConnections()) {
//                DiagramElement target = connection.getTarget();
//                if (target != null) {
//                    Pane targetPane = findElementPane(target);
//                    if (targetPane == null) continue;
//
//                    double x2 = target.getX() + targetPane.getWidth() / 2;
//                    double y2 = target.getY() + targetPane.getHeight() / 2;
//
//                    // Dibujar línea
//                    gc.setStroke(Color.WHITE);
//                    gc.strokeLine(x1, y1, x2, y2);
//                }
//            }
//        }
//    }
//
//    private void redrawConnections22() {
//        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(2);
//
//        // Obtener el desplazamiento actual del ScrollPane
//        double scrollX = scrollPane.getHvalue();
//        double scrollY = scrollPane.getVvalue();
//        double viewportWidth = scrollPane.getViewportBounds().getWidth();
//        double viewportHeight = scrollPane.getViewportBounds().getHeight();
//
//        for (DiagramElement element : elements) {
//            Pane sourcePane = findElementPane(element);
//            if (sourcePane == null) continue;
//
//            // Calcular coordenadas relativas al viewport
//            double x1 = element.getX() + sourcePane.getWidth() / 2 - (scrollX * (container.getWidth() - viewportWidth));
//            double y1 = element.getY() + sourcePane.getHeight() / 2 - (scrollY * (container.getHeight() - viewportHeight));
//
//            for (ElementConnection connection : element.getConnections()) {
//                DiagramElement targetElement = connection.getTarget();
//                if (targetElement != null) {
//                    Pane targetPane = findElementPane(targetElement);
//                    if (targetPane == null) continue;
//
//                    double x2 = targetElement.getX() + targetPane.getWidth() / 2 - (scrollX * (container.getWidth() - viewportWidth));
//                    double y2 = targetElement.getY() + targetPane.getHeight() / 2 - (scrollY * (container.getHeight() - viewportHeight));
//
//                    gc.strokeLine(x1, y1, x2, y2);
//                    drawMarker(gc, x1, y1, x2, y2, connection.canMove());
//                }
//            }
//        }
//    }
//
//    private void redrawConnections23() {
//        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(2);
//
//        // Obtener el área visible del ScrollPane
//        Bounds viewportBounds = scrollPane.getViewportBounds();
//        double containerWidth = container.getWidth();
//        double containerHeight = container.getHeight();
//
//        for (DiagramElement element : elements) {
//            Pane sourcePane = findElementPane(element);
//            if (sourcePane == null) continue;
//
//            // Calcular coordenadas absolutas
//            double x1 = element.getX() + sourcePane.getWidth() / 2;
//            double y1 = element.getY() + sourcePane.getHeight() / 2;
//
//            for (ElementConnection connection : element.getConnections()) {
//                DiagramElement target = connection.getTarget();
//                if (target == null) continue;
//
//                Pane targetPane = findElementPane(target);
//                if (targetPane == null) continue;
//
//                double x2 = target.getX() + targetPane.getWidth() / 2;
//                double y2 = target.getY() + targetPane.getHeight() / 2;
//
//                // Ajustar al viewport
//                double adjustedX1 = x1 - (scrollPane.getHvalue() * (containerWidth - viewportBounds.getWidth()));
//                double adjustedY1 = y1 - (scrollPane.getVvalue() * (containerHeight - viewportBounds.getHeight()));
//                double adjustedX2 = x2 - (scrollPane.getHvalue() * (containerWidth - viewportBounds.getWidth()));
//                double adjustedY2 = y2 - (scrollPane.getVvalue() * (containerHeight - viewportBounds.getHeight()));
//
//                gc.strokeLine(adjustedX1, adjustedY1, adjustedX2, adjustedY2);
//                drawMarker(gc, adjustedX1, adjustedY1, adjustedX2, adjustedY2, connection.canMove());
//            }
//        }
//    }
//
//    public void redrawConnections24() {
//        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(2);
//
//        Bounds viewportBounds = scrollPane.getViewportBounds();
//        double containerWidth = container.getWidth();
//        double containerHeight = container.getHeight();
//
//        // Calcular ancho/alto del viewport
//        double viewportWidth = viewportBounds.getMaxX() - viewportBounds.getMinX();
//        double viewportHeight = viewportBounds.getMaxY() - viewportBounds.getMinY();
//
//        for (DiagramElement element : elements) {
//            Pane sourcePane = findElementPane(element);
//            if (sourcePane == null) continue;
//
//            double x1 = element.getX() + sourcePane.getWidth() / 2;
//            double y1 = element.getY() + sourcePane.getHeight() / 2;
//
//            for (ElementConnection connection : element.getConnections()) {
//                DiagramElement target = connection.getTarget();
//                if (target == null) continue;
//
//                Pane targetPane = findElementPane(target);
//                if (targetPane == null) continue;
//
//                double x2 = target.getX() + targetPane.getWidth() / 2;
//                double y2 = target.getY() + targetPane.getHeight() / 2;
//
//                // Aplicar corrección del viewport
//                double adjustedX1 = x1 - (scrollPane.getHvalue() * (containerWidth - viewportWidth));
//                double adjustedY1 = y1 - (scrollPane.getVvalue() * (containerHeight - viewportHeight));
//                double adjustedX2 = x2 - (scrollPane.getHvalue() * (containerWidth - viewportWidth));
//                double adjustedY2 = y2 - (scrollPane.getVvalue() * (containerHeight - viewportHeight));
//
//                gc.strokeLine(adjustedX1, adjustedY1, adjustedX2, adjustedY2);
//                drawMarker(gc, adjustedX1, adjustedY1, adjustedX2, adjustedY2, connection.canMove());
//            }
//        }
//    }


//    private void redrawRelevantConnections(List<ElementConnection> connectionsToRedraw) {
//        GraphicsContext gc = linesCanvas.getGraphicsContext2D();
//        // Limpiar todo el canvas para asegurar que las líneas se actualicen correctamente
//        gc.clearRect(0, 0, linesCanvas.getWidth(), linesCanvas.getHeight());
//
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(2);
//
////        Set<Pair<Long, Long>> processedConnections = new HashSet<>();
//        Set<AppController.Pair<Long, Long>> processedConnections = new HashSet<>();
//
//        for (ElementConnection connection : connectionsToRedraw) {
//            DiagramElement sourceElement = null;
//            for (DiagramElement elem : elements) {
//                if (elem.getConnections().contains(connection)) {
//                    sourceElement = elem;
//                    break;
//                }
//            }
//            DiagramElement targetElement = connection.getTarget();
//
//            if (sourceElement != null && targetElement != null) {
//                long sourceId = sourceElement.getId();
//                long targetId = targetElement.getId();
//                AppController.Pair<Long, Long> connectionPair = Pair.of(Math.min(sourceId, targetId), Math.max(sourceId, targetId));
////                Pair<Long, Long> connectionPair = Pair.of(Math.min(sourceId, targetId), Math.max(sourceId, targetId));
//
//                if (processedConnections.contains(connectionPair)) {
//                    continue;
//                }
//                processedConnections.add(connectionPair);
//
//                Pane sourcePane = findElementPane(sourceElement);
//                Pane targetPane = findElementPane(targetElement);
//
//                if (sourcePane != null && targetPane != null) {
//                    double x1 = sourceElement.getX() + sourcePane.getWidth() / 2;
//                    double y1 = sourceElement.getY() + sourcePane.getHeight() / 2;
//                    double x2 = targetElement.getX() + targetPane.getWidth() / 2;
//                    double y2 = targetElement.getY() + targetPane.getHeight() / 2;
//                    gc.strokeLine(x1, y1, x2, y2);
//
//                    // Dibujar marcadores (flechas o cuadrados)
//                    double dx = x2 - x1;
//                    double dy = y2 - y1;
//                    double length = Math.sqrt(dx * dx + dy * dy);
//                    if (length > 0) {
//                        double markerX = x1 + dx * 0.5;
//                        double markerY = y1 + dy * 0.5;
//
//                        if (connection.canMove()) {
//                            // Dibujar flecha
//                            double angle = Math.atan2(dy, dx);
//                            double arrowSize = 10;
//                            gc.setFill(Color.WHITE);
//                            gc.setStroke(Color.WHITE);
//                            gc.setLineWidth(1);
//                            gc.beginPath();
//                            gc.moveTo(markerX + arrowSize * Math.cos(angle), markerY + arrowSize * Math.sin(angle));
//                            gc.lineTo(markerX - arrowSize * Math.cos(angle) - arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) + arrowSize * Math.cos(angle));
//                            gc.lineTo(markerX - arrowSize * Math.cos(angle) + arrowSize * Math.sin(angle), markerY - arrowSize * Math.sin(angle) - arrowSize * Math.cos(angle));
//                            gc.closePath();
//                            gc.fill();
//                            gc.stroke();
//                        } else {
//                            // Dibujar cuadrado
//                            double squareSize = 7;
//                            gc.setFill(Color.RED);
//                            gc.setStroke(Color.WHITE);
//                            gc.fillRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
//                            gc.strokeRect(markerX - squareSize, markerY - squareSize, 2 * squareSize, 2 * squareSize);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private void handleAutoScroll(double mouseX, double mouseY) {
//        Bounds viewport = scrollPane.getViewportBounds();
//        double margin = 50; // Margen para activar el desplazamiento
//        double scrollSpeed = 0.02; // Velocidad de desplazamiento
//
//        // Desplazamiento horizontal
//        if (mouseX < margin) {
//            scrollPane.setHvalue(Math.max(0, scrollPane.getHvalue() - scrollSpeed));
//        } else if (mouseX > viewport.getWidth() - margin) {
//            scrollPane.setHvalue(Math.min(1, scrollPane.getHvalue() + scrollSpeed));
//        }
//
//        // Desplazamiento vertical
//        if (mouseY < margin) {
//            scrollPane.setVvalue(Math.max(0, scrollPane.getVvalue() - scrollSpeed));
//        } else if (mouseY > viewport.getHeight() - margin) {
//            scrollPane.setVvalue(Math.min(1, scrollPane.getVvalue() + scrollSpeed));
//        }
//    }

}