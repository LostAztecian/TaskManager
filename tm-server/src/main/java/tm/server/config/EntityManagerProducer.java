package tm.server.config;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import tm.server.entity.Project;
import tm.server.entity.Session;
import tm.server.entity.Task;
import tm.server.entity.User;
import tm.server.service.DatabasePropertyServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class EntityManagerProducer {

//    @PersistenceUnit @Autowired
    private EntityManagerFactory entityManagerFactory;

//    @Bean
    public EntityManager create() {
        return entityManagerFactory.createEntityManager();
    }

    public void close(EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

//    @NotNull @Bean
    public static EntityManagerFactory getEntityManagerFactory() {
        final tm.server.api.service.DatabasePropertyService propertyService = new DatabasePropertyServiceImpl();
        final Map<String, String> settings = new HashMap<>();

        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(Environment.URL, propertyService.getJdbcUrl());
        settings.put(Environment.USER, propertyService.getJdbcUsername());
        settings.put(Environment.PASS, propertyService.getJdbcPassword());
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.USE_SECOND_LEVEL_CACHE, "true");
        settings.put(Environment.USE_QUERY_CACHE, "true");
//        settings.put(Environment.USE_MINIMAL_PUTS, "true");
//        settings.put("hibernate.cache.hazelcast.use_lite_member", "true"); //does not contain any data
        settings.put(Environment.CACHE_REGION_PREFIX, "taskmanager-server");

        settings.put(Environment.CACHE_PROVIDER_CONFIG, "hazelcast.xml");
        settings.put(Environment.CACHE_REGION_FACTORY, "com.hazelcast.hibernate.HazelcastLocalCacheRegionFactory");

        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

}
