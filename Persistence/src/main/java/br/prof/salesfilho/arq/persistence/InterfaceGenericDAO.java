/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.persistence;

import java.util.List;
import java.util.Map;

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

    public K getId(T entity);

    public void insertAll(List<T> entityList);

    public void updateAll(List<T> entityList);

    public void deletetAll(List<T> entityList);

    public List<T> findAll();

    public List<T> findPage(int startingAt, int maxPerPage);

    public List<T> findPage(int first, int pageSize, String sortField, boolean sortOrderAsc, Map<String, Object> filters);

    public int countAll();

    public void checkBeforeInsert(T entity) throws PersistenceException;

    public void checkBeforeUpdate(T entity) throws PersistenceException;

    public void checkBeforeDelete(T entity) throws PersistenceException;

}
