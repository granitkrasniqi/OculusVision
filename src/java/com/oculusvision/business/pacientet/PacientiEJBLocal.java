/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.pacientet;

import com.oculusvision.business.entities.Pacienti;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Granit Krasniqi
 */
@Local
public interface PacientiEJBLocal {
    void shtoPacientin(Pacienti pacienti) throws PacientiException;
    boolean fshijPacientin(Integer pacientiId) throws PacientiException;
    Pacienti editoPacientin(Integer pacientiId, Pacienti pacienti) throws PacientiException;
    Pacienti gjejPacientin(Integer id) throws PacientiException;
    List<Pacienti> kthePacientet() throws PacientiException;
}
