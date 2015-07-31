/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.view.crud;

import br.prof.salesfilho.arq.model.AbstractBean;
import br.prof.salesfilho.arq.service.GenericService;
import br.prof.salesfilho.arq.view.primefaces.GenericLazyDataModel;
import br.prof.salesfilho.arq.view.primefaces.LazyDataModelView;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salesfilho
 * @param <T>
 * @param <K>
 */
@Named
@ManagedBean
public class GenericCrudMBean<T extends AbstractBean, K> extends BaseMBean {

    @Inject
    private GenericService<T, K> genericService;
    @Inject
    private GenericLazyDataModel<T, K> dataModel;

    private Class<T> entityBeanClass;
    private T bean;

    public GenericCrudMBean() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityBeanClass = (Class) pt.getActualTypeArguments()[0];
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public GenericLazyDataModel<T, K> getDataModel() {
        return dataModel;
    }

    /**
     * Prepara view adicionar
     */
    @Override
    public void changeToInsertState() {
        setCurrentState(INSERT_STATE);
        if (this.bean == null) {
            createNewEntityBean();
        }
    }

    /**
     * Adiciona
     */
    public void processInsert() {
        try {
            genericService.insert(this.bean);
            addMessage(FacesMessage.SEVERITY_INFO, "Incusão: ", "Operação realizada com sucesso.");
            createNewEntityBean();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Falha ao inserir registro.", e.getMessage());
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Prepara view atualizar
     *
     * @param id
     */
    public void startUpdate(K id) {
        this.setBean(genericService.findOne(id));
        this.setCurrentState(UPDATE_STATE);
    }

    /**
     * Atualiza registro
     */
    public void processUpdate() {
        try {
            genericService.update(this.bean);
            addMessage(FacesMessage.SEVERITY_INFO, "Alteração: ", "Operação realizada com sucesso.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Falha ao atualizar registro.", e.getMessage());
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Deleta o bean com id passado como parametro
     *
     * @param id
     */
    public void processDelete(K id) {
        try {
            T auxBean = genericService.findOne(id);
            genericService.delete(auxBean);
            addMessage(FacesMessage.SEVERITY_INFO, "Exclusão: ", "Operação realizada com sucesso.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Falha ao excluir registro.", e.getMessage());
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void createNewEntityBean() {
        try {
            this.bean = (T) Class.forName(this.entityBeanClass.getName()).newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException ex) {
            Logger.getLogger(GenericCrudMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
