/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.kualifikimet;

import com.oculusvision.business.entities.Kualifikimet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Granit Krasniqi
 */
@Local
public interface KualifikimetFacadeLocal {

    void create(Kualifikimet kualifikimet);

    void edit(Kualifikimet kualifikimet);

    void remove(Kualifikimet kualifikimet);

    Kualifikimet find(Object id);

    List<Kualifikimet> findAll();

    List<Kualifikimet> findRange(int[] range);

    int count();
    
}
