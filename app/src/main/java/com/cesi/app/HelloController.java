package com.cesi.app;

import com.cesi.app.model.services.ServicesDTO;
import com.cesi.app.model.sites.SitesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.cesi.app.model.employes.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static com.cesi.app.call.getAll;
import static com.cesi.app.model.employes.DTOtoEmployes.CollecDTO2CollecEmployes;
import static com.cesi.app.model.employes.DTOtoEmployes.DTO2Employes;

public class HelloController implements Initializable {

    @FXML
    private TextField sort_by_id;
    @FXML
    private TextField sort_by_nom;
    @FXML
    private TextField sort_by_prenom;
    @FXML
    private Button envoyer;
    @FXML
    private TableView<Employes> TableAnnuaire;
    @FXML
    private TableColumn<Employes, Integer> id_salarie;
    @FXML
    private TableColumn<Employes, String> nom;
    @FXML
    private TableColumn<Employes, String> prenom;
    @FXML
    private TableColumn<Employes, String> tel_fixe;
    @FXML
    private TableColumn<Employes, String> tel_portable;
    @FXML
    private TableColumn<Employes, String> email;
    @FXML
    private TableColumn<Employes, String> libelle_service;
    @FXML
    private TableColumn<Employes, String> ville_site;
    @FXML
    private TableColumn<Employes, String> type_site;
    @FXML
    private ComboBox combo_site;
    @FXML
    private ComboBox combo_service;
    private Dictionary<String, Integer> dicoSites = new Hashtable();
    private Dictionary<String, Integer> dicoServices = new Hashtable();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_salarie.setCellValueFactory(new PropertyValueFactory<Employes, Integer>("id_salarie"));
        nom.setCellValueFactory(new PropertyValueFactory<Employes, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Employes, String>("prenom"));
        tel_fixe.setCellValueFactory(new PropertyValueFactory<Employes, String>("tel_fixe"));
        tel_portable.setCellValueFactory(new PropertyValueFactory<Employes, String>("tel_portable"));
        email.setCellValueFactory(new PropertyValueFactory<Employes, String>("email"));
        libelle_service.setCellValueFactory(new PropertyValueFactory<Employes, String>("libelle_service"));
        ville_site.setCellValueFactory(new PropertyValueFactory<Employes, String>("ville_site"));
        type_site.setCellValueFactory(new PropertyValueFactory<Employes, String>("type_site"));

        String resultSites = call.getAll("sites");
        SitesDTO mySitesDTO = new SitesDTO(0,"","");
        Collection<SitesDTO> collecSites = new ArrayList<>();
        try {
            collecSites = mySitesDTO.DeserializeAll(resultSites);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        combo_site.getItems().add("Site");
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
        combo_service.getItems().add("Service");
        for (ServicesDTO servicesDTO : collecServices){
            combo_service.getItems().add(servicesDTO.getLibelle());
            dicoServices.put(servicesDTO.getLibelle(),servicesDTO.getId_service());
        }


        EmployesDTO myEmploye = new EmployesDTO(0,"","","","","",0,0);
        String result = getAll("employes");
        Collection<EmployesDTO> colecEmployesDTO = null;
        try {
            colecEmployesDTO = myEmploye.DeserializeAll(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Collection<Employes> colecEmployes = CollecDTO2CollecEmployes(colecEmployesDTO);
        for (Employes employe: colecEmployes) {
            TableAnnuaire.getItems().add(employe);
        }

    }
    @FXML
    protected void onButtonEnvoyerClick() {
        TableAnnuaire.getItems().clear();
        EmployesDTO myEmploye = new EmployesDTO(0,"","","","","",0,0);
        String result = getAll("employes");
        Collection<EmployesDTO> colecEmployesDTO = null;
        try {
            colecEmployesDTO = myEmploye.DeserializeAll(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Collection<Employes> colecEmployes = CollecDTO2CollecEmployes(colecEmployesDTO);

        if (!sort_by_id.getText().isEmpty()){
            colecEmployes.removeIf(e -> e.getId_salarie()!=Integer.parseInt(sort_by_id.getText()));
        }
        if (!sort_by_nom.getText().isEmpty()){
            colecEmployes.removeIf(e -> !e.getNom().toLowerCase().contains(sort_by_nom.getText().toLowerCase()));
        }
        if(!sort_by_prenom.getText().isEmpty()){
            colecEmployes.removeIf(e -> !e.getPrenom().toLowerCase().contains(sort_by_prenom.getText().toLowerCase()));
        }
        if(!combo_site.getValue().equals("Site")){
            colecEmployes.removeIf(e -> !e.getVille_site().equals(combo_site.getValue()));
        }
        if(!combo_service.getValue().equals("Service")){
            colecEmployes.removeIf(e -> !e.getLibelle_service().equals(combo_service.getValue()));
        }
        for (Employes employe: colecEmployes) {
            TableAnnuaire.getItems().add(employe);
        }
    }
    @FXML
    void keyPressed(KeyEvent event) {
        if (event.isControlDown() && event.isAltDown() && event.getCode() == KeyCode.A) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("password.fxml"));
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(loader.load()));
                stage2.setTitle("Administration");
                stage2.setResizable(false);
                stage2.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}