/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.demo.persistence;

import br.prof.salesfilho.arq.persistence.GenericDAO;
import br.prof.salesfilho.demo.model.Contato;
import javax.inject.Named;

/**
 *
 * @author salesfilho
 */
@Named
public class ContatoDAO extends GenericDAO<Contato, Long> {

}
