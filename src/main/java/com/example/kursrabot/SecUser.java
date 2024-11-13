package com.example.kursrabot;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class SecUser {
    @FXML
    private ListView<String> spis;

    public void setUserValue(ArrayList<String> value) {
        spis.getItems().addAll(value);
    }



}
