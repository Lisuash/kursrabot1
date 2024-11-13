package com.example.kursrabot;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin {


    @FXML
    private Button reit_ad;

    @FXML
    private Button tour_ad;



    @FXML
    void initialize(){
        reit_ad.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("reiting.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    stage.setTitle("рейтинг");
                    stage.getIcons().add(new Image("file:/C:/Users/feday/IdeaProjects/kursrabot/icon.png"));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        tour_ad.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("new_tournirs.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 400, 400);
                    stage.setTitle("новые турниры");
                    stage.getIcons().add(new Image("file:/C:/Users/feday/IdeaProjects/kursrabot/icon.png"));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });


    }








}
