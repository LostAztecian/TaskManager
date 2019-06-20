package tm.server.utils;

import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.server.api.service.DatabasePropertyService;
import tm.server.entity.Project;
import tm.server.entity.Session;
import tm.server.entity.Task;
import tm.server.entity.User;
import tm.server.service.MySqlPropertyService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {

    @NotNull
    @Produces
    @ApplicationScoped
    public static EntityManagerFactory getEntityManagerFactory() {
        final DatabasePropertyService propertyService = new MySqlPropertyService();
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(Environment.URL, propertyService.getJdbcUrl());
        settings.put(Environment.USER, propertyService.getJdbcUsername());
        settings.put(Environment.PASS, propertyService.getJdbcPassword());
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
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

    @NotNull
    public static String getSortColumn(@Nullable final ComparatorType comparatorType) {
        if (comparatorType == null) return "creationDate";
        switch (comparatorType) {
            case BY_STATUS: {
                return "status";
            }
            case BY_START_DATE: {
                return "startDate";
            }
            case BY_END_DATE: {
                return "endDate";
            }
            case BY_CREATION_DATE: {
                return "creationDate";
            }
        }
        return "creationDate";
    }

}
