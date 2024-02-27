package com.cesi.app.model.services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;

public class ServicesDTO {
    SimpleIntegerProperty id_service;
    SimpleStringProperty libelle;

    public ServicesDTO(@JsonProperty("id_service") int id_service,
                       @JsonProperty("libelle") String libelle) {
        this.id_service = new SimpleIntegerProperty(id_service);
        this.libelle = new SimpleStringProperty(libelle);
    }

    @JsonCreator
    public int getId_service() {
        return id_service.get();
    }
    public void setId_service(int id_service) {
        this.id_service.set(id_service);
    }
    public String getLibelle() {
        return libelle.get();
    }
    public void setLibelle(String libelle) {
        this.libelle.set(libelle);
    }
    public SimpleIntegerProperty id_serviceProperty() {
        return id_service;
    }
    public SimpleStringProperty libelleProperty() {
        return libelle;
    }


    public ServicesDTO Deserialize(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ServicesDTO item = objectMapper.readValue(json, ServicesDTO.class);
        return item;
    }

    public Collection<ServicesDTO> DeserializeAll(String json) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Collection<ServicesDTO> collectionItems = objectMapper.readValue(json, new TypeReference<Collection<ServicesDTO>>(){});
        return collectionItems;
    }
}
