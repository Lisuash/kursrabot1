package com.example.kursrabot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Tournirs {

    @FXML
    private ListView<Tourdata> idspistour;
    private Tournirsinformation tournirsinformationController;
    private Usertour usertourContr;


    DB db = null;

    @FXML
    void initialize(){
        db = new DB();
        loadtour();
    }



    void loadtour() {
        try {
            List<Tourdata> ls = db.getTournirs();
            idspistour.getItems().addAll(ls);
            idspistour.setCellFactory(stringListView -> {
                ListCell<Tourdata> cell = new Tour();
                ContextMenu contextMenu = new ContextMenu();
                MenuItem editItem = new MenuItem("информация о турнире");
                MenuItem user = new MenuItem("участники турнира");
                editItem.setOnAction(event -> {
                    Tourdata selectedProduct = cell.getItem();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tournirsinformation.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(fxmlLoader.load(), 300, 100);
                        stage.setTitle("информация о турнире");
                        stage.getIcons().add(new Image("file:/C:/Users/feday/IdeaProjects/kursrabot/icon.png"));
                        stage.setScene(scene);
                        stage.show();
                        tournirsinformationController = fxmlLoader.getController();
                        tournirsinformationController.setInftourValue(db.getDeskOfTour(selectedProduct.getId()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                });
                user.setOnAction(event -> {
                    Tourdata selectedProduct = cell.getItem();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("usertour.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
                        stage.setTitle("участники турнира");
                        stage.getIcons().add(new Image("file:/C:/Users/feday/IdeaProjects/kursrabot/icon.png"));
                        stage.setScene(scene);
                        stage.show();
                        usertourContr = fxmlLoader.getController();
                        usertourContr.setUserValue(db.getRaiting(selectedProduct.getId()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

                contextMenu.getItems().addAll(editItem, user);
                cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                    if (isNowEmpty) {
                        cell.setContextMenu(null);
                    } else {
                        cell.setContextMenu(contextMenu);
                    }
                });
                return cell;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}













