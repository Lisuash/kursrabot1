package com.example.kursrabot;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Sekcii {
    @FXML
    private ListView<Secdata> idspis;
    private SecUser SecUserContr;

    DB db = null;



    @FXML
    void initialize(){
        db = new DB();
        loadInfo();
    }

    void loadInfo() {
        try {
            List<Secdata> ls = db.getSection();
            idspis.getItems().addAll(ls);
            idspis.setCellFactory(param -> {
                Sec cell = new Sec();
                ContextMenu contextMenu = new ContextMenu();
                MenuItem editItem = new MenuItem("участники секции");
                editItem.setOnAction(event -> {
                    Secdata selectedProduct = cell.getItem();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("secuser.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
                        stage.setTitle("участники секций");
                        stage.getIcons().add(new Image("file:/C:/Users/feday/IdeaProjects/kursrabot/icon.png"));
                        stage.setScene(scene);
                        stage.show();
                        SecUserContr = fxmlLoader.getController();
                        SecUserContr.setUserValue(db.getSectionUsers(db.getidsekcii(selectedProduct.getName(), Integer.parseInt(selectedProduct.getPrice()))));
                    }catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                });
                contextMenu.getItems().addAll(editItem);
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













