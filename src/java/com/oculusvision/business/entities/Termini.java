/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Granit Krasniqi
 */
@Entity
@Table(name = "termini")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termini.findAll", query = "SELECT t FROM Termini t")
    , @NamedQuery(name = "Termini.findById", query = "SELECT t FROM Termini t WHERE t.id = :id")
    , @NamedQuery(name = "Termini.findByKohaPlanifikuarF", query = "SELECT t FROM Termini t WHERE t.kohaPlanifikuarF = :kohaPlanifikuarF")
    , @NamedQuery(name = "Termini.findByKohaPlanifikuarM", query = "SELECT t FROM Termini t WHERE t.kohaPlanifikuarM = :kohaPlanifikuarM")
    , @NamedQuery(name = "Termini.findByDataTerminit", query = "SELECT t FROM Termini t WHERE t.dataTerminit = :dataTerminit")})
public class Termini implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "KohaPlanifikuarF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kohaPlanifikuarF;
    @Column(name = "KohaPlanifikuarM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kohaPlanifikuarM;
    @Column(name = "DataTerminit")
    @Temporal(TemporalType.DATE)
    private Date dataTerminit;
    @JoinColumn(name = "StafiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Stafi stafiID;
    @JoinColumn(name = "PacientiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pacienti pacientiID;

    public Termini() {
    }

    public Termini(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getKohaPlanifikuarF() {
        return kohaPlanifikuarF;
    }

    public void setKohaPlanifikuarF(Date kohaPlanifikuarF) {
        this.kohaPlanifikuarF = kohaPlanifikuarF;
    }

    public Date getKohaPlanifikuarM() {
        return kohaPlanifikuarM;
    }

    public void setKohaPlanifikuarM(Date kohaPlanifikuarM) {
        this.kohaPlanifikuarM = kohaPlanifikuarM;
    }

    public Date getDataTerminit() {
        return dataTerminit;
    }

    public void setDataTerminit(Date dataTerminit) {
        this.dataTerminit = dataTerminit;
    }

    public Stafi getStafiID() {
        return stafiID;
    }

    public void setStafiID(Stafi stafiID) {
        this.stafiID = stafiID;
    }

    public Pacienti getPacientiID() {
        return pacientiID;
    }

    public void setPacientiID(Pacienti pacientiID) {
        this.pacientiID = pacientiID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Termini)) {
            return false;
        }
        Termini other = (Termini) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oculusvision.business.entities.Termini[ id=" + id + " ]";
    }
    
}
