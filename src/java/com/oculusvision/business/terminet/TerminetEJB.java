/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.terminet;

import com.oculusvision.business.entities.Termini;
import java.util.Date;
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
public class TerminetEJB implements TerminetEJBLocal {
    
    @PersistenceContext(name = "OculusVisionPU")
    private EntityManager em;

    @Override
    public void shtoTerminin(Termini termini) throws TerminetException {
        em.persist(termini);
    }

    @Override
    public boolean fshijTerminin(Integer terminiId) throws TerminetException {
        Termini termini = gjejTerminin(terminiId);
        if(termini != null) {
            em.remove(termini);
            return true;
        }
        return false;
    }

    @Override
    public Termini editoTerminin(Integer terminiId, Termini termini) throws TerminetException {
        Termini terminiN = gjejTerminin(terminiId);
        if(terminiN != null) {
            terminiN.setKohaPlanifikuarF(termini.getKohaPlanifikuarF());
            terminiN.setKohaPlanifikuarM(termini.getKohaPlanifikuarM());
            terminiN.setDataTerminit(termini.getDataTerminit());
            terminiN.setPacientiID(termini.getPacientiID());
            terminiN.setStafiID(termini.getStafiID());
        }
        return terminiN;
    }

    @Override
    public Termini gjejTerminin(Integer id) throws TerminetException {
        return em.find(Termini.class, id);
    }

    @Override
    public List<Termini> ktheTerminet() throws TerminetException {
        return em.createNamedQuery("Termini.findAll", Termini.class).getResultList();
    }

    @Override
    public List<Termini> ktheTerminetSotme() throws TerminetException {
        TypedQuery<Termini> query = em.createNamedQuery("Termini.findByDataTerminit", Termini.class);
        query.setParameter("dataTerminit", new Date());
        return query.getResultList();
    }
    
}
