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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RSI_RESERVATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RsiReservation.findAll", query = "SELECT r FROM RsiReservation r")
    , @NamedQuery(name = "RsiReservation.findById", query = "SELECT r FROM RsiReservation r WHERE r.id = :id")
    , @NamedQuery(name = "RsiReservation.findByReservationContact", query = "SELECT r FROM RsiReservation r WHERE r.reservationContact = :reservationContact")
    , @NamedQuery(name = "RsiReservation.findByReserved", query = "SELECT r FROM RsiReservation r WHERE r.reserved = :reserved")
    , @NamedQuery(name = "RsiReservation.findByActive", query = "SELECT r FROM RsiReservation r WHERE r.active = :active")})
public class RsiReservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 1024)
    @Column(name = "RESERVATION_CONTACT")
    private String reservationContact;
    @Column(name = "RESERVED")
    private Boolean reserved;
    @Column(name = "ACTIVE")
    private Boolean active;
    @JoinColumn(name = "CLIENT_RESERVER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RsiClient clientReserverId;
    @JoinColumn(name = "SCREENING_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RsiScreening screeningId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservationId")
    private Collection<RsiSeatReserved> rsiSeatReservedCollection;

    public RsiReservation() {
    }

    public RsiReservation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservationContact() {
        return reservationContact;
    }

    public void setReservationContact(String reservationContact) {
        this.reservationContact = reservationContact;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RsiClient getClientReserverId() {
        return clientReserverId;
    }

    public void setClientReserverId(RsiClient clientReserverId) {
        this.clientReserverId = clientReserverId;
    }

    public RsiScreening getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(RsiScreening screeningId) {
        this.screeningId = screeningId;
    }

    @XmlTransient
    public Collection<RsiSeatReserved> getRsiSeatReservedCollection() {
        return rsiSeatReservedCollection;
    }

    public void setRsiSeatReservedCollection(Collection<RsiSeatReserved> rsiSeatReservedCollection) {
        this.rsiSeatReservedCollection = rsiSeatReservedCollection;
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
        if (!(object instanceof RsiReservation)) {
            return false;
        }
        RsiReservation other = (RsiReservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soapserv.moviedto.RsiReservation[ id=" + id + " ]";
    }
    
}
