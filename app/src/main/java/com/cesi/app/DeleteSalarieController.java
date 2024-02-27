package com.cesi.app;

import com.cesi.app.model.employes.EmployesDTO;
import com.cesi.app.model.employes.NewEmployes;
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

public class DeleteSalarieController implements Initializable {
    @FXML
    private ComboBox combo_site;
    @FXML
    private ComboBox combo_service;
    @FXML
    private TextField id_salarie;
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
    private Dictionary<Integer, String> dicoInverseSites = new Hashtable();
    private Dictionary<Integer, String> dicoInverseServices = new Hashtable();

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
            dicoInverseSites.put(sitesDTO.getId_site(),sitesDTO.getVille());
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
            dicoInverseServices.put(servicesDTO.getId_service(), servicesDTO.getLibelle());
        }
    }
    @FXML
    protected void onClickAnnuler(){
        Stage actualStage = (Stage) combo_service.getScene().getWindow();
        actualStage.close();
    }
    @FXML
    protected void onClickEnvoyer() throws JsonProcessingException {

        if (id_salarie.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner un identifant");
            alert.showAndWait();
        }
        else {
            String boolresult = call.delete("employes", Integer.parseInt(id_salarie.getText()));

            if(boolresult.equals("false")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifant valide");
                alert.showAndWait();
            }
            else{
                Stage actualStage = (Stage) combo_service.getScene().getWindow();
                actualStage.close();
            }


        }
    }
    @FXML
    protected void onClickRechercher(){
        if (id_salarie.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez renseigner un identifiant");
            alert.showAndWait();
        }
        else {
            EmployesDTO employeDTO = new EmployesDTO(0, "", "", "", "", "", 0, 0);
            String body = call.get("employes", Integer.parseInt(id_salarie.getText()));
            if (!body.equals("")) {
                try {
                    employeDTO = employeDTO.Deserialize(body);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                nom.setText(employeDTO.getNom());
                prenom.setText(employeDTO.getPrenom());
                tel_fixe.setText(employeDTO.getTel_fixe());
                tel_portable.setText(employeDTO.getTel_portable());
                email.setText(employeDTO.getEmail());
                combo_service.setValue(dicoInverseServices.get(employeDTO.getId_service()));
                combo_site.setValue(dicoInverseSites.get(employeDTO.getId_site()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez renseigner un identifiant valide");
                alert.showAndWait();
            }
        }

    }
}
