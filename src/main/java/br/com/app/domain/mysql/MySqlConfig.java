package br.com.app.domain.mysql;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="mySqlEntityManager",
        transactionManagerRef="mySqlTransactionManager",
        basePackages= {"br.com.app.domain.mysql.repository"})
public class MySqlConfig {

    @Bean(name = "mySqlEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource mySqlDataSoruce) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(mySqlDataSoruce);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.id.new_generator_mappings", "false");
        jpaProperties.put("hibernate.show_sql", "false");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.ejb.entitymanager_factory_name", "emf_mysql");
        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setPackagesToScan("br.com.app.domain.mysql");
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());

        return entityManagerFactory;
    }

    @Bean(name = "mySqlTransactionManager")
    @Primary
    public JpaTransactionManager transactionManager(EntityManagerFactory mySqlEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(mySqlEntityManager);
        return transactionManager;
    }

    @Bean(destroyMethod="close", name = "mySqlDataSoruce")
    @Primary
    public DataSource dataSource() {
        HikariConfig hc = new HikariConfig();
        hc.setDriverClassName("com.mysql.jdbc.Driver");
        hc.setJdbcUrl("jdbc:mysql://localhost:3306/teste?useTimezone=true&serverTimezone=UTC");
        hc.setUsername("root");
        hc.setPassword("admin");
        hc.setPoolName("mysql-local-pool");
        hc.addDataSourceProperty("cachePrepStmts", "true");
        hc.addDataSourceProperty("prepStmtCacheSize", "250");
        hc.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hc.addDataSourceProperty("useSSL", "false");
        HikariDataSource hds = new HikariDataSource(hc);
        hds.setIdleTimeout(30000);
        return hds;
    }

}