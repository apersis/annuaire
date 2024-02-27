package com.cesi.demo.buisness.employes;

import com.cesi.demo.controller.employes.model.NewEmployes;
import com.cesi.demo.controller.employes.model.Employes;
import com.cesi.demo.dao.employes.EmployesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployesBusiness {
    private final EmployesDAO employesDAO;

    @Autowired
    public EmployesBusiness(EmployesDAO employesDAO){
        this.employesDAO = employesDAO;
    }
    public Employes createEmployesBusiness(NewEmployes employes){
        return employesDAO.create(employes);
    }
    public boolean deleteEmployesBusiness(int employeId) {
        return employesDAO.delete(employeId);
    }
    public Employes updateEmployesBusiness(int employeId,NewEmployes employes){
        return employesDAO.update(employeId,employes);
    }
    public Employes getEmployesBusiness(int employeId){
        return employesDAO.read(employeId);
    }
    public List<Employes> getAllEmployesBusiness(){
        return employesDAO.read();
    }

}
