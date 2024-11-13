package com.example.kursrabot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Reiting {

    @FXML
    private ListView<String> spis;


    @FXML
    private Button create_tour;


    @FXML
    private ComboBox<String> sportbox;
    DB db = null;



    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        db = new DB();
        sportbox.getItems().addAll(db.getTour());
        // Добавляем обработчик событий к sportbox
        sportbox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Если новый выбор не равен null, запрашиваем данные из базы данных
            if (newSelection != null) {
                // Запрос данных из базы данных на основе выбора в sportbox
                ObservableList<String> data = null;
                try {
                    data = db.getData(newSelection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                // Заполняем ListView этими данными
                spis.setItems(data);
            }
        });


        create_tour.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createreit.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 300, 200);
                    stage.setTitle("создать турнир");
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






