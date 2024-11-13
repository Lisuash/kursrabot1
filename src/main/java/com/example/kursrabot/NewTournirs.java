package com.example.kursrabot;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.List;

public class NewTournirs {

    @FXML
    private ListView<String> all;

    @FXML
    private ComboBox<String> new_combo;

    @FXML
    private ListView<String> tour;

    @FXML
    private Button savebut;

    @FXML
    private TextField textdesk;

    DB db = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        savebut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    db.Updatedeskfortour(textdesk.getText(),new_combo.getValue());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println(textdesk.getText());
            }
        });

        db = new DB();
        new_combo.getItems().addAll(db.getnewTour());

        // Добавьте этот код в метод initialize()
        new_combo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                tour.getItems().clear();
                loadtour();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        loaduch();


    }

    void loaduch() throws SQLException, ClassNotFoundException {
        List<String> items = db.getNewUcastniki();
        all.getItems().addAll(items);
        all.setCellFactory(param -> {
            ListCell<String> cell = new ListCell<>();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("добавить в турнир");
            editItem.setOnAction(event -> {
                String selectedItem = cell.getItem();
                try {
                    db.insertnewTour_us(db.getidbynewtour(new_combo.getValue()), db.getidbyuser(selectedItem));
                    loadtour();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            contextMenu.getItems().addAll(editItem);
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            cell.itemProperty().addListener((obs, oldItem, newItem) -> {
                cell.setText(newItem);
            });
            return cell;
        });
    }


    void loadtour() throws SQLException, ClassNotFoundException {
        List<String> items = db.getAddedUcastniki(db.getidbynewtour(new_combo.getValue()));
        tour.getItems().addAll(items);
        tour.setCellFactory(param -> {
            ListCell<String> cell = new ListCell<>();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("удалить из турнира");
            editItem.setOnAction(event -> {
                String selectedItem = cell.getItem();
                try {
                    db.deleteFromTour(db.getidbyuser(selectedItem));
                    tour.getItems().remove(selectedItem);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            contextMenu.getItems().addAll(editItem);
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            cell.itemProperty().addListener((obs, oldItem, newItem) -> {
                cell.setText(newItem);
            });
            return cell;
        });
    }

}
