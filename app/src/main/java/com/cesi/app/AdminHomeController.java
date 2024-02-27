package com.cesi.app;

import com.cesi.app.model.employes.DTOtoEmployes;
import com.cesi.app.model.employes.Employes;
import com.cesi.app.model.employes.EmployesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import static com.cesi.app.call.getAll;
import static com.cesi.app.model.employes.DTOtoEmployes.CollecDTO2CollecEmployes;
import static com.cesi.app.model.employes.DTOtoEmployes.DTO2Employes;

public class AdminHomeController implements Initializable {

    @FXML
    private TableView<Employes> TableSalarie;
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

        updateTableView();
    }
    @FXML
    protected void onClickSites(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-sites.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1280, 720);
            Stage stage = HelloApplication.getMainStage();
            stage.setResizable(false);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected  void onClickServices(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-services.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1280, 720);
            Stage stage = HelloApplication.getMainStage();
            stage.setResizable(false);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onClickQuitter(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1280, 720);
            Stage stage = HelloApplication.getMainStage();
            stage.setResizable(false);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onClickCreate(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-salarie.fxml"));
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(loader.load()));
            stage2.setTitle("Ajouter");
            stage2.setResizable(false);
            stage2.showAndWait();
            updateTableView();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onClickUpdate(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update-salarie.fxml"));
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(loader.load()));
            stage2.setTitle("Modifier");
            stage2.setResizable(false);
            stage2.showAndWait();
            updateTableView();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onClickDelete(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("delete-salarie.fxml"));
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(loader.load()));
            stage2.setTitle("Supprimer");
            stage2.setResizable(false);
            stage2.showAndWait();
            updateTableView();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void updateTableView(){
        TableSalarie.getItems().clear();
        EmployesDTO myEmploye = new EmployesDTO(0,"","","","","",0,0);
        String result = getAll("employes");
        Collection<EmployesDTO> collecEmployesDTO = null;
        try {
            collecEmployesDTO = myEmploye.DeserializeAll(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Collection<Employes> collecEmployes = CollecDTO2CollecEmployes(collecEmployesDTO);
        for (Employes employe : collecEmployes) {
            TableSalarie.getItems().add(employe);
        }
    }
}

