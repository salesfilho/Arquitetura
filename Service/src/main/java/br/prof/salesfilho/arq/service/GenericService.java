/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.service;

import br.prof.salesfilho.arq.persistence.GenericDAO;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <K>
 */
@Named
@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = {"ServiceException.class"})
public class GenericService<T, K extends Serializable> {

    @Inject
    private GenericDAO<T, K> genericDao;

    public void insert(T entity) {
        genericDao.insert(entity);
    }

    public void insertAll(List<T> entityList) {
        for (T entity : entityList) {
            insert(entity);
        }
    }

    public T update(T entity) {
        return genericDao.update(entity);
    }

    public void updateAll(List<T> entityList) {
        for (T entity : entityList) {
            update(entity);
        }
    }

    public void delete(T entity) {
        genericDao.delete(entity);
    }

    public void deleteAll(List<T> entityList) {
        for (T entity : entityList) {
            delete(entity);
        }
    }

    @Transactional(readOnly = true)
    public T findOne(K key) {
        return genericDao.findOne(key);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return genericDao.findAll();
    }

    public void checkBeforeInsert(T entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException("Não é possível inserir objeto nulo");
        }
    }

    public void checkBeforeUpdate(T entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException("Não é possível atualizar objeto nulo");
        }
    }

    public void checkBeforeDelete(T entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException("Não é possível excluir objeto nulo");
        }
    }

}
