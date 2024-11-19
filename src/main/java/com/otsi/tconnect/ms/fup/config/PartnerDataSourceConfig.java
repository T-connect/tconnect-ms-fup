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
    basePackages = "com.otsi.tconnect.ms.fup.partner.repository",
    entityManagerFactoryRef = "partnerEntityManagerFactory",
    transactionManagerRef = "partnerTransactionManager"
)
public class PartnerDataSourceConfig {
	
	
	@Bean(name = "partnerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.partner")
    public DataSource billingDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "partnerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean billingEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("partnerDataSource") DataSource billingDataSource) {
        return builder
                .dataSource(billingDataSource)
                .packages("com.otsi.tconnect.ms.fup.partner.entity")
                .persistenceUnit("partner")
                .build();
    }

    @Bean(name = "partnerTransactionManager")
    public PlatformTransactionManager billingTransactionManager(
            @Qualifier("partnerEntityManagerFactory") EntityManagerFactory billingEntityManagerFactory) {
        return new JpaTransactionManager(billingEntityManagerFactory);
    }

}
