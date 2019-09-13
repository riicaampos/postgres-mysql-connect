package br.com.app.domain.postgres;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager",
        basePackages = "br.com.app.domain.postgres.repository")
public class PostgresConfig {

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("postgresDataSource") DataSource postgresDataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(postgresDataSource);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.id.new_generator_mappings", "false");
        jpaProperties.put("hibernate.show_sql", "false");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.ejb.entitymanager_factory_name", "emf_postgres");
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults","false");
        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setPackagesToScan("br.com.app.domain.postgres");
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());

        return entityManagerFactory;

    }

    @Bean( name = "postgresDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig hc = new HikariConfig();
        hc.setDriverClassName("org.postgresql.Driver");
        hc.setJdbcUrl("jdbc:postgresql://localhost:5432/teste");
        hc.setUsername("postgres");
        hc.setPassword("admin");
        hc.setPoolName("postgres-local-pool");
        hc.addDataSourceProperty("cachePrepStmts", "true");
        hc.addDataSourceProperty("prepStmtCacheSize", "250");
        hc.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hc.addDataSourceProperty("useSSL", "false");
        HikariDataSource hds = new HikariDataSource(hc);
        hds.setIdleTimeout(30000);
        return hds;

    }

    @Bean(name = "postgresTransactionManager")
    @Qualifier
    public JpaTransactionManager transactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory postgresEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(postgresEntityManagerFactory);
        return transactionManager;

    }

}
