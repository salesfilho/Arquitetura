/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <K>
 */
@Named
public class GenericDAO<T, K extends Serializable> implements InterfaceGenericDAO<T, K> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entityClass;

    public GenericDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class) pt.getActualTypeArguments()[0];
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void insert(T entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public T update(T entity) {
        return this.entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        T auxEntity = entityManager.merge(entity);
        this.entityManager.remove(auxEntity);
    }

    @Override
    public T findOne(K key) {
        return this.entityManager.find(this.entityClass, key);
    }

    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT t FROM " + this.entityClass.getSimpleName() + " t").getResultList();
    }

    @Override
    public void checkBeforeInsert(T entity) throws PersistenceException {
        if (entity == null) {
            throw new PersistenceException("Não é possível persistir objeto nulo.");
        }
    }

    @Override
    public void checkBeforeUpdate(T entity) throws PersistenceException {
        if (entity == null) {
            throw new PersistenceException("Não é possível atualizar objeto nulo.");
        }
    }

    @Override
    public void checkBeforeDelete(T entity) throws PersistenceException {
        if (entity == null) {
            throw new PersistenceException("Não é possível excluir objeto nulo.");
        }
    }

}
