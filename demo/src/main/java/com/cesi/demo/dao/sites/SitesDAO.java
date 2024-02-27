package com.cesi.demo.dao.sites;

import com.cesi.demo.controller.sites.model.NewSites;
import com.cesi.demo.controller.sites.model.Sites;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class SitesDAO {
    private final JdbcTemplate jdbcTemplate;

    private static final String ID_FIELD = "id_site";
    private static final String TYPE_FIELD = "type";
    private static final String VILLE_FIELD = "ville";


    private final RowMapper<Sites> rowMapper = (rs, rowNum) -> {
        final Sites sites = new Sites();
        sites.setId_site(rs.getInt(ID_FIELD));
        sites.setType(rs.getString(TYPE_FIELD));
        sites.setVille(rs.getString(VILLE_FIELD));
        return sites;
    };

    @Autowired
    public SitesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Sites create(NewSites sites) {
        //INSERT DANS BDD
        Sites sites1 = null;
        final String query = "INSERT INTO sites(type, ville) VALUES(?,?)";
        int result = this.jdbcTemplate.update(query, sites.getType(), sites.getVille());
        if(result ==1){
            List<Sites> listSites = read();
            int SiteId = listSites.get(listSites.size() - 1).getId_site();
            sites1= new Sites();
            sites1.setId_site(SiteId);
            sites1.setType(sites.getType());
            sites1.setVille(sites.getVille());
        }
        return sites1;
    }


    public boolean delete(int siteId){
        //DELETE DANS BDD
        final String query = ("DELETE from sites where id_site=?");
        int result = this.jdbcTemplate.update(query, siteId);
        if(result == 1) {
            return true;
        }else{
            return false;
        }
    }

    public Sites update(int siteId, NewSites sites){
        //UPDATE DANS BDD
        Sites sites1= null;
        final String query = "UPDATE sites set type=?, ville=? where id_site=?";
        int result = this.jdbcTemplate.update(query, sites.getType(), sites.getVille(), siteId);
        if(result ==1){
            sites1= new Sites();
            sites1.setId_site(siteId);
            sites1.setType(sites.getType());
            sites1.setVille(sites.getVille());
        }
        return sites1;
    }

    public Sites read(int siteId) {
        // READ ONE PERSON DANS BDD
        List<Sites> dtos = this.jdbcTemplate.query("select * from sites where id_site =" + siteId, this.rowMapper);
        Sites sites = null;
        if(dtos != null && dtos.size() == 1){
            sites = new Sites();
            sites.setId_site(dtos.get(0).getId_site());
            sites.setType(dtos.get(0).getType());
            sites.setVille(dtos.get(0).getVille());


        }
        return sites;
    }

    public List<Sites> read() {
        // READ ALL PERSON DANS BDD
        List<Sites> dtos = this.jdbcTemplate.query("select * from sites", this.rowMapper);
        return dtos;
    }
}
