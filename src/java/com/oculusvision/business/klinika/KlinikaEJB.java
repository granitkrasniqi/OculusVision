/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.klinika;

import com.oculusvision.business.entities.Klinika;
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
public class KlinikaEJB implements KlinikaEJBLocal {
    
    @PersistenceContext(name = "OculusVisionPU")
    private EntityManager em;

    @Override
    public void shtoKliniken(Klinika klinika) throws KlinikaException {
        em.persist(klinika);
    }

    @Override
    public boolean fshijKliniken(Integer klinikaId) throws KlinikaException {
        Klinika k = gjejKliniken(klinikaId);
        if (k != null) {
            em.remove(k);
            return true;
        }
        return false;
    }
    
    @Override
    public Klinika editoKliniken(Integer klinikaId, String emri,
            String adresa, String pershkrimi) throws KlinikaException {
        Klinika k = gjejKliniken(klinikaId);
        System.out.println(k);
        if (k != null) {
            k.setEmri(emri);
            k.setAdresa(adresa);
            k.setPershkrimi(pershkrimi);
        }
        return k;
    }

    @Override
    public Klinika gjejKliniken(Integer id) throws KlinikaException {
        return em.find(Klinika.class, id);
    }

    @Override
    public List<Klinika> ktheKlinikat() throws KlinikaException {
        TypedQuery<Klinika> query = em.createNamedQuery("Klinika.findAll", Klinika.class);
        return query.getResultList();
    }

    

    
}
