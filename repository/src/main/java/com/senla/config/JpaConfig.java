package com.senla.config;

import jakarta.persistence.EntityManagerFactory;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class JpaConfig {
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    private static final String HIBERNATE_JAVAX_CACHE_PROVIDER = "hibernate.javax.cache.provider";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setPackagesToScan("com.senla");
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Map<String, Object> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put(HIBERNATE_HBM2DDL_AUTO, "validate");

        jpaPropertyMap.put(HIBERNATE_GENERATE_STATISTICS, true);
        jpaPropertyMap.put(HIBERNATE_SHOW_SQL, true);
        jpaPropertyMap.put(HIBERNATE_FORMAT_SQL, true);

        jpaPropertyMap.put(HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, true);
        jpaPropertyMap.put(HIBERNATE_JAVAX_CACHE_PROVIDER, EhcacheCachingProvider.class.getName());

        factoryBean.setJpaPropertyMap(jpaPropertyMap);

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) { return new JpaTransactionManager(entityManagerFactory); }
}
