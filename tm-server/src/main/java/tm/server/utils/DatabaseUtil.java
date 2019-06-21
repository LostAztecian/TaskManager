package tm.server.utils;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.server.entity.Project;
import tm.server.entity.Session;
import tm.server.entity.Task;
import tm.server.entity.User;
import tm.server.service.DatabasePropertyServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {

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
