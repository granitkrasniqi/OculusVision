/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.stafi;

/**
 *
 * @author Granit Krasniqi
 */
public class StafiException extends Exception {

    /**
     * Creates a new instance of <code>StafiException</code> without detail
     * message.
     */
    public StafiException() {
    }

    /**
     * Constructs an instance of <code>StafiException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public StafiException(String msg) {
        super(msg);
    }
}
