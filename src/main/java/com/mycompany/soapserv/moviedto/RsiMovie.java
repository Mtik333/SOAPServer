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
@Table(name = "RSI_MOVIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RsiMovie.findAll", query = "SELECT r FROM RsiMovie r")
    , @NamedQuery(name = "RsiMovie.findById", query = "SELECT r FROM RsiMovie r WHERE r.id = :id")
    , @NamedQuery(name = "RsiMovie.findByTitle", query = "SELECT r FROM RsiMovie r WHERE r.title = :title")
    , @NamedQuery(name = "RsiMovie.findByDirector", query = "SELECT r FROM RsiMovie r WHERE r.director = :director")
    , @NamedQuery(name = "RsiMovie.findByActors", query = "SELECT r FROM RsiMovie r WHERE r.actors = :actors")
    , @NamedQuery(name = "RsiMovie.findByDescription", query = "SELECT r FROM RsiMovie r WHERE r.description = :description")
    , @NamedQuery(name = "RsiMovie.findByDuration", query = "SELECT r FROM RsiMovie r WHERE r.duration = :duration")})
public class RsiMovie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECTOR")
    private String director;
    @Size(max = 1000)
    @Column(name = "ACTORS")
    private String actors;
    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DURATION")
    private Integer duration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<RsiScreening> rsiScreeningCollection;

    public RsiMovie() {
    }

    public RsiMovie(Integer id) {
        this.id = id;
    }

    public RsiMovie(Integer id, String title, String director) {
        this.id = id;
        this.title = title;
        this.director = director;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
        if (!(object instanceof RsiMovie)) {
            return false;
        }
        RsiMovie other = (RsiMovie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soapserv.moviedto.RsiMovie[ id=" + id + " ]";
    }
    
}
