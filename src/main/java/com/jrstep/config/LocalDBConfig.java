package com.jrstep.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "localDB", transactionManagerRef = "localDB", basePackages = {"com.jrstep.dao.local"})
@EntityScan("com.jrstep.dao.local")
public class LocalDBConfig {
    private String dbName;

    @Bean(name = "localDB")
    @ConfigurationProperties(prefix = "localdb.datasrouce")
    public DataSource localDbDataSouce() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("<db-class-name>");
        dataSourceBuilder.url("localhost");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("test");
        return dataSourceBuilder.build();
    }

    @Bean(name = "localDbEntittyManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(EntityManagerFactoryBuilder builder,
                         @Qualifier("localDbDataSource") DataSource
                                 localDbDataSrouce) {
        return builder.dataSource(localDbDataSrouce)
                .packages("com.jrstep.dao.local")
                .persistenceUnit(dbName).build();
    }

    @Bean(name = "localDbTransationManager")
    public PlatformTransactionManager localDbTransactionManager(
            @Qualifier("localEntityManagerFactory")
                    EntityManagerFactory localEntityManagerFactory) {
        return new JpaTransactionManager(localEntityManagerFactory);
    }

}
