package com.jrstep.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "remoteEntityManagerFactory", transactionManagerRef="remoteTransactionManager",
        basePackages = { "com.jrstep.dao.remote" })
public class RemoteDBConfig {
    @Primary
    @Bean(name = "remoteDataSource")
    public DataSource remoteDataSource() {
        return DataSourceBuilder.create().url("jdbc:mysql://dev.itpaths.com:3306/admin_rules_poc").username("admin_rules").password("rules@123").build();
    }

    @Primary
    @Bean(name = "remoteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean remoteEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("remoteDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.jrstep.dao.remote")
                .persistenceUnit("admin_rules_poc")
                .build();
    }

    @Primary
    @Bean(name = "remoteTransactionManager")
    public PlatformTransactionManager remoteTransactionManager(
            @Qualifier("remoteEntityManagerFactory") EntityManagerFactory remoteEntityManagerFactory) {
        return new JpaTransactionManager(remoteEntityManagerFactory);
    }

}
