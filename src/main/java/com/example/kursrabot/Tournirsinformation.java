package com.example.kursrabot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Tournirsinformation {
    @FXML
    private Label inftour;

    public void setInftourValue(String value) {
        inftour.setText(value);
    }

}
