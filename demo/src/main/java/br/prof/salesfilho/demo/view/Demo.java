/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.demo.view;

import br.prof.salesfilho.demo.config.DemoApplicationConfig;
import br.prof.salesfilho.demo.model.Contato;
import br.prof.salesfilho.demo.service.ContatoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author salesfilho
 */
public class Demo {

    private ContatoService contatoService;
    private Contato contato;
    private ApplicationContext ctx;

    public Demo() {
        ctx = new AnnotationConfigApplicationContext(DemoApplicationConfig.class);
        contatoService = ctx.getBean(ContatoService.class);
    }

    //ApplicationContext context = new JavaCon
    public static void main(String[] args) {
        //System.out.println("Olá Spring");
        //System.out.println("Olá Spring");
        //System.out.println("Olá Spring");
        //System.out.println("");

        Demo demo = new Demo();

        demo.addContato();
        demo.addAllContatos();
        demo.listContatos();

    }

    public void addContato() {

        //contatoService = new ContatoService();
        contato = new Contato();
        contato.setNome("Erika");
        contato.setEmail("erika@gmail.com");
        contatoService.insert(contato);
    }

    public void addAllContatos() {

        //contatoService = new ContatoService();
        List<Contato> contatos = new ArrayList();
        for (int i = 0; i < 100; i++) {
            contato = new Contato();
            contato.setNome("Contato_" + i + 100);
            contato.setEmail("Contato_" + i + 100 + "gmail.com");
            contatos.add(contato);
        }

        contatoService.insertAll(contatos);
    }

    public void listContatos() {

        List<Contato> contatos = contatoService.findAll();

        for (Contato c : contatos) {
            System.out.println(c.toString());
        }
    }

}
