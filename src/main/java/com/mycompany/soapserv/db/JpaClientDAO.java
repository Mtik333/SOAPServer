/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiClient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateusz
 */
public class JpaClientDAO extends GenericJpaDao<RsiClient, Integer> implements ClientDAO{

    @Override
    public RsiClient findByUsernamePassword(String username, String password) {
        EntityManager em = getEntityManager();
        TypedQuery<RsiClient> query = em.createNamedQuery("RsiClient.findByUsernamePassword", RsiClient.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<RsiClient> results = query.getResultList();
        if (results.isEmpty())
            return null;
        else return results.get(0);
        
    }
    
}
