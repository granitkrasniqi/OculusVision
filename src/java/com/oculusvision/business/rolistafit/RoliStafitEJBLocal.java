/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.rolistafit;

import com.oculusvision.business.entities.Rolistafit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Granit Krasniqi
 */
@Local
public interface RoliStafitEJBLocal {
    void shtoRoliStafit(Rolistafit roliStafit) throws RoliStafitException;
    boolean fshijRoliStafit(Integer roliStafitId) throws RoliStafitException;
    Rolistafit editoRolistafit(Integer rolistafitId, Rolistafit roliStafit) throws RoliStafitException;
    Rolistafit gjejRolistafit(Integer id) throws RoliStafitException;
    List<Rolistafit> ktheRoletstafit() throws RoliStafitException;
}
