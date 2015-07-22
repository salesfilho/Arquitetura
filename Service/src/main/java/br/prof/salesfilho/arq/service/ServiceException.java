/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.service;

/**
 *
 * @author salesfilho
 */
public class ServiceException extends RuntimeException {

    String message;

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
