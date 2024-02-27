package com.cesi.app.model.sites;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;

public class SitesDTO {
    SimpleIntegerProperty id_site;
    SimpleStringProperty type;
    SimpleStringProperty ville;

    public SitesDTO(@JsonProperty("id_site") int id_site,
                    @JsonProperty("type") String type,
                    @JsonProperty("ville") String ville) {
        this.id_site = new SimpleIntegerProperty(id_site);
        this.type = new SimpleStringProperty(type);
        this.ville = new SimpleStringProperty(ville);
    }


    @JsonCreator
    public int getId_site() {
        return id_site.get();
    }

    public SimpleIntegerProperty id_siteProperty() {
        return id_site;
    }

    public void setId_site(int id_site) {
        this.id_site.set(id_site);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getVille() {
        return ville.get();
    }

    public SimpleStringProperty villeProperty() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }


    public SitesDTO Deserialize(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SitesDTO item = objectMapper.readValue(json, SitesDTO.class);
        return item;
    }

    public Collection<SitesDTO> DeserializeAll(String json) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Collection<SitesDTO> collectionItems = objectMapper.readValue(json, new TypeReference<Collection<SitesDTO>>(){});
        return collectionItems;
    }
}
