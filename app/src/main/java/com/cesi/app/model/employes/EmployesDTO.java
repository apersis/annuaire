package com.cesi.app.model.employes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;



public class EmployesDTO {
    private final SimpleIntegerProperty id_salarie;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty tel_fixe;
    private final SimpleStringProperty tel_portable;
    private final SimpleStringProperty email;
    private final SimpleIntegerProperty id_site;
    private final SimpleIntegerProperty id_service;

    public EmployesDTO(@JsonProperty("id_salarie") int id_salarie,
                       @JsonProperty("nom") String nom,
                       @JsonProperty("prenom") String prenom,
                       @JsonProperty("tel_fixe") String tel_fixe,
                       @JsonProperty("tel_portable") String tel_portable,
                       @JsonProperty("email") String email,
                       @JsonProperty("id_site") int id_site,
                       @JsonProperty("id_service") int id_service) {
        this.id_salarie = new SimpleIntegerProperty(id_salarie);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.tel_fixe = new SimpleStringProperty(tel_fixe);
        this.tel_portable = new SimpleStringProperty(tel_portable);
        this.email = new SimpleStringProperty(email);
        this.id_site = new SimpleIntegerProperty(id_site);
        this.id_service = new SimpleIntegerProperty(id_service);
    }

    @JsonCreator
    public int getId_salarie() {
        return id_salarie.get();
    }

    public SimpleIntegerProperty id_salarieProperty() {
        return id_salarie;
    }

    public void setId_salarie(int id_salarie) {
        this.id_salarie.set(id_salarie);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getTel_fixe() {
        return tel_fixe.get();
    }

    public SimpleStringProperty tel_fixeProperty() {
        return tel_fixe;
    }

    public void setTel_fixe(String tel_fixe) {
        this.tel_fixe.set(tel_fixe);
    }

    public String getTel_portable() {
        return tel_portable.get();
    }

    public SimpleStringProperty tel_portableProperty() {
        return tel_portable;
    }

    public void setTel_portable(String tel_portable) {
        this.tel_portable.set(tel_portable);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getId_site() {
        return id_site.get();
    }

    public SimpleIntegerProperty id_siteProperty() {
        return id_site;
    }

    public void setId_site(int id_site) {
        this.id_site.set(id_site);
    }

    public int getId_service() {
        return id_service.get();
    }

    public SimpleIntegerProperty id_serviceProperty() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service.set(id_service);
    }




    public EmployesDTO Deserialize(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmployesDTO item = objectMapper.readValue(json, EmployesDTO.class);
        return item;
    }

    public Collection<EmployesDTO> DeserializeAll(String json) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Collection<EmployesDTO> collectionItems = objectMapper.readValue(json, new TypeReference<Collection<EmployesDTO>>(){});
        return collectionItems;
    }
}
