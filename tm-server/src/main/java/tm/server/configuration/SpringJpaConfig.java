package tm.server.configuration;

import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tm.server.api.service.DatabasePropertyService;
import tm.server.bootstrap.ServerBootstrap;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"tm.server.repository"})
public class SpringJpaConfig {

    @NotNull
    private final DatabasePropertyService dbProperties = new DatabasePropertyServiceImpl();

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbProperties.getJdbcDriver());
        dataSource.setUrl(dbProperties.getJdbcUrl());
        dataSource.setUsername(dbProperties.getJdbcUsername());
        dataSource.setPassword(dbProperties.getJdbcPassword());
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean factoryBean;
        factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("tm.server.repository.deltaspike");
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("tm.server.entity", "tm.server.repository");
        final Properties properties = new Properties();
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.USE_SECOND_LEVEL_CACHE, "true");
        properties.put(Environment.USE_QUERY_CACHE, "true");
        properties.put(Environment.CACHE_REGION_PREFIX, "taskmanager-server");
        properties.put(Environment.CACHE_PROVIDER_CONFIG, "hazelcast.xml");
        properties.put(Environment.CACHE_REGION_FACTORY, "com.hazelcast.hibernate.HazelcastLocalCacheRegionFactory");
        properties.put(Environment.IMPLICIT_NAMING_STRATEGY, "jpa");
//        properties.put(Environment.USE_MINIMAL_PUTS, "true");
//        properties.put("hibernate.cache.hazelcast.use_lite_member", "true"); //does not contain any data
        properties.put("hibernate.dialect", this.dbProperties.getHibernateDialect());
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final LocalContainerEntityManagerFactoryBean emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }

    @Bean(name = "bootstrap")
    public ServerBootstrap getServiceLocator() {
        return new ServerBootstrap();
    }

}
