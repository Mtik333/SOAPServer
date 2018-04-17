/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.moviedto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(name = "RSI_AUDITORIUM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RsiAuditorium.findAll", query = "SELECT r FROM RsiAuditorium r")
    , @NamedQuery(name = "RsiAuditorium.findById", query = "SELECT r FROM RsiAuditorium r WHERE r.id = :id")
    , @NamedQuery(name = "RsiAuditorium.findByName", query = "SELECT r FROM RsiAuditorium r WHERE r.name = :name")
    , @NamedQuery(name = "RsiAuditorium.findBySeatsNumber", query = "SELECT r FROM RsiAuditorium r WHERE r.seatsNumber = :seatsNumber")})
public class RsiAuditorium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEATS_NUMBER")
    private int seatsNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditoriumId")
    private Collection<RsiSeat> rsiSeatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditoriumId")
    private Collection<RsiScreening> rsiScreeningCollection;

    public RsiAuditorium() {
    }

    public RsiAuditorium(Integer id) {
        this.id = id;
    }

    public RsiAuditorium(Integer id, String name, int seatsNumber) {
        this.id = id;
        this.name = name;
        this.seatsNumber = seatsNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @XmlTransient
    public Collection<RsiSeat> getRsiSeatCollection() {
        return rsiSeatCollection;
    }

    public void setRsiSeatCollection(Collection<RsiSeat> rsiSeatCollection) {
        this.rsiSeatCollection = rsiSeatCollection;
    }

    @XmlTransient
    public Collection<RsiScreening> getRsiScreeningCollection() {
        return rsiScreeningCollection;
    }

    public void setRsiScreeningCollection(Collection<RsiScreening> rsiScreeningCollection) {
        this.rsiScreeningCollection = rsiScreeningCollection;
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
        if (!(object instanceof RsiAuditorium)) {
            return false;
        }
        RsiAuditorium other = (RsiAuditorium) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soapserv.moviedto.RsiAuditorium[ id=" + id + " ]";
    }
    
}
