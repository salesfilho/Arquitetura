/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.model;

import java.io.Serializable;

/**
 *
 * @author salesfilho
 * @param <K>
 */
public interface  IEntityBean<K> extends Serializable{
    public K getId();
}
