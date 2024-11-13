package com.example.kursrabot;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class Usertour {
    @FXML
    private ListView<String> iduser;


    public void setUserValue(ArrayList<String> value) {
        iduser.getItems().addAll(value);
    }







}
