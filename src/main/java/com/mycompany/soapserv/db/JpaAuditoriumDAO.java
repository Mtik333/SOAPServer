/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiAuditorium;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Mateusz
 */
public class JpaAuditoriumDAO extends GenericJpaDao<RsiAuditorium, Integer> implements AuditoriumDAO{

    @Override
    public List findAll() {
        EntityManager em = getEntityManager();
        List movies = em.createQuery("select c from RsiClient c where ").getResultList();
        em.close();
        return movies;
    }
    
}
