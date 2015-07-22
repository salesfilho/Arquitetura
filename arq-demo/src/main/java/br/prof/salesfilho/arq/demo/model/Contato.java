/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.model;

import br.prof.salesfilho.arq.model.AbstractBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author salesfilho
 */
@Entity
@SequenceGenerator(name = "ID_CONTATO", sequenceName = "sq_contato", allocationSize = 1)
public class Contato extends AbstractBean<Long> {

    private String nome;
    private String email;

    @Id()
    @GeneratedValue(generator = "ID_CONTATO", strategy = GenerationType.SEQUENCE)
    @Override
    public Long getId() {
        return super.getId();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + "{nome=" + this.nome + ", email=" + this.email + "}"; //To change body of generated methods, choose Tools | Templates.
    }

}
