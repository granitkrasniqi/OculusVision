/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.stafi;

import com.oculusvision.business.entities.Stafi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Granit Krasniqi
 */
@Local
public interface StafiEJBLocal {
    void shtoStafin(Stafi stafi) throws StafiException;
    boolean fshijStafin(Integer stafitId) throws StafiException;
    Stafi editoStafi(Integer stafiId, Stafi stafi) throws StafiException;
    Stafi gjejStafin(Integer id) throws StafiException;
    List<Stafi> ktheStafin() throws StafiException;
}
