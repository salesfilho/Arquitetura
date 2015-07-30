/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.view;

import br.prof.salesfilho.arq.demo.model.Contato;
import br.prof.salesfilho.arq.view.crud.GenericCrudMBean;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salesfilho
 */
@Named
@ManagedBean
@ViewScoped
public class ContatoCrudMBean extends GenericCrudMBean<Contato, Long> {

    public ContatoCrudMBean() {
        setCurrentState(LIST_STATE);
    }
}
