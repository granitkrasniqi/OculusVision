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
public interface KualifikimetEJBLocal {
    void shtoKualifikimin(Kualifikimet kualfikimi) throws KualifikimetException;
    boolean fshijKualifikimin(Integer kualifikimiId) throws KualifikimetException;
    Kualifikimet editoKualifikimin(Integer kualifikimiId, Kualifikimet kualfikimi) throws KualifikimetException;
    Kualifikimet gjejKualifikimin(Integer id) throws KualifikimetException;
    List<Kualifikimet> ktheKualifikimet() throws KualifikimetException;
}
