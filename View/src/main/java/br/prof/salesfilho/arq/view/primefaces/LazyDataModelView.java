/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.view.primefaces;

import br.prof.salesfilho.arq.model.GenericModelBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author salesfilho
 * @param <T>
 */
@Named
public class LazyDataModelView<T extends GenericModelBean, K> implements Serializable {

    @Inject
    private LazyDataModel<T> lazyModel;

    private T selected;

    @PostConstruct
    public void init() {
    }
    
       public LazyDataModel<T> getLazyModel() {
        return lazyModel;
    }
 
    public T getSelected() {
        return selected;
    }
 
    public void setSelected(T selected) {
        this.selected = selected;
    }
     
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Objeto selecionado", (String) ((T) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
