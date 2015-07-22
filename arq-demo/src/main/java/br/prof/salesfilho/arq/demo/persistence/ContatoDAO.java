/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.persistence;

import br.prof.salesfilho.arq.persistence.GenericDAO;
import br.prof.salesfilho.arq.demo.model.Contato;
import br.prof.salesfilho.arq.demo.model.QContato;
import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author salesfilho
 */
@Named
public class ContatoDAO extends GenericDAO<Contato, Long> {

    private JPAQuery query;
    private QContato qContato = QContato.contato;

    public List<Contato> findByName(String nome) {
        List<Contato> contatos;
        query = new JPAQuery(getEntityManager());

        contatos = query.from(qContato).where(qContato.nome.containsIgnoreCase(nome)).list(qContato);
        return contatos;
    }
}
