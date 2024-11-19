package com.otsi.tconnect.ms.fup.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.otsi.tconnect.ms.fup.fup.repository",
    entityManagerFactoryRef = "fupEntityManagerFactory",
    transactionManagerRef = "fupTransactionManager"
)
public class FUPDataSourceConfig {
	
	
	@Bean(name = "fupDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.fup")
    public DataSource fupDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "fupEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean fupEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("fupDataSource") DataSource fupDataSource) {
        return builder
                .dataSource(fupDataSource)
                .packages("com.otsi.tconnect.ms.fup.fup.entity")
                .persistenceUnit("fup")
                .build();
    }

    @Bean(name = "fupTransactionManager")
    public PlatformTransactionManager fupTransactionManager(
            @Qualifier("fupEntityManagerFactory") EntityManagerFactory fupTransactionManager) {
        return new JpaTransactionManager(fupTransactionManager);
    }

}
