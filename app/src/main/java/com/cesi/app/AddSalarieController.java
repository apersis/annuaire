package com.cesi.app;


import com.cesi.app.model.employes.NewEmployes;
import com.cesi.app.model.services.NewServices;
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

public class AddSalarieController implements Initializable {
    @FXML
    private ComboBox combo_site;
    @FXML
    private ComboBox combo_service;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField tel_portable;
    @FXML
    private TextField tel_fixe;
    private Dictionary<String, Integer> dicoSites = new Hashtable();
    private Dictionary<String, Integer> dicoServices = new Hashtable();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String resultSites = call.getAll("sites");
        SitesDTO mySitesDTO = new SitesDTO(0,"","");
        Collection<SitesDTO> collecSites = new ArrayList<>();
        try {
            collecSites = mySitesDTO.DeserializeAll(resultSites);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (SitesDTO sitesDTO : collecSites){
            combo_site.getItems().add(sitesDTO.getVille());
            dicoSites.put(sitesDTO.getVille(),sitesDTO.getId_site());
        }

        String resultService = call.getAll("services");
        ServicesDTO myServiceDTO = new ServicesDTO(0,"");
        Collection<ServicesDTO> collecServices = new ArrayList<>();
        try {
            collecServices = myServiceDTO.DeserializeAll(resultService);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (ServicesDTO servicesDTO : collecServices){
            combo_service.getItems().add(servicesDTO.getLibelle());
            dicoServices.put(servicesDTO.getLibelle(),servicesDTO.getId_service());
        }
    }
    @FXML
    protected void onClickAnnuler(){
        Stage actualStage = (Stage) combo_service.getScene().getWindow();
        actualStage.close();
    }
    @FXML
    protected void onClickEnvoyer() throws JsonProcessingException {

        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || tel_fixe.getText().isEmpty() || tel_portable.getText().isEmpty() ||
                combo_site.getValue().equals("Site") || combo_service.getValue().equals("Service") ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner toutes les cases");
            alert.showAndWait();
        }
        else{
            NewEmployes employes = new NewEmployes();
            employes.setNom(nom.getText());
            employes.setPrenom(prenom.getText());
            employes.setEmail(email.getText());
            employes.setTel_fixe(tel_fixe.getText());
            employes.setTel_portable(tel_portable.getText());
            employes.setId_service(dicoServices.get(combo_service.getValue()));
            employes.setId_site(dicoSites.get(combo_site.getValue()));

            String body = employes.Serialize();

            call.create("employes",body);

            Stage actualStage = (Stage) combo_service.getScene().getWindow();
            actualStage.close();
        }


    }
}
