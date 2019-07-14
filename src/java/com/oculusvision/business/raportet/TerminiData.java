/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.raportet;

/**
 *
 * @author Granit Krasniqi
 */
public class TerminiData {
    private String emriPacientit;
    private String emriMjekut;
    private String kohaFillimit;
    private String kohaMbarimit;

    public TerminiData(String emriPacientit, String emriMjekut, String kohaFillimit, String kohaMbarimit) {
        this.emriPacientit = emriPacientit;
        this.emriMjekut = emriMjekut;
        this.kohaFillimit = kohaFillimit;
        this.kohaMbarimit = kohaMbarimit;
    }

    public String getEmriPacientit() {
        return emriPacientit;
    }

    public void setEmriPacientit(String emriPacientit) {
        this.emriPacientit = emriPacientit;
    }

    public String getEmriMjekut() {
        return emriMjekut;
    }

    public void setEmriMjekut(String emriMjekut) {
        this.emriMjekut = emriMjekut;
    }

    public String getKohaFillimit() {
        return kohaFillimit;
    }

    public void setKohaFillimit(String kohaFillimit) {
        this.kohaFillimit = kohaFillimit;
    }

    public String getKohaMbarimit() {
        return kohaMbarimit;
    }

    public void setKohaMbarimit(String kohaMbarimit) {
        this.kohaMbarimit = kohaMbarimit;
    }
    
    
}
