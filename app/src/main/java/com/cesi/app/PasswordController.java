package com.cesi.app;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class PasswordController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize(){
    }

    @FXML
    protected void onButtonAnnulerClick(){
        Stage actualStage = (Stage) passwordField.getScene().getWindow();
        actualStage.close();
    }

    @FXML
    protected void onButtonConfirmerClick(){
        if ("admin".equals(passwordField.getText())) {
            // Mot de passe correct, affichage d'une autre alerte
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Mot de passe correct !");
            successAlert.showAndWait();
            try {
                Stage actualStage = (Stage) passwordField.getScene().getWindow();
                actualStage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-home.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1280, 720);
                Stage stage = HelloApplication.getMainStage();
                stage.setResizable(false);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("Échec");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Mot de passe incorrect !");
            successAlert.showAndWait();
            passwordField.setText("");
        }
    }

}
