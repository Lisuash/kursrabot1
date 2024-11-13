package com.example.kursrabot;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private TextField fiofield;

    @FXML
    private TextField kodfield;

    @FXML
    private Button vhodbut;


    DB db = null;


    @FXML
    void initialize() {

        // Инициируем объект
        db = new DB();

        // Обработчик события. Сработает при нажатии на кнопку
        vhodbut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Проверяем является ли поле заполненным
                    if (!fiofield.getText().trim().equals("") & !kodfield.getText().trim().equals("")) {
                        int a = db.getUser(fiofield.getText(), kodfield.getText());
                        if (a == 1) {
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("usercab.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                            stage.setTitle("личный кабинет");
                            stage.getIcons().add(new Image("file:/C:/Users/feday/IdeaProjects/kursrabot/icon.png"));
                            stage.setScene(scene);
                            stage.show();


                        }
                        if (a == 2) {
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                            stage.setTitle("поле администратора");
                            stage.getIcons().add(new Image("file:/C:/Users/feday/IdeaProjects/kursrabot/icon.png"));
                            stage.setScene(scene);
                            stage.show();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}