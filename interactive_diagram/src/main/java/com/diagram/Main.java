package com.diagram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("app.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
//        stage.setTitle("Diagrama Interactivo");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Escuchar cambios de pantalla completa
        stage.fullScreenProperty().addListener((obs, oldVal, newVal) -> {
            AppController controller = fxmlLoader.getController();
            controller.redrawConnections();
        });

        stage.setTitle("Diagrama Interactivo");
        stage.setScene(scene);
        stage.show();
    }
}

