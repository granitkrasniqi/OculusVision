/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Granit Krasniqi
 */
@Entity
@Table(name = "ilac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ilac.findAll", query = "SELECT i FROM Ilac i")
    , @NamedQuery(name = "Ilac.findById", query = "SELECT i FROM Ilac i WHERE i.id = :id")
    , @NamedQuery(name = "Ilac.findByEmri", query = "SELECT i FROM Ilac i WHERE i.emri = :emri")
    , @NamedQuery(name = "Ilac.findByDoza", query = "SELECT i FROM Ilac i WHERE i.doza = :doza")
    , @NamedQuery(name = "Ilac.findByBrendi", query = "SELECT i FROM Ilac i WHERE i.brendi = :brendi")
    , @NamedQuery(name = "Ilac.findByDataProdhimit", query = "SELECT i FROM Ilac i WHERE i.dataProdhimit = :dataProdhimit")
    , @NamedQuery(name = "Ilac.findByDataSkadimit", query = "SELECT i FROM Ilac i WHERE i.dataSkadimit = :dataSkadimit")
    , @NamedQuery(name = "Ilac.findByPershkrimi", query = "SELECT i FROM Ilac i WHERE i.pershkrimi = :pershkrimi")
    , @NamedQuery(name = "Ilac.findByQmimi", query = "SELECT i FROM Ilac i WHERE i.qmimi = :qmimi")
    , @NamedQuery(name = "Ilac.findByVendiProdhimit", query = "SELECT i FROM Ilac i WHERE i.vendiProdhimit = :vendiProdhimit")})
public class Ilac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "Emri")
    private String emri;
    @Column(name = "Doza")
    private Integer doza;
    @Size(max = 100)
    @Column(name = "Brendi")
    private String brendi;
    @Column(name = "DataProdhimit")
    @Temporal(TemporalType.DATE)
    private Date dataProdhimit;
    @Column(name = "DataSkadimit")
    @Temporal(TemporalType.DATE)
    private Date dataSkadimit;
    @Size(max = 300)
    @Column(name = "pershkrimi")
    private String pershkrimi;
    @Column(name = "qmimi")
    private BigInteger qmimi;
    @Size(max = 50)
    @Column(name = "VendiProdhimit")
    private String vendiProdhimit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ilacID")
    private Collection<Alergjite> alergjiteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ilacID")
    private Collection<Perberesitilacit> perberesitilacitCollection;

    public Ilac() {
    }

    public Ilac(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public Integer getDoza() {
        return doza;
    }

    public void setDoza(Integer doza) {
        this.doza = doza;
    }

    public String getBrendi() {
        return brendi;
    }

    public void setBrendi(String brendi) {
        this.brendi = brendi;
    }

    public Date getDataProdhimit() {
        return dataProdhimit;
    }

    public void setDataProdhimit(Date dataProdhimit) {
        this.dataProdhimit = dataProdhimit;
    }

    public Date getDataSkadimit() {
        return dataSkadimit;
    }

    public void setDataSkadimit(Date dataSkadimit) {
        this.dataSkadimit = dataSkadimit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public BigInteger getQmimi() {
        return qmimi;
    }

    public void setQmimi(BigInteger qmimi) {
        this.qmimi = qmimi;
    }

    public String getVendiProdhimit() {
        return vendiProdhimit;
    }

    public void setVendiProdhimit(String vendiProdhimit) {
        this.vendiProdhimit = vendiProdhimit;
    }

    @XmlTransient
    public Collection<Alergjite> getAlergjiteCollection() {
        return alergjiteCollection;
    }

    public void setAlergjiteCollection(Collection<Alergjite> alergjiteCollection) {
        this.alergjiteCollection = alergjiteCollection;
    }

    @XmlTransient
    public Collection<Perberesitilacit> getPerberesitilacitCollection() {
        return perberesitilacitCollection;
    }

    public void setPerberesitilacitCollection(Collection<Perberesitilacit> perberesitilacitCollection) {
        this.perberesitilacitCollection = perberesitilacitCollection;
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
        if (!(object instanceof Ilac)) {
            return false;
        }
        Ilac other = (Ilac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oculusvision.business.entities.Ilac[ id=" + id + " ]";
    }
    
}
