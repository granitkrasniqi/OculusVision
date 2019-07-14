/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.pacientet;

import com.oculusvision.business.entities.Pacienti;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Granit Krasniqi
 */
@Stateless
public class PacientiEJB implements PacientiEJBLocal {
    
    @PersistenceContext(name = "OculusVisionPU")
    private EntityManager em;

    @Override
    public void shtoPacientin(Pacienti pacienti) throws PacientiException {
        try {
        em.persist(pacienti);
        } catch (ConstraintViolationException e) {
            Logger log = Logger.getLogger(PacientiEJB.class.getName());
            log.log(Level.SEVERE,"Exception: ");
            e.getConstraintViolations().forEach(err->log.log(Level.SEVERE,err.toString()));
    }
        
    }

    @Override
    public boolean fshijPacientin(Integer pacientiId) throws PacientiException {
        Pacienti p = gjejPacientin(pacientiId);
        if (p != null) {
            em.remove(p);
            return true;
        }
        return false;
    }

    @Override
    public Pacienti editoPacientin(Integer pacientiId, Pacienti pacienti) throws PacientiException {
        Pacienti p = gjejPacientin(pacientiId);
        if (p != null) {
            p.setEmri(pacienti.getEmri());
            p.setMbiemri(pacienti.getMbiemri());
            p.setAdresa(pacienti.getAdresa());
            p.setDataLindjes(pacienti.getDataLindjes());
            p.setPershkrim(pacienti.getPershkrim());
            p.setGjinia(pacienti.getGjinia());
            p.setNumriTelefonit(pacienti.getNumriTelefonit());
            p.setEmail(pacienti.getEmail());
        }
        return p;
    }

    @Override
    public Pacienti gjejPacientin(Integer id) throws PacientiException {
        return em.find(Pacienti.class, id);
    }

    @Override
    public List<Pacienti> kthePacientet() throws PacientiException {
        return em.createNamedQuery("Pacienti.findAll", Pacienti.class).getResultList();
    }
    
}
