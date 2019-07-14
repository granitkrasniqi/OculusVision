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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Granit Krasniqi
 */
@Entity
@Table(name = "qertifikata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qertifikata.findAll", query = "SELECT q FROM Qertifikata q")
    , @NamedQuery(name = "Qertifikata.findById", query = "SELECT q FROM Qertifikata q WHERE q.id = :id")
    , @NamedQuery(name = "Qertifikata.findByShenimet", query = "SELECT q FROM Qertifikata q WHERE q.shenimet = :shenimet")
    , @NamedQuery(name = "Qertifikata.findByDataLeshimit", query = "SELECT q FROM Qertifikata q WHERE q.dataLeshimit = :dataLeshimit")
    , @NamedQuery(name = "Qertifikata.findBySemundjet", query = "SELECT q FROM Qertifikata q WHERE q.semundjet = :semundjet")
    , @NamedQuery(name = "Qertifikata.findBySimptomat", query = "SELECT q FROM Qertifikata q WHERE q.simptomat = :simptomat")
    , @NamedQuery(name = "Qertifikata.findByDitePushimi", query = "SELECT q FROM Qertifikata q WHERE q.ditePushimi = :ditePushimi")
    , @NamedQuery(name = "Qertifikata.findByQertifikataID", query = "SELECT q FROM Qertifikata q WHERE q.qertifikataID = :qertifikataID")})
public class Qertifikata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 2000)
    @Column(name = "Shenimet")
    private String shenimet;
    @Column(name = "DataLeshimit")
    @Temporal(TemporalType.DATE)
    private Date dataLeshimit;
    @Size(max = 500)
    @Column(name = "Semundjet")
    private String semundjet;
    @Size(max = 500)
    @Column(name = "Simptomat")
    private String simptomat;
    @Column(name = "DitePushimi")
    private Integer ditePushimi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QertifikataID")
    private int qertifikataID;
    @JoinColumn(name = "PacientiID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pacienti pacientiID;
    @JoinColumn(name = "LlojiQertifikatesID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Llojiqertifikates llojiQertifikatesID;

    public Qertifikata() {
    }

    public Qertifikata(Integer id) {
        this.id = id;
    }

    public Qertifikata(Integer id, int qertifikataID) {
        this.id = id;
        this.qertifikataID = qertifikataID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShenimet() {
        return shenimet;
    }

    public void setShenimet(String shenimet) {
        this.shenimet = shenimet;
    }

    public Date getDataLeshimit() {
        return dataLeshimit;
    }

    public void setDataLeshimit(Date dataLeshimit) {
        this.dataLeshimit = dataLeshimit;
    }

    public String getSemundjet() {
        return semundjet;
    }

    public void setSemundjet(String semundjet) {
        this.semundjet = semundjet;
    }

    public String getSimptomat() {
        return simptomat;
    }

    public void setSimptomat(String simptomat) {
        this.simptomat = simptomat;
    }

    public Integer getDitePushimi() {
        return ditePushimi;
    }

    public void setDitePushimi(Integer ditePushimi) {
        this.ditePushimi = ditePushimi;
    }

    public int getQertifikataID() {
        return qertifikataID;
    }

    public void setQertifikataID(int qertifikataID) {
        this.qertifikataID = qertifikataID;
    }

    public Pacienti getPacientiID() {
        return pacientiID;
    }

    public void setPacientiID(Pacienti pacientiID) {
        this.pacientiID = pacientiID;
    }

    public Llojiqertifikates getLlojiQertifikatesID() {
        return llojiQertifikatesID;
    }

    public void setLlojiQertifikatesID(Llojiqertifikates llojiQertifikatesID) {
        this.llojiQertifikatesID = llojiQertifikatesID;
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
        if (!(object instanceof Qertifikata)) {
            return false;
        }
        Qertifikata other = (Qertifikata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oculusvision.business.entities.Qertifikata[ id=" + id + " ]";
    }
    
}
