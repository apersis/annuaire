package com.cesi.app.model.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NewServices {
    String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String Serialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(this);
        return string;
    }

}
