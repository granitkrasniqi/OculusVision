/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvision.business.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Granit Krasniqi
 */
@Entity
@Table(name = "stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
    , @NamedQuery(name = "Stock.findById", query = "SELECT s FROM Stock s WHERE s.id = :id")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockid")
    private Collection<Produkti> produktiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockid")
    private Collection<Syzet> syzetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockid")
    private Collection<Thjerreza> thjerrezaCollection;
    @JoinColumn(name = "KlinikaID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klinika klinikaID;

    public Stock() {
    }

    public Stock(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Produkti> getProduktiCollection() {
        return produktiCollection;
    }

    public void setProduktiCollection(Collection<Produkti> produktiCollection) {
        this.produktiCollection = produktiCollection;
    }

    @XmlTransient
    public Collection<Syzet> getSyzetCollection() {
        return syzetCollection;
    }

    public void setSyzetCollection(Collection<Syzet> syzetCollection) {
        this.syzetCollection = syzetCollection;
    }

    @XmlTransient
    public Collection<Thjerreza> getThjerrezaCollection() {
        return thjerrezaCollection;
    }

    public void setThjerrezaCollection(Collection<Thjerreza> thjerrezaCollection) {
        this.thjerrezaCollection = thjerrezaCollection;
    }

    public Klinika getKlinikaID() {
        return klinikaID;
    }

    public void setKlinikaID(Klinika klinikaID) {
        this.klinikaID = klinikaID;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oculusvision.business.entities.Stock[ id=" + id + " ]";
    }
    
}
