/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv.db;

import com.mycompany.soapserv.moviedto.RsiSeat;
import java.util.List;

/**
 *
 * @author Mateusz
 */
public interface SeatDAO extends GenericDao<RsiSeat, Integer>{
    
    List findAll();
}
