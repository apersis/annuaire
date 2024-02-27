package com.cesi.app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class HelloApplication extends Application {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }
    public static Stage getMainStage() {
        return mainStage;
    }


    @Override
    public void start(Stage stage) throws IOException {
        //Pane root = new Pane();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        setMainStage(stage);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Annuaire");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}