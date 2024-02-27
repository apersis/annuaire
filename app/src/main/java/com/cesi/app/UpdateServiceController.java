package com.cesi.app;

import com.cesi.app.model.services.NewServices;
import com.cesi.app.model.services.ServicesDTO;
import com.cesi.app.model.sites.NewSites;
import com.cesi.app.model.sites.SitesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateServiceController {
    @FXML
    private TextField id_service;
    @FXML
    private TextField libelle;

    @FXML
    protected void onClickAnnuler(){
        Stage actualStage = (Stage) libelle.getScene().getWindow();
        actualStage.close();
    }
    @FXML
    protected void onClickEnvoyer() throws JsonProcessingException {
        if(id_service.getText().isEmpty() || libelle.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner toutes les cases");
            alert.showAndWait();
        }else {
            NewServices services = new NewServices();
            services.setLibelle(libelle.getText());

            String body = services.Serialize();

            String result = call.update("services", Integer.parseInt(id_service.getText()), body);

            if(!result.equals("")) {
                Stage actualStage = (Stage) libelle.getScene().getWindow();
                actualStage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifiant valide");
                alert.showAndWait();
            }
        }
    }
    @FXML
    protected void onClickRechercher(){
        if(id_service.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner un identifiant");
            alert.showAndWait();
        }
        else{
            ServicesDTO servicesDTO = new ServicesDTO(0,"");
            String body = call.get("services",Integer.parseInt(id_service.getText()));
            if (!body.equals("")){
                try {
                    servicesDTO = servicesDTO.Deserialize(body);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                libelle.setText(servicesDTO.getLibelle());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifiant valide");
                alert.showAndWait();
            }

        }

    }
}
