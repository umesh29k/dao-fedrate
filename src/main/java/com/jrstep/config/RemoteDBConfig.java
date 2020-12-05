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
@EnableJpaRepositories(entityManagerFactoryRef = "remoteDB", transactionManagerRef = "remoteDB",
        basePackages = {"com.jrstep.dao.remote"})
@EntityScan("com.jrstep.dao.remote")
public class RemoteDBConfig {
    private String dbName;

    @Bean(name = "remoteDB")
    @ConfigurationProperties(prefix = "remotedb.datasrouce")
    public DataSource remoteDbDataSouce() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("<db-class-name>");
        dataSourceBuilder.url("dev.itpaths.com/admin_rules_poc");
        dataSourceBuilder.username("admin_rules");
        dataSourceBuilder.password("rules@123");
        return dataSourceBuilder.build();
    }

    @Bean(name = "remoteDbEntittyManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(EntityManagerFactoryBuilder builder,
                         @Qualifier("remoteDbDataSource") DataSource
                                 remoteDbDataSrouce) {
        return builder.dataSource(remoteDbDataSrouce)
                .packages("com.jrstep.dao.remote")
                .persistenceUnit(dbName).build();
    }

    @Bean(name = "remoteDbTransationManager")
    public PlatformTransactionManager remoteDbTransactionManager(
            @Qualifier("remoteEntityManagerFactory")
                    EntityManagerFactory remoteEntityManagerFactory) {
        return new JpaTransactionManager(remoteEntityManagerFactory);
    }

}
