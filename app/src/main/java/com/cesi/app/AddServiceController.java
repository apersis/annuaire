package com.cesi.app;

import com.cesi.app.model.services.NewServices;
import com.cesi.app.model.sites.NewSites;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddServiceController {
    @FXML
    private TextField libelle;

    @FXML
    protected void onClickAnnuler(){
        Stage actualStage = (Stage) libelle.getScene().getWindow();
        actualStage.close();
    }
    @FXML
    protected void onClickEnvoyer() throws JsonProcessingException {
        if(libelle.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner toutes les cases");
            alert.showAndWait();
        }
        else {
            NewServices services = new NewServices();
            services.setLibelle(libelle.getText());

            String body = services.Serialize();

            call.create("services", body);

            Stage actualStage = (Stage) libelle.getScene().getWindow();
            actualStage.close();
        }
    }
}
