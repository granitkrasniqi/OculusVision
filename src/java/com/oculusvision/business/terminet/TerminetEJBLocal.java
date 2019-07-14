/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.terminet;

import com.oculusvision.business.entities.Termini;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Granit Krasniqi
 */
@Local
public interface TerminetEJBLocal {
    void shtoTerminin(Termini termini) throws TerminetException;
    boolean fshijTerminin(Integer terminiId) throws TerminetException;
    Termini editoTerminin(Integer terminiId, Termini termini) throws TerminetException;
    Termini gjejTerminin(Integer id) throws TerminetException;
    List<Termini> ktheTerminet() throws TerminetException;
    List<Termini> ktheTerminetSotme() throws TerminetException;
}
