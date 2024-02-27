package com.cesi.demo.buisness.services;

import com.cesi.demo.controller.services.model.NewServices;
import com.cesi.demo.controller.services.model.Services;
import com.cesi.demo.dao.services.ServicesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesBusiness {
    private final ServicesDAO servicesDAO;

    @Autowired
    public ServicesBusiness(ServicesDAO servicesDAO){
        this.servicesDAO = servicesDAO;
    }
    public Services createServicesBusiness(NewServices services){
        return servicesDAO.create(services);
    }
    public boolean deleteServicesBusiness(int serviceId) {
        return servicesDAO.delete(serviceId);
    }
    public Services updateServicesBusiness(int serviceId,NewServices services){
        return servicesDAO.update(serviceId,services);
    }
    public Services getServicesBusiness(int serviceId){
        return servicesDAO.read(serviceId);
    }
    public List<Services> getAllServicesBusiness(){
        return servicesDAO.read();
    }

}
