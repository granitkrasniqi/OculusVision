/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.kualifikimet;

import com.oculusvision.business.entities.Kualifikimet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Granit Krasniqi
 */
@Stateless
public class KualifikimetFacade extends AbstractFacade<Kualifikimet> implements KualifikimetFacadeLocal {

    @PersistenceContext(unitName = "OculusVisionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KualifikimetFacade() {
        super(Kualifikimet.class);
    }
    
}
