/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Rashas
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable {

    @XmlElement
    private String nazwa;
    @XmlElement
    private String opis;
    @XmlElement
    private int cena;
    

    public Product() {
    }

    public Product(String nazwa, String opis, int cena) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Product{" + "nazwa=" + nazwa + ", opis=" + opis + ", cena=" + cena + '}';
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public int getCena() {
        return cena;
    }
}
