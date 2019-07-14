/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.pacientet;

/**
 *
 * @author Granit Krasniqi
 */
public class PacientiException extends Exception {

    /**
     * Creates a new instance of <code>PacientiException</code> without detail
     * message.
     */
    public PacientiException() {
    }

    /**
     * Constructs an instance of <code>PacientiException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PacientiException(String msg) {
        super(msg);
    }
}
