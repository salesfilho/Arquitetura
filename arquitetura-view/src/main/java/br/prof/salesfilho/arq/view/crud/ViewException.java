/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.view.crud;

/**
 *
 * @author salesfilho
 */
public class ViewException extends Exception {

    String message;

    public ViewException(String message) {
        super(message);
        this.message = message;
    }

}
