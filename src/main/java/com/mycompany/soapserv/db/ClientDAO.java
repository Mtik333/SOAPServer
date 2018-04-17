/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiClient;

/**
 *
 * @author Mateusz
 */
public interface ClientDAO extends GenericDao<RsiClient, Integer>{
    
    RsiClient findByUsernamePassword(String username, String password);
}
