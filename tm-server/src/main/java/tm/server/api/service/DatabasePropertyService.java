package tm.server.api.service;

import org.jetbrains.annotations.NotNull;

public interface DatabasePropertyService {

    @NotNull String getJdbcUsername();
    @NotNull String getJdbcPassword();
    @NotNull String getJdbcUrl();
    @NotNull String getJdbcDriver();
    @NotNull String getHibernateDialect();

}
