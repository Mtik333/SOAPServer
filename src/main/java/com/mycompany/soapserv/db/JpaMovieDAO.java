/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiMovie;
import java.util.List;
import javax.persistence.EntityManager;

public class JpaMovieDAO extends GenericJpaDao<RsiMovie, Integer> implements MovieDAO {
    @Override
    public List findByMovieTitle(String title) {
        List movies = null;
        try {
            EntityManager em = getEntityManager();
            movies = em.createQuery("select m from Movie m where lower(m.movieTitle) LIKE :title").setParameter("title", "%" + title.toLowerCase() + "%").getResultList();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public List findAllMovies() {
        EntityManager em = getEntityManager();
        List movies = em.createQuery("select m from Movie m").getResultList();
        em.close();
        return movies;
    }
}
