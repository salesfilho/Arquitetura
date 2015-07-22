/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.view;

import br.prof.salesfilho.arq.demo.model.Contato;
import br.prof.salesfilho.arq.demo.service.ContatoService;
import br.prof.salesfilho.arq.view.crud.GenericCrudMBean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salesfilho
 */
@Named
@ManagedBean
@ViewScoped
public class ContatoCrudMBean extends GenericCrudMBean<Contato, Long> {

    @Inject
    private ContatoService contatoService;
    private String stringBusca = "";
    private List<Contato> contatos;

    public ContatoCrudMBean() {
        setCurrentState(SEARCH_STATE);
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public String getStringBusca() {
        return this.stringBusca;
    }

    public void setStringBusca(String strBusca) {
        this.stringBusca = strBusca;
    }

    public List<Contato> searchByName() {
        this.contatos = contatoService.findByName(this.stringBusca);
        return contatos;
    }

}