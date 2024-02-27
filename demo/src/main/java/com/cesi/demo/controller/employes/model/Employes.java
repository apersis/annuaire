package com.cesi.demo.controller.employes.model;

public class Employes {
    int id_salarie;
    String nom;
    String prenom;
    String tel_fixe;
    String tel_portable;
    String email;
    int id_site;
    int id_service;

    public int getId_salarie() {
        return id_salarie;
    }

    public void setId_salarie(int id_salarie) {
        this.id_salarie = id_salarie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel_fixe() {
        return tel_fixe;
    }

    public void setTel_fixe(String tel_fixe) {
        this.tel_fixe = tel_fixe;
    }

    public String getTel_portable() {
        return tel_portable;
    }

    public void setTel_portable(String tel_portable) {
        this.tel_portable = tel_portable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_site() {
        return id_site;
    }

    public void setId_site(int id_site) {
        this.id_site = id_site;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }
}
