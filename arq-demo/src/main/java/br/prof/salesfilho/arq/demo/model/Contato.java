/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.model;

import br.prof.salesfilho.arq.model.GenericModelBean;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author salesfilho
 */
@Entity
@Getter
@Setter
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "ID", sequenceName = "sq_contato", allocationSize = 1)
public class Contato extends GenericModelBean<Long> {

    private String nome;
    private String email;

}
