/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiReservation;
import com.mycompany.soapserv.moviedto.RsiSeatReserved;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateusz
 */
public class JpaSeatReservedDAO extends GenericJpaDao<RsiSeatReserved, Integer> implements SeatReservedDAO {
    @Override
    public List findAll() {
        EntityManager em = getEntityManager();
        TypedQuery<RsiSeatReserved> query = em.createNamedQuery("RsiSeatReserved.findAll", RsiSeatReserved.class);
        List<RsiSeatReserved> results = query.getResultList();
        return results;
    }

    @Override
    public RsiSeatReserved findByReservationId(RsiReservation reservation) {
        EntityManager em = getEntityManager();
        TypedQuery<RsiSeatReserved> query = em.createNamedQuery("RsiSeatReserver.findByReservationId", RsiSeatReserved.class);
        query.setParameter("reservationId", reservation);
        return query.getSingleResult();
    }
}
