package com.cesi.app.model.employes;

import com.cesi.app.call;
import com.cesi.app.model.services.ServicesDTO;
import com.cesi.app.model.sites.SitesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Collection;

public class DTOtoEmployes {
    public static Employes DTO2Employes(EmployesDTO employesDTO){

        String getService = call.get("services",employesDTO.getId_service());
        ServicesDTO myServicesDTO = new ServicesDTO(0,"");
        ServicesDTO servicesDTO = null;
        try {
            servicesDTO = myServicesDTO.Deserialize(getService);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String getSite = call.get("sites", employesDTO.getId_site());
        SitesDTO mySitesDTO = new SitesDTO(0,"","");
        SitesDTO sitesDTO = null;
        try {
            sitesDTO = mySitesDTO.Deserialize(getSite);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Employes employes = new Employes();
        employes.setId_salarie(employesDTO.getId_salarie());
        employes.setNom(employesDTO.getNom());
        employes.setPrenom(employesDTO.getPrenom());
        employes.setTel_portable(employesDTO.getTel_portable());
        employes.setTel_fixe(employesDTO.getTel_fixe());
        employes.setEmail(employesDTO.getEmail());
        employes.setId_service(servicesDTO.getId_service());
        employes.setLibelle_service(servicesDTO.getLibelle());
        employes.setId_site(sitesDTO.getId_site());
        employes.setType_site(sitesDTO.getType());
        employes.setVille_site(sitesDTO.getVille());

        return employes;
    }

    public static Collection<Employes> CollecDTO2CollecEmployes(Collection<EmployesDTO> collecDTO){
        Collection<Employes> collecEmployes = new ArrayList<>();
        for(EmployesDTO employesDTO : collecDTO){
            collecEmployes.add(DTO2Employes(employesDTO));
        }
        return collecEmployes;
    }
}
