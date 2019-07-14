/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.utils;

/**
 *
 * @author Granit Krasniqi
 */
public class ValidationUtils {
    
    public static boolean validNullEmpty(String str) {
        return str == null || str.trim().isEmpty();
    } 
    
    public static boolean onlyAlphabetic(String str) {
        return str.matches("[a-zA-Z_ ]+");
    }
    
    public static boolean onlyNumbers(String str) {
        return str.matches("[0-9]+");
    }
}
