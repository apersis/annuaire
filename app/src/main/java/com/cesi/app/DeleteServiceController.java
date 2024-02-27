package com.cesi.app;

import com.cesi.app.model.employes.EmployesDTO;
import com.cesi.app.model.services.ServicesDTO;
import com.cesi.app.model.sites.SitesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class DeleteServiceController {
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
        EmployesDTO employesDTO = new EmployesDTO(0, "","","","","",0,0);
        String resultGetAll = call.getAll("employes");
        Collection<EmployesDTO> collecEmployesDTO = employesDTO.DeserializeAll(resultGetAll);
        if (id_service.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner un identifant");
            alert.showAndWait();
        }else if(collecEmployesDTO.stream().anyMatch(objet -> objet.getId_service() == Integer.parseInt(id_service.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Suppression annulée");
            alert.setContentText("Des employés sont encore liés à ce service");
            alert.showAndWait();
        }else {
            String result = call.delete("services", Integer.parseInt(id_service.getText()));

            if(result.equals("false")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifant valide");
                alert.showAndWait();
            }else{
                Stage actualStage = (Stage) libelle.getScene().getWindow();
                actualStage.close();
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
        }else {
            ServicesDTO servicesDTO = new ServicesDTO(0, "");
            String body = call.get("services", Integer.parseInt(id_service.getText()));
            if(!body.equals("")) {
                try {
                    servicesDTO = servicesDTO.Deserialize(body);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                libelle.setText(servicesDTO.getLibelle());
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifiant valide");
                alert.showAndWait();
            }
        }
    }
}
