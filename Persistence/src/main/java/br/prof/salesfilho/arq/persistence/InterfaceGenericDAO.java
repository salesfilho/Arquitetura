/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.persistence;

import java.util.List;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <K>
 */
public interface InterfaceGenericDAO<T, K> {

    public void insert(T entity);

    public T update(T entity);

    public void delete(T entity);

    public T findOne(K key);

    public List<T> findAll();

    public void checkBeforeInsert(T entity) throws PersistenceException;

    public void checkBeforeUpdate(T entity) throws PersistenceException;

    public void checkBeforeDelete(T entity) throws PersistenceException;

}
