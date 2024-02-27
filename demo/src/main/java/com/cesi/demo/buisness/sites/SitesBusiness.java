package com.cesi.demo.buisness.sites;

import com.cesi.demo.controller.sites.model.NewSites;
import com.cesi.demo.controller.sites.model.Sites;
import com.cesi.demo.dao.sites.SitesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitesBusiness {
    private final SitesDAO sitesDAO;

    @Autowired
    public SitesBusiness(SitesDAO sitesDAO){
        this.sitesDAO = sitesDAO;
    }
    public Sites createSitesBusiness(NewSites sites){
        return sitesDAO.create(sites);
    }
    public boolean deleteSitesBusiness(int siteId) {
        return sitesDAO.delete(siteId);
    }
    public Sites updateSitesBusiness(int siteId,NewSites sites){
        return sitesDAO.update(siteId,sites);
    }
    public Sites getSitesBusiness(int siteId){
        return sitesDAO.read(siteId);
    }
    public List<Sites> getAllSitesBusiness(){
        return sitesDAO.read();
    }

}
