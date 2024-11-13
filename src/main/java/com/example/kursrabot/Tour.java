package com.example.kursrabot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Tour extends ListCell<Tourdata> {


    @FXML
    private AnchorPane cont;

    @FXML
    private Label data;

    @FXML
    private Label ochki;

    @FXML
    private VBox str;




    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Tourdata student, boolean empty) {
        super.updateItem(student, empty);
        if (empty || student == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("tournirsdata.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            data.setText(student.getData());
            ochki.setText(student.getOchki());


            setText(null);
            setGraphic(cont);
        }
    }




}
