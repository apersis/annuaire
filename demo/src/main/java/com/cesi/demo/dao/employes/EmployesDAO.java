package com.cesi.demo.dao.employes;

import com.cesi.demo.controller.employes.model.Employes;
import com.cesi.demo.controller.employes.model.NewEmployes;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class EmployesDAO {
    private final JdbcTemplate jdbcTemplate;

    private static final String ID_FIELD = "id_salarie";
    private static final String NOM_FIELD = "nom";
    private static final String PRENOM_FIELD = "prenom";
    private static final String TELFIXE_FIELD = "tel_fixe";
    private static final String TELPORTABLE_FIELD = "tel_portable";
    private static final String EMAIL_FIELD = "email";
    private static final String SITEID_FIELD = "id_site";
    private static final String SERVICEID_FIELD = "id_service";


    private final RowMapper<Employes> rowMapper = (rs, rowNum) -> {
        final Employes employes = new Employes();
        employes.setId_salarie(rs.getInt(ID_FIELD));
        employes.setNom(rs.getString(NOM_FIELD));
        employes.setPrenom(rs.getString(PRENOM_FIELD));
        employes.setTel_fixe(rs.getString(TELFIXE_FIELD));
        employes.setTel_portable(rs.getString(TELPORTABLE_FIELD));
        employes.setEmail(rs.getString(EMAIL_FIELD));
        employes.setId_site(rs.getInt(SITEID_FIELD));
        employes.setId_service(rs.getInt(SERVICEID_FIELD));
        return employes;
    };

    @Autowired
    public EmployesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Employes create(NewEmployes employes) {
        //INSERT DANS BDD
        Employes employes1 = null;
        final String query = "INSERT INTO salaries(nom, prenom, tel_fixe, tel_portable, email, id_site, id_service) " +
                "VALUES(?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(query, employes.getNom(), employes.getPrenom(), employes.getTel_fixe(),
                employes.getTel_portable(), employes.getEmail(), employes.getId_site(), employes.getId_service());
        if(result ==1){
            List<Employes> listEmployes = read();
            int EmployeId = listEmployes.get(listEmployes.size() - 1).getId_salarie();
            employes1= new Employes();
            employes1.setId_salarie(EmployeId);
            employes1.setNom(employes.getNom());
            employes1.setPrenom(employes.getPrenom());
            employes1.setTel_fixe(employes.getTel_fixe());
            employes1.setTel_portable(employes.getTel_portable());
            employes1.setEmail(employes.getEmail());
            employes1.setId_site(employes.getId_site());
            employes1.setId_service(employes.getId_service());
        }
        return employes1;
    }


    public boolean delete(int employeId){
        //DELETE DANS BDD
        final String query = ("DELETE from salaries where id_salarie=?");
        int result = this.jdbcTemplate.update(query, employeId);
        if(result == 1) {
            return true;
        }else{
            return false;
        }
    }

    public Employes update(int employeId, NewEmployes employes){
        //UPDATE DANS BDD
        Employes employes1= null;
        final String query = "UPDATE salaries set nom=?, prenom=?, tel_fixe=?, tel_portable=?, email=?, id_site=?, id_service=? " +
                "where id_salarie=?";
        int result = this.jdbcTemplate.update(query, employes.getNom(), employes.getPrenom(), employes.getTel_fixe(),
                employes.getTel_portable(), employes.getEmail(), employes.getId_site(), employes.getId_service(), employeId);
        if(result ==1){
            employes1= new Employes();
            employes1.setId_salarie(employeId);
            employes1.setNom(employes.getNom());
            employes1.setPrenom(employes.getPrenom());
            employes1.setTel_fixe(employes.getTel_fixe());
            employes1.setTel_portable(employes.getTel_portable());
            employes1.setEmail(employes.getEmail());
            employes1.setId_site(employes.getId_site());
            employes1.setId_service(employes.getId_service());
        }
        return employes1;
    }

    public Employes read(int employeId) {
        // READ ONE PERSON DANS BDD
        List<Employes> dtos = this.jdbcTemplate.query("select * from salaries where id_salarie =" + employeId, this.rowMapper);
        Employes employes = null;
        if(dtos != null && dtos.size() == 1){
            employes = new Employes();
            employes.setId_salarie(dtos.get(0).getId_salarie());
            employes.setNom(dtos.get(0).getNom());
            employes.setPrenom(dtos.get(0).getPrenom());
            employes.setTel_fixe(dtos.get(0).getTel_fixe());
            employes.setTel_portable(dtos.get(0).getTel_portable());
            employes.setEmail(dtos.get(0).getEmail());
            employes.setId_site(dtos.get(0).getId_site());
            employes.setId_service(dtos.get(0).getId_service());


        }
        return employes;
    }

    public List<Employes> read() {
        // READ ALL PERSON DANS BDD
        List<Employes> dtos = this.jdbcTemplate.query("select * from salaries", this.rowMapper);
        return dtos;
    }
}
