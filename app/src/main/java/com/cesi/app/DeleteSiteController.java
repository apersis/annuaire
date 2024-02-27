package com.cesi.app;

import com.cesi.app.model.employes.EmployesDTO;
import com.cesi.app.model.services.ServicesDTO;
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

public class DeleteSiteController implements Initializable {
    @FXML
    private TextField id_site;
    @FXML
    private TextField type;
    @FXML
    private TextField ville;


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
        EmployesDTO employesDTO = new EmployesDTO(0, "","","","","",0,0);
        String resultGetAll = call.getAll("employes");
        Collection<EmployesDTO> collecEmployesDTO = employesDTO.DeserializeAll(resultGetAll);
        if (id_site.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner un identifant");
            alert.showAndWait();
        }else if(collecEmployesDTO.stream().anyMatch(objet -> objet.getId_site() == Integer.parseInt(id_site.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Suppression annulée");
            alert.setContentText("Des employés sont encore liés à ce site");
            alert.showAndWait();
        }else{
            String result = call.delete("sites", Integer.parseInt(id_site.getText()));
            if (result.equals("false")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifant valide");
                alert.showAndWait();
            } else {
                Stage actualStage = (Stage) ville.getScene().getWindow();
                actualStage.close();
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

                type.setText(sitesDTO.getType());
                ville.setText(sitesDTO.getVille());
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifiant valide");
                alert.showAndWait();
            }
        }
    }
}
