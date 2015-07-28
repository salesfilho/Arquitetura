/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.config;


import br.prof.salesfilho.arq.persistence.config.ArquiteturaPersistenceConfig;
import br.prof.salesfilho.arq.view.config.ArquiteturaViewConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author salesfilho
 */
@Configuration
@Import({ArquiteturaPersistenceConfig.class, ArquiteturaViewConfig.class})
@ComponentScan(basePackages = {"br.prof.salesfilho.arq.demo.service", "br.prof.salesfilho.arq.demo.persistence", "br.prof.salesfilho.arq.demo.view"})
public class ApplicationConfig {

    @Bean
    public String packagesToScan() {
        //Pacotes que contem as classes anotadas com @Entity para que o JPA consiga manipular
        return "br.prof.salesfilho.arq.demo.model";
    }

    @Bean
    public Properties dataBaseProperties() {
        InputStream stream;
        Properties dataBaseProperties = new Properties();
        try {
            stream = getClass().getResourceAsStream("/database.properties");
            if (stream != null) {
                dataBaseProperties.load(stream);
                System.out.println("Configuracoes de banco carregadas...");
                stream.close();
            }else{
                System.out.println("Arquivo de configuraçao nao encontrado");
            }

        } catch (IOException ex) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBaseProperties;
    }

}
