package com.cesi.demo.dao.services;

import com.cesi.demo.controller.services.model.NewServices;
import com.cesi.demo.controller.services.model.Services;
import com.cesi.demo.controller.sites.model.NewSites;
import com.cesi.demo.controller.sites.model.Sites;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public class ServicesDAO {
    private final JdbcTemplate jdbcTemplate;

    private static final String ID_FIELD = "id_service";
    private static final String LIBELLE_FIELD = "libelle";


    private final RowMapper<Services> rowMapper = (rs, rowNum) -> {
        final Services services = new Services();
        services.setId_service(rs.getInt(ID_FIELD));
        services.setLibelle(rs.getString(LIBELLE_FIELD));
        return services;
    };

    @Autowired
    public ServicesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Services create(NewServices services) {
        //INSERT DANS BDD
        Services services1 = null;
        final String query = "INSERT INTO services(libelle) VALUES(?)";
        int result = this.jdbcTemplate.update(query, services.getLibelle());
        if(result ==1){
            List<Services> listServices = read();
            int serviceId = listServices.get(listServices.size() - 1).getId_service();
            services1 = new Services();
            services1.setId_service(serviceId);
            services1.setLibelle(services.getLibelle());
        }
        return services1;
    }


    public boolean delete(int serviceId){
        //DELETE DANS BDD
        final String query = ("DELETE from services where id_service=?");
        int result = this.jdbcTemplate.update(query, serviceId);
        if(result == 1) {
            return true;
        }else{
            return false;
        }
    }

    public Services update(int serviceId, NewServices services){
        //UPDATE DANS BDD
        Services services1 = null;
        final String query = "UPDATE services set libelle=? where id_service=?";
        int result = this.jdbcTemplate.update(query, services.getLibelle(), serviceId);
        if(result ==1){
            services1= new Services();
            services1.setId_service(serviceId);
            services1.setLibelle(services.getLibelle());
        }
        return services1;
    }

    public Services read(int serviceId) {
        // READ ONE PERSON DANS BDD
        List<Services> dtos = this.jdbcTemplate.query("select * from services where id_service =" + serviceId, this.rowMapper);
        Services services = null;
        if(dtos != null && dtos.size() == 1){
            services = new Services();
            services.setId_service(dtos.get(0).getId_service());
            services.setLibelle(dtos.get(0).getLibelle());


        }
        return services;
    }

    public List<Services> read() {
        // READ ALL PERSON DANS BDD
        List<Services> dtos = this.jdbcTemplate.query("select * from services", this.rowMapper);
        return dtos;
    }
}
