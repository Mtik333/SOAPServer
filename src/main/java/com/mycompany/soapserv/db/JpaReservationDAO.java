/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiReservation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateusz
 */
public class JpaReservationDAO extends GenericJpaDao<RsiReservation, Integer> implements ReservationDAO{
    @Override
    public List findAll() {
        EntityManager em = getEntityManager();
        TypedQuery<RsiReservation> query = em.createNamedQuery("RsiReservation.findAll", RsiReservation.class);
        List<RsiReservation> results = query.getResultList();
        return results;
    }
}
