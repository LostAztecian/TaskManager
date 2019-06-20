package tm.server;

import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import tm.server.api.service.DatabasePropertyService;
import tm.server.entity.Project;
import tm.server.entity.Session;
import tm.server.entity.Task;
import tm.server.entity.User;
import tm.server.service.DatabasePropertyServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class EntityManagerProducer {

    @NotNull
    private static final String UNIT_NAME = "ENTERPRISE";

    @Inject
    @PersistenceUnitName(UNIT_NAME)
    private EntityManagerFactory entityManagerFactory;

    @NotNull
    @Produces
    @Alternative
    @TransactionScoped
    public EntityManager create() {
        return this.entityManagerFactory.createEntityManager();
    }

    public void dispose(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) entityManager.close();
    }

    @NotNull
    @Produces
    @Alternative
    @ApplicationScoped
    public static EntityManagerFactory getEntityManagerFactory() {
        final DatabasePropertyService propertyService = new DatabasePropertyServiceImpl();
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(Environment.URL, propertyService.getJdbcUrl());
        settings.put(Environment.USER, propertyService.getJdbcUsername());
        settings.put(Environment.PASS, propertyService.getJdbcPassword());
        settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
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

