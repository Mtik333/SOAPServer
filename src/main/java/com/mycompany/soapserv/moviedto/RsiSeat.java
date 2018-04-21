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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(name = "RSI_SEAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RsiSeat.findAll", query = "SELECT r FROM RsiSeat r")
    , @NamedQuery(name = "RsiSeat.findById", query = "SELECT r FROM RsiSeat r WHERE r.id = :id")
    , @NamedQuery(name = "RsiSeat.findBySeatRow", query = "SELECT r FROM RsiSeat r WHERE r.seatRow = :seatRow")
    , @NamedQuery(name = "RsiSeat.findBySeatNumber", query = "SELECT r FROM RsiSeat r WHERE r.seatNumber = :seatNumber")})
public class RsiSeat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEAT_ROW")
    private int seatRow;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEAT_NUMBER")
    private int seatNumber;
    @XmlElement
    @JoinColumn(name = "AUDITORIUM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RsiAuditorium auditoriumId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seatId")
    private Collection<RsiSeatReserved> rsiSeatReservedCollection;

    public RsiSeat() {
    }

    public RsiSeat(Integer id) {
        this.id = id;
    }

    public RsiSeat(Integer id, int seatRow, int seatNumber) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    @XmlTransient
    public RsiAuditorium getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(RsiAuditorium auditoriumId) {
        this.auditoriumId = auditoriumId;
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
        if (!(object instanceof RsiSeat)) {
            return false;
        }
        RsiSeat other = (RsiSeat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soapserv.moviedto.RsiSeat[ id=" + id + " ]";
    }
    
}
