package com.itpaths.rules.price;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/*
 * @Author javaoutofbound
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Bean
    public KieContainer kieContainer() {
        // load up the knowledge base
        KieServices kServices = KieServices.Factory.get();
        // Create the KieContainer which read all files from class path
        KieContainer kContainer = kServices.getKieClasspathContainer();
        return kContainer;
    }*/

}
