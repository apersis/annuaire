package com.cesi.app;

import com.cesi.app.model.sites.SitesDTO;
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

public class AdminSitesController implements Initializable {
    @FXML
    private TableView<SitesDTO> TableSites;
    @FXML
    private TableColumn<SitesDTO, String> ville;
    @FXML
    private TableColumn<SitesDTO, Integer> id_site;
    @FXML
    private TableColumn<SitesDTO, String> type;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ville.setCellValueFactory(new PropertyValueFactory<SitesDTO, String>("ville"));
        id_site.setCellValueFactory(new PropertyValueFactory<SitesDTO, Integer>("id_site"));
        type.setCellValueFactory(new PropertyValueFactory<SitesDTO, String>("type"));

        updateTableView();

    }
    public void updateTableView(){
        TableSites.getItems().clear();
        SitesDTO mySite = new SitesDTO(0,"","");
        String result = getAll("sites");
        Collection<SitesDTO> colecSites = null;
        try {
            colecSites = mySite.DeserializeAll(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (SitesDTO site : colecSites) {
            TableSites.getItems().add(site);

        }
    }

    @FXML
    protected void onClickHome(){
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-site.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update-site.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("delete-site.fxml"));
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
