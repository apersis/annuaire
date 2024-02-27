package com.cesi.app;

import com.cesi.app.model.services.ServicesDTO;
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

public class AdminServicesController implements Initializable {
    @FXML
    private TableView<ServicesDTO> TableServices;
    @FXML
    private TableColumn<ServicesDTO, String> libelle;
    @FXML
    private TableColumn<ServicesDTO, Integer> id_service;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        libelle.setCellValueFactory(new PropertyValueFactory<ServicesDTO, String>("libelle"));
        id_service.setCellValueFactory(new PropertyValueFactory<ServicesDTO, Integer>("id_service"));

        updateTableView();
    }
    public void updateTableView(){
        TableServices.getItems().clear();
        ServicesDTO myService = new ServicesDTO(0,"");
        String result = getAll("services");
        Collection<ServicesDTO> colecServices = null;
        try {
            colecServices = myService.DeserializeAll(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (ServicesDTO service : colecServices) {
            TableServices.getItems().add(service);

        }
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
    protected  void onClickHome(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-home.fxml"));
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
    protected  void onClickQuitter(){
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-service.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update-service.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("delete-service.fxml"));
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
}
