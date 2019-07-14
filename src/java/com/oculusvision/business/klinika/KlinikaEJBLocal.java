/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.klinika;

import com.oculusvision.business.entities.Klinika;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Granit Krasniqi
 */
@Local
public interface KlinikaEJBLocal {
    void shtoKliniken(Klinika klinika) throws KlinikaException;
    boolean fshijKliniken(Integer klinikaId) throws KlinikaException;
    Klinika editoKliniken(Integer klinikaId, String emri, String adresa, String pershkrimi) throws KlinikaException;
    Klinika gjejKliniken(Integer id) throws KlinikaException;
    List<Klinika> ktheKlinikat() throws KlinikaException;
}
