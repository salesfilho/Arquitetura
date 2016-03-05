/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.service;

import br.prof.salesfilho.arq.model.GenericModelBean;
import br.prof.salesfilho.arq.model.Page;
import br.prof.salesfilho.arq.persistence.GenericDAO;
import java.util.List;
import java.util.Map;
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
public class GenericService<T extends GenericModelBean, K> {

    @Inject
    private GenericDAO<T, K> genericDao;

    public void insert(T entity) {
        genericDao.insert(entity);
    }

    public void insertAll(List<T> entityList) {
        genericDao.insertAll(entityList);
    }

    public T update(T entity) {
        return genericDao.update(entity);
    }

    public void updateAll(List<T> entityList) {
        genericDao.updateAll(entityList);
    }

    public void delete(T entity) {
        genericDao.delete(entity);
    }

    public void deleteAll(List<T> entityList) {
        genericDao.deletetAll(entityList);
    }

    @Transactional(readOnly = true)
    public T findOne(K key) {
        return genericDao.findOne(key);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return genericDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<T> findRange(int startingAt, int maxPerPage) {
        return genericDao.findRange(startingAt, maxPerPage);
    }

    @Transactional(readOnly = true)
    public Page<T> findPage(int first, int pageSize, String sortField, boolean sortOrderAsc, Map<String, Object> filters) {
        return genericDao.findPage(first, pageSize, sortField, sortOrderAsc, filters);
    }

    @Transactional(readOnly = true)
    public int countAll() {
        return genericDao.countAll();
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
