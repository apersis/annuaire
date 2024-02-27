package com.cesi.app;

import com.cesi.app.model.employes.EmployesDTO;
import com.cesi.app.model.employes.NewEmployes;
import com.cesi.app.model.services.ServicesDTO;
import com.cesi.app.model.sites.NewSites;
import com.cesi.app.model.sites.SitesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class UpdateSiteController implements Initializable {
    @FXML
    private TextField id_site;
    @FXML
    private TextField ville;
    @FXML
    private TextField type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    protected void onClickAnnuler(){
        Stage actualStage = (Stage) ville.getScene().getWindow();
        actualStage.close();
    }
    @FXML
    protected void onClickEnvoyer() throws JsonProcessingException {
        if(ville.getText().isEmpty() || id_site.getText().isEmpty() || type.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner toutes les cases");
            alert.showAndWait();
        }else {
            NewSites sites = new NewSites();
            sites.setType(type.getText());
            sites.setVille(ville.getText());

            String body = sites.Serialize();

            String result = call.update("sites", Integer.parseInt(id_site.getText()), body);

            if(!result.equals("")) {

                Stage actualStage = (Stage) ville.getScene().getWindow();
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
        if(id_site.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner un identifiant");
            alert.showAndWait();
        }else {
            SitesDTO sitesDTO = new SitesDTO(0, "", "");
            String body = call.get("sites", Integer.parseInt(id_site.getText()));
            if (!body.equals("")) {
                try {
                    sitesDTO = sitesDTO.Deserialize(body);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                ville.setText(sitesDTO.getVille());
                type.setText(sitesDTO.getType());
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifiant valide");
                alert.showAndWait();
            }
        }
    }
}
