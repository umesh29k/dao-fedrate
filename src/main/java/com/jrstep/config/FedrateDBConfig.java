package com.jrstep.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "fedrateEntityManagerFactory", transactionManagerRef = "fedrateTransactionManager",
        basePackages = { "com.jrstep.dao.fedrate" })
public class FedrateDBConfig {

    @Bean(name = "fedrateDataSource")
    public DataSource fedrateDataSource() {
        return DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/fedrate").username("root").password("").build();
    }

    @Bean(name = "fedrateEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean fedrateEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("fedrateDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.jdbc.batch_size", 30);
        properties.put("hibernate.order_inserts", "true");
        properties.put("hibernate.order_updates", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(dataSource)
                .packages("com.jrstep.dao.fedrate")
                .persistenceUnit("fedrate")
                .properties(properties)
                .build();
    }

    @Bean(name = "fedrateTransactionManager")
    public PlatformTransactionManager fedrateTransactionManager(
            @Qualifier("fedrateEntityManagerFactory") EntityManagerFactory fedrateEntityManagerFactory) {
        return new JpaTransactionManager(fedrateEntityManagerFactory);
    }

}
