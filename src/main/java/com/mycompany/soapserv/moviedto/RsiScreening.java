/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.moviedto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(name = "RSI_SCREENING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RsiScreening.findAll", query = "SELECT r FROM RsiScreening r")
    , @NamedQuery(name = "RsiScreening.findById", query = "SELECT r FROM RsiScreening r WHERE r.id = :id")
    , @NamedQuery(name = "RsiScreening.findByScreeningStart", query = "SELECT r FROM RsiScreening r WHERE r.screeningStart = :screeningStart")})
public class RsiScreening implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SCREENING_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date screeningStart;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "screeningId")
    private Collection<RsiReservation> rsiReservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "screeningId")
    private Collection<RsiSeatReserved> rsiSeatReservedCollection;
    @JoinColumn(name = "AUDITORIUM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RsiAuditorium auditoriumId;
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RsiMovie movieId;

    public RsiScreening() {
    }

    public RsiScreening(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getScreeningStart() {
        return screeningStart;
    }

    public void setScreeningStart(Date screeningStart) {
        this.screeningStart = screeningStart;
    }

    @XmlTransient
    public Collection<RsiReservation> getRsiReservationCollection() {
        return rsiReservationCollection;
    }

    public void setRsiReservationCollection(Collection<RsiReservation> rsiReservationCollection) {
        this.rsiReservationCollection = rsiReservationCollection;
    }

    @XmlTransient
    public Collection<RsiSeatReserved> getRsiSeatReservedCollection() {
        return rsiSeatReservedCollection;
    }

    public void setRsiSeatReservedCollection(Collection<RsiSeatReserved> rsiSeatReservedCollection) {
        this.rsiSeatReservedCollection = rsiSeatReservedCollection;
    }

    public RsiAuditorium getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(RsiAuditorium auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public RsiMovie getMovieId() {
        return movieId;
    }

    public void setMovieId(RsiMovie movieId) {
        this.movieId = movieId;
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
        if (!(object instanceof RsiScreening)) {
            return false;
        }
        RsiScreening other = (RsiScreening) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soapserv.moviedto.RsiScreening[ id=" + id + " ]";
    }
    
}
