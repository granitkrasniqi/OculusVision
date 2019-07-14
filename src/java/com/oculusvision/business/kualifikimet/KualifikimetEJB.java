/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.kualifikimet;

import com.oculusvision.business.entities.Kualifikimet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Granit Krasniqi
 */
@Stateless
public class KualifikimetEJB implements KualifikimetEJBLocal {
    
    @PersistenceContext(name = "OculusVisionPU")
    private EntityManager em;

    @Override
    public void shtoKualifikimin(Kualifikimet kualifikimi) throws KualifikimetException {
        em.persist(kualifikimi);
    }

    @Override
    public boolean fshijKualifikimin(Integer kualifikimiId) throws KualifikimetException {
        Kualifikimet k = gjejKualifikimin(kualifikimiId);
        if (k != null) {
            em.remove(k);
            return true;
        }
        return false;
    }

    @Override
    public Kualifikimet editoKualifikimin(Integer kualifikimiId, Kualifikimet kualifikimi) throws KualifikimetException {
        Kualifikimet k = gjejKualifikimin(kualifikimiId);
        if (k != null) {
            k.setEmri(kualifikimi.getEmri());
            k.setFakulteti(kualifikimi.getFakulteti());
            k.setShteti(kualifikimi.getShteti());
            k.setPershkrimi(kualifikimi.getPershkrimi());
        }
        return k;
    }

    @Override
    public Kualifikimet gjejKualifikimin(Integer id) throws KualifikimetException {
        return em.find(Kualifikimet.class, id);
    }

    @Override
    public List<Kualifikimet> ktheKualifikimet() throws KualifikimetException {
        return em.createNamedQuery("Kualifikimet.findAll", Kualifikimet.class).getResultList();
    }
    
}
