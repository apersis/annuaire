package com.cesi.demo.controller.sites;


import com.cesi.demo.buisness.sites.SitesBusiness;
import com.cesi.demo.controller.sites.model.Sites;
import com.cesi.demo.controller.sites.model.NewSites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class SitesController {
    private final String versionAPI = "/api/v1/";

    private final SitesBusiness sitesBusiness;

    @Autowired
    public SitesController(SitesBusiness sitesBusiness) {
        this.sitesBusiness = sitesBusiness;
    }

    @PostMapping(versionAPI + "sites")
    public Sites createSites(@RequestBody NewSites sites){
        return sitesBusiness.createSitesBusiness(sites);
    }
    @DeleteMapping(versionAPI + "sites/{id_site}")
    public boolean deleteSites(@PathVariable int id_site){
        return sitesBusiness.deleteSitesBusiness(id_site);
    }
    @PutMapping(versionAPI + "sites/{id_site}")
    public Sites updateSites(@PathVariable int id_site, @RequestBody NewSites sites){
        return sitesBusiness.updateSitesBusiness(id_site,sites);
    }
    @GetMapping(versionAPI + "sites/{id_site}")
    public Sites getSites(@PathVariable int id_site){
        return sitesBusiness.getSitesBusiness(id_site);
    }
    @GetMapping(versionAPI + "sites")
    public List<Sites> getAllSites(){
        return sitesBusiness.getAllSitesBusiness();
    }
}
