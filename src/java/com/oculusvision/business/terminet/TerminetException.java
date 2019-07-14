/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.terminet;

/**
 *
 * @author Granit Krasniqi
 */
public class TerminetException extends Exception {

    /**
     * Creates a new instance of <code>TerminetException</code> without detail
     * message.
     */
    public TerminetException() {
    }

    /**
     * Constructs an instance of <code>TerminetException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TerminetException(String msg) {
        super(msg);
    }
}
