/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiScreening;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateusz
 */
public class JpaScreeningDAO extends GenericJpaDao<RsiScreening, Integer> implements ScreeningDAO{
    @Override
    public List findAll() {
        EntityManager em = getEntityManager();
        TypedQuery<RsiScreening> query = em.createNamedQuery("RsiScreening.findAll", RsiScreening.class);
        List<RsiScreening> results = query.getResultList();
        return results;
    }
}
