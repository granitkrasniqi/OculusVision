/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.stafi;

import com.oculusvision.business.entities.Stafi;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Granit Krasniqi
 */
@Stateless
public class StafiEJB implements StafiEJBLocal {
    
    @PersistenceContext(name = "OculusVisionPU")
    private EntityManager em;
    

    @Override
    public void shtoStafin(Stafi stafi) throws StafiException {
        em.persist(stafi);
    }

    @Override
    public boolean fshijStafin(Integer stafitId) throws StafiException {
        Stafi stafi = gjejStafin(stafitId);
        if (stafi != null) {
            em.remove(stafi);
            return true;
        }
        return false;
    }

    @Override
    public Stafi editoStafi(Integer stafiId, Stafi stafi) throws StafiException {
        Stafi s = gjejStafin(stafiId);
        if (s != null) {
            s.setEmri(stafi.getEmri());
            s.setMbiemri(stafi.getMbiemri());
            s.setGjinia(stafi.getGjinia());
            s.setKualifikimetID(stafi.getKualifikimetID());
            s.setKlinikaID(stafi.getKlinikaID());
            s.setRoliStafitID(stafi.getRoliStafitID());
        }
         return s;
    }

    @Override
    public Stafi gjejStafin(Integer id) throws StafiException {
        return em.find(Stafi.class, id);
    }

    @Override
    public List<Stafi> ktheStafin() throws StafiException {
        return em.createNamedQuery("Stafi.findAll", Stafi.class).getResultList();
    }
    
    
}
