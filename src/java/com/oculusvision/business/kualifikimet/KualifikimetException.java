/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.kualifikimet;

/**
 *
 * @author Granit Krasniqi
 */
public class KualifikimetException extends Exception {

    /**
     * Creates a new instance of <code>KualifikimetException</code> without
     * detail message.
     */
    public KualifikimetException() {
    }

    /**
     * Constructs an instance of <code>KualifikimetException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public KualifikimetException(String msg) {
        super(msg);
    }
}
