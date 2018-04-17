package com.mycompany.soapserv;

import com.mycompany.soapserv.moviedto.RsiAuditorium;
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
    Boolean authenticateClient() throws InvalidPasswordException;
    
}
