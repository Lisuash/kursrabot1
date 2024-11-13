package com.example.kursrabot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.sql.SQLException;

public class Pretendent {
    @FXML
    private ListView<String> spis;
    @FXML
    private ComboBox<String> combo;
    DB db = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        db = new DB();
        combo.getItems().addAll(db.getAllTourDesks());
        combo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                spis.getItems().clear();
                loadinfo();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        loadinfo();
    }


    void loadinfo() throws SQLException, ClassNotFoundException {
        spis.getItems().addAll(db.getUsersByTourDesk(combo.getValue()));

    }
}
