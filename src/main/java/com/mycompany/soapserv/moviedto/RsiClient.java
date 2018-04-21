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
@Table(name = "RSI_CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RsiClient.findAll", query = "SELECT r FROM RsiClient r")
    , @NamedQuery(name = "RsiClient.findById", query = "SELECT r FROM RsiClient r WHERE r.id = :id")
    , @NamedQuery(name = "RsiClient.findByUsername", query = "SELECT r FROM RsiClient r WHERE r.username = :username")
    , @NamedQuery(name = "RsiClient.findByPassword", query = "SELECT r FROM RsiClient r WHERE r.password = :password")
    , @NamedQuery(name = "RsiClient.findByUsernamePassword", query = "SELECT r FROM RsiClient r WHERE r.username = :username and r.password = :password")})

public class RsiClient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientReserverId")
    private Collection<RsiReservation> rsiReservationCollection;

    public RsiClient() {
    }

    public RsiClient(Integer id) {
        this.id = id;
    }

    public RsiClient(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<RsiReservation> getRsiReservationCollection() {
        return rsiReservationCollection;
    }

    public void setRsiReservationCollection(Collection<RsiReservation> rsiReservationCollection) {
        this.rsiReservationCollection = rsiReservationCollection;
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
        if (!(object instanceof RsiClient)) {
            return false;
        }
        RsiClient other = (RsiClient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soapserv.moviedto.RsiClient[ id=" + id + " ]";
    }
    
}
