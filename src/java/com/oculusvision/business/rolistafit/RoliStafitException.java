/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.rolistafit;

/**
 *
 * @author Granit Krasniqi
 */
public class RoliStafitException extends Exception {

    /**
     * Creates a new instance of <code>RoliStafitException</code> without detail
     * message.
     */
    public RoliStafitException() {
    }

    /**
     * Constructs an instance of <code>RoliStafitException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RoliStafitException(String msg) {
        super(msg);
    }
}
