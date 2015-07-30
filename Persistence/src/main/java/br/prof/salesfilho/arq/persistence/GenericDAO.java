/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.persistence;

import br.prof.salesfilho.arq.model.AbstractBean;
import br.prof.salesfilho.arq.model.Page;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <K>
 */
@Named
public class GenericDAO<T extends AbstractBean, K> implements InterfaceGenericDAO<T, K> {

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
    public K getId(T entity) {
        return (K) entity.getId();
    }

    @Override
    public void insert(T entity) {
        this.checkBeforeInsert(entity);
        this.entityManager.persist(entity);
    }

    @Override
    public void insertAll(List<T> entityList) {
        for (T entity : entityList) {
            insert(entity);
        }
    }

    @Override
    public T update(T entity) {
        checkBeforeUpdate(entity);
        return this.entityManager.merge(entity);
    }

    @Override
    public void updateAll(List<T> entityList) {
        for (T entity : entityList) {
            update(entity);
        }
    }

    @Override
    public void delete(T entity) {
        checkBeforeDelete(entity);
        T auxEntity = entityManager.find(entityClass, entity.getId());
        this.entityManager.remove(auxEntity);
    }

    @Override
    public void deletetAll(List<T> entityList) {
        for (T entity : entityList) {
            delete(entity);
        }
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

    @Override
    public List<T> findRange(int startingAt, int maxPerPage) {
        Query query;
        query = this.entityManager.createQuery("SELECT t FROM " + this.entityClass.getSimpleName() + " t");
        query.setFirstResult(startingAt);
        query.setMaxResults(maxPerPage);

        return query.getResultList();
    }

    @Override
    public List<T> findPage(int first, int pageSize, String sortField, boolean sortOrderAsc, Map<String, Object> filters) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> from = query.from(entityClass);

        Predicate predicate = builder.and();

        if (!filters.isEmpty()) {
            Iterator<Entry<String, Object>> iterator = filters.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> entry = iterator.next();
                //Adiciona os filtros
                predicate = builder.and(predicate, builder.like(builder.lower(from.<String>get(entry.getKey())), "%" + entry.getValue().toString().toLowerCase() + "%"));
            }
        }
        //Realiza a ordenacao
        TypedQuery<T> typedQuery = entityManager.createQuery(query.select(from).where(predicate));
        if (sortField != null) {
            if (!sortOrderAsc) {
                typedQuery = entityManager.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get(sortField))));
            } else {
                typedQuery = entityManager.createQuery(query.select(from).where(predicate).orderBy(builder.desc(from.get(sortField))));
            }

        }
        //Configura os limites minimo e maximo da consulta
        typedQuery.setFirstResult(first);
        typedQuery.setMaxResults(first + pageSize);

        return typedQuery.getResultList();
    }

    @Override
    public int countPage(int first, int pageSize, String sortField, boolean sortOrderAsc, Map<String, Object> filters) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> from = query.from(entityClass);

        Predicate predicate = builder.and();

        if (!filters.isEmpty()) {
            Iterator<Entry<String, Object>> iterator = filters.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> entry = iterator.next();
                //Adiciona os filtros
                predicate = builder.and(predicate, builder.like(builder.lower(from.<String>get(entry.getKey())), "%" + entry.getValue().toString().toLowerCase() + "%"));
            }
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(query.select(from).where(predicate));
        return typedQuery.getResultList().size();
    }

    @Override
    public int countAll() {
        Query query;
        query = this.entityManager.createQuery("SELECT COUNT(t) FROM " + this.entityClass.getSimpleName() + " t");
        Number result = (Number) query.getSingleResult();

        return result.intValue();
    }

}
