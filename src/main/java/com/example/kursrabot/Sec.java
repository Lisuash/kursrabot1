package com.example.kursrabot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Sec extends ListCell<Secdata> {
    @FXML
    private Label address;

    @FXML
    private AnchorPane idContainer;

    @FXML
    private VBox idStr;

    @FXML
    private Label name;

    @FXML
    private Label price;
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Secdata student, boolean empty) {
        super.updateItem(student, empty);
        if (empty || student == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("sectiondata.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            name.setText(student.getName());
            address.setText(student.getAddress());
            price.setText(student.getPrice());

            setText(null);
            setGraphic(idContainer);
        }
    }



}