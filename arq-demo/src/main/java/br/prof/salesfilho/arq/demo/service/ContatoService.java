/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.service;

import br.prof.salesfilho.arq.service.GenericService;
import br.prof.salesfilho.arq.demo.model.Contato;
import br.prof.salesfilho.arq.demo.persistence.ContatoDAO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salesfilho
 */
@Named
public class ContatoService extends GenericService<Contato, Long>{
    //@Inject
    //private ContatoDAO dao;
}
