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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.revising.apidev.repository.user",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class UserDBConfig {

    @Bean("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.userdb")
    public DataSource SecondaryDataSource(){
        DataSource dataSource= DataSourceBuilder.create().type(HikariDataSource.class).build();
        System.out.println("Secondary DataSource: " + dataSource);
        return dataSource;
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("secondaryDataSource") DataSource dataSource){
        return builder.dataSource(dataSource).packages("com.revising.apidev.entity.user")
                .persistenceUnit("secondary").build();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryPlatformTransactionManager(@Qualifier("secondaryEntityManagerFactory")
                                                                          EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
