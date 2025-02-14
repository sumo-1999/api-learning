package com.revising.apidev.configuration;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.revising.apidev.repository.revision",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class RevisionDBConfig {

    @Bean("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.revisiondb")
    @Primary
    public DataSource primaryDataSource(){
        DataSource dataSource= DataSourceBuilder.create().type(HikariDataSource.class).build();
        System.out.println("Primary DataSource: " + dataSource);
        return dataSource;
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("primaryDataSource") DataSource dataSource){
        return builder.dataSource(dataSource).packages("com.revising.apidev.entity.revision")
                .persistenceUnit("primary").build();
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryPlatformTransactionManager(@Qualifier("primaryEntityManagerFactory")
                                                                          EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/revision_db");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
////        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setPoolName("HikariCP");

//        System.out.println("Primary DataSource: " + dataSource);

