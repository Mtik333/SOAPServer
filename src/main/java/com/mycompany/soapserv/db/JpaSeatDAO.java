/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiSeat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateusz
 */
public class JpaSeatDAO extends GenericJpaDao<RsiSeat, Integer> implements SeatDAO{
    
    @Override
    public List findAll() {
        EntityManager em = getEntityManager();
        TypedQuery<RsiSeat> query = em.createNamedQuery("RsiSeat.findAll", RsiSeat.class);
        List<RsiSeat> results = query.getResultList();
        return results;
    }
}
