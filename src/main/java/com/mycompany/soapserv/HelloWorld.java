package com.mycompany.soapserv;

import com.mycompany.soapserv.moviedto.RsiAuditorium;
import com.mycompany.soapserv.moviedto.RsiClient;
import com.mycompany.soapserv.moviedto.RsiMovie;
import com.mycompany.soapserv.moviedto.RsiReservation;
import com.mycompany.soapserv.moviedto.RsiScreening;
import com.mycompany.soapserv.moviedto.RsiSeat;
import com.mycompany.soapserv.moviedto.RsiSeatReserved;
import java.awt.Image;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

//Service Endpoint Interface
@WebService /// ustawia portType w 

@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL) //optional

public interface HelloWorld {

    @WebMethod (operationName = "getHelloWorldAsString", exclude = false)
    String getHelloWorldAsString(String name) throws InvalidPasswordException;

    @WebMethod
    List<Product> getProducts();
    
    @WebMethod
    List<RsiAuditorium> getAuditoriums();
    
    @WebMethod
    List<RsiClient> getClients();
    
    @WebMethod
    List<RsiMovie> getMovies();
    
    @WebMethod
    List<RsiReservation> getReservations();
    
    @WebMethod
    List<RsiScreening> getScreenings();
    
    @WebMethod
    List<RsiSeat> getSeats();
    
    @WebMethod
    List<RsiSeatReserved> getReservedSeats();
    
    @WebMethod
    Boolean authenticateClient() throws InvalidPasswordException;
    
    @WebMethod
    Image downloadImage(String name);
    
    @WebMethod
    void createReservation(RsiReservation reservationId, RsiSeat rsiSeat);
}
