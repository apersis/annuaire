package com.cesi.demo.controller.employes;


import com.cesi.demo.buisness.employes.EmployesBusiness;
import com.cesi.demo.controller.employes.model.Employes;
import com.cesi.demo.controller.employes.model.NewEmployes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class EmployesController {
    private final String versionAPI = "/api/v1/";

    private final EmployesBusiness employesBusiness;

    @Autowired
    public EmployesController(EmployesBusiness employesBusiness) {
        this.employesBusiness = employesBusiness;
    }

    @PostMapping(versionAPI + "employes")
    public Employes createEmployes(@RequestBody NewEmployes employes){
        return employesBusiness.createEmployesBusiness(employes);
    }
    @DeleteMapping(versionAPI + "employes/{id_employe}")
    public boolean deleteEmployes(@PathVariable int id_employe){
        return employesBusiness.deleteEmployesBusiness(id_employe);
    }
    @PutMapping(versionAPI + "employes/{id_employe}")
    public Employes updateEmployes(@PathVariable int id_employe, @RequestBody NewEmployes employes){
        return employesBusiness.updateEmployesBusiness(id_employe,employes);
    }
    @GetMapping(versionAPI + "employes/{id_employe}")
    public Employes getEmployes(@PathVariable int id_employe){
        return employesBusiness.getEmployesBusiness(id_employe);
    }
    @GetMapping(versionAPI + "employes")
    public List<Employes> getAllEmployes(){
        return employesBusiness.getAllEmployesBusiness();
    }
}
