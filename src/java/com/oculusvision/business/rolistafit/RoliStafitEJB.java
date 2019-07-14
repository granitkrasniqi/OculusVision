/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.rolistafit;

import com.oculusvision.business.entities.Rolistafit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Granit Krasniqi
 */
@Stateless
public class RoliStafitEJB implements RoliStafitEJBLocal {
    
    @PersistenceContext(name = "OculusVisionPU")
    private EntityManager em;

    @Override
    public void shtoRoliStafit(Rolistafit roliStafit) throws RoliStafitException {
        em.persist(roliStafit);
    }

    @Override
    public boolean fshijRoliStafit(Integer roliStafitId) throws RoliStafitException {
        Rolistafit rolistf = gjejRolistafit(roliStafitId);
        if (rolistf != null) {
            em.remove(rolistf);
            return true;
        }
        return false;
    }

    @Override
    public Rolistafit editoRolistafit(Integer rolistafitId, Rolistafit roliStafit) throws RoliStafitException {
        Rolistafit rolistf = gjejRolistafit(rolistafitId);
        if(rolistf != null) {
            rolistf.setEmriRolit(roliStafit.getEmriRolit());
            rolistf.setPershkrimiRolit(roliStafit.getPershkrimiRolit());
        }
        return rolistf;
    }

    @Override
    public Rolistafit gjejRolistafit(Integer id) throws RoliStafitException {
        return em.find(Rolistafit.class, id);
    }

    @Override
    public List<Rolistafit> ktheRoletstafit() throws RoliStafitException {
        return em.createNamedQuery("Rolistafit.findAll", Rolistafit.class)
                .getResultList();
    }

}
