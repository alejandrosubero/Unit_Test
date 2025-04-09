package com.diagram;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewElementDialog {

    @FXML
    private TextField newName;

    @FXML
    private TextField newValue;

    @FXML
    private TextField newVariables;

    @FXML
    private TextField newMethods;

    @FXML
    private TextField newRelations;

    @FXML
    private VBox consteContainer;

    private AppController appController;
    private DiagramElement elementToEdit;

    public void setAppController(AppController appController) {
        this.appController = appController;
        populateConnectionOptions();
    }

    public void setElementToEdit(DiagramElement elementToEdit) {
        this.elementToEdit = elementToEdit;
    }

    @FXML
    public void initialize() {
        addConnectionEntry(); // Add at least one entry on initialization
    }

    private void populateConnectionOptions() {
        if (appController != null) {
            consteContainer.getChildren().clear();
            addConnectionEntry(); // Add a default entry
        }
    }

    public void populateFields(DiagramElement element) {
        newName.setText(element.getName());
        newValue.setText(element.getValue());
        newVariables.setText(String.join(", ", element.getVariables()));
        newMethods.setText(String.join(", ", element.getMethods()));
        newRelations.setText(String.join(", ", element.getRelations()));

        consteContainer.getChildren().clear();
        for (ElementConnection connection : element.getConnections()) {
            if (connection != null && connection.getTarget() != null) {
                addConnectionEntry(connection.getTarget().getName(), connection.canMove());
            }
        }
        if (element.getConnections().isEmpty()) {
            addConnectionEntry(); // Ensure at least one entry if none exist
        }
    }

    @FXML
    private void addConnectionEntry() {
        addConnectionEntry(null, false);
    }

    private void addConnectionEntry(String selectedTarget, boolean moveChecked) {
        if (appController == null) return;

        HBox entry = new HBox(10);
        ComboBox<String> connectionSelect = new ComboBox<>(FXCollections.observableArrayList(
                appController.getElements().stream().map(DiagramElement::getName).collect(Collectors.toList())
        ));
        if (selectedTarget != null) {
            connectionSelect.setValue(selectedTarget);
        }
        CheckBox moveCheckbox = new CheckBox("Mover");
        moveCheckbox.setSelected(moveChecked);
        Button removeButton = new Button("×");
        removeButton.setOnAction(event -> consteContainer.getChildren().remove(entry));

        entry.getChildren().addAll(connectionSelect, moveCheckbox, removeButton);
        consteContainer.getChildren().add(entry);
    }

    @FXML
    private void handleSave() {
        String name = newName.getText();
        String value = newValue.getText();
        List<String> variables = Arrays.asList(newVariables.getText().split(",")).stream().map(String::trim).collect(Collectors.toList());
        List<String> methods = Arrays.asList(newMethods.getText().split(",")).stream().map(String::trim).collect(Collectors.toList());
        List<String> relations = Arrays.asList(newRelations.getText().split(",")).stream().map(String::trim).collect(Collectors.toList());

        List<ElementConnectionData> connectionsDataList = new ArrayList<>();
        for (javafx.scene.Node node : consteContainer.getChildren()) {
            if (node instanceof HBox) {
                HBox entry = (HBox) node;
                ComboBox<String> connectionSelect = (ComboBox<String>) entry.getChildren().get(0);
                CheckBox moveCheckbox = (CheckBox) entry.getChildren().get(1);
                if (connectionSelect.getValue() != null && !connectionSelect.getValue().isEmpty()) {
                    connectionsDataList.add(new ElementConnectionData(connectionSelect.getValue(), moveCheckbox.isSelected()));
                }
            }
        }

        if (elementToEdit == null) {
            appController.addElement(name, value, variables, methods, relations, connectionsDataList, 100 + appController.getElements().size() * 150, 100);
        } else {
            appController.updateElement(elementToEdit, name, value, variables, methods, relations, connectionsDataList);
        }
        closeDialog();
    }

    @FXML
    private void handleCancel() {
        closeDialog();
    }

    private void closeDialog() {
        ((Stage) newName.getScene().getWindow()).close();
    }
}