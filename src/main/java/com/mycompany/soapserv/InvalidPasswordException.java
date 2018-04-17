/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapserv;

/**
 *
 * @author rashas
 */
public class InvalidPasswordException extends Exception {

    private String exceptionMessage = "";

    public InvalidPasswordException(String message) {
        super(message);
        this.exceptionMessage = message;
    }

    public String getExceptionInfo() {
        return this.exceptionMessage;
    }

}
