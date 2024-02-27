package com.cesi.demo.controller.services;


import com.cesi.demo.buisness.services.ServicesBusiness;
import com.cesi.demo.controller.services.model.Services;
import com.cesi.demo.controller.services.model.NewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ServicesController {
    private final String versionAPI = "/api/v1/";

    private final ServicesBusiness servicesBusiness;

    @Autowired
    public ServicesController(ServicesBusiness servicesBusiness) {
        this.servicesBusiness = servicesBusiness;
    }

    @PostMapping(versionAPI + "services")
    public Services createServices(@RequestBody NewServices services){
        return servicesBusiness.createServicesBusiness(services);
    }
    @DeleteMapping(versionAPI + "services/{id_service}")
    public boolean deleteServices(@PathVariable int id_service){
        return servicesBusiness.deleteServicesBusiness(id_service);
    }
    @PutMapping(versionAPI + "services/{id_service}")
    public Services updateServices(@PathVariable int id_service, @RequestBody NewServices services){
        return servicesBusiness.updateServicesBusiness(id_service,services);
    }
    @GetMapping(versionAPI + "services/{id_service}")
    public Services getServices(@PathVariable int id_service){
        return servicesBusiness.getServicesBusiness(id_service);
    }
    @GetMapping(versionAPI + "services")
    public List<Services> getAllServices(){
        return servicesBusiness.getAllServicesBusiness();
    }
}
