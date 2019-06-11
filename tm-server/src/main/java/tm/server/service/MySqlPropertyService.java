package tm.server.service;

import org.jetbrains.annotations.NotNull;
import tm.server.api.service.DatabasePropertyService;

public class MySqlPropertyService implements DatabasePropertyService {

    private final String username = "root";
    private final String password = "root";
    private final String url = "jdbc:mysql://localhost:3306/taskmanager?serverTimezone=UTC&useSSL=false";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    @Override
    public @NotNull String getJdbcUsername() {
        return username;
    }

    @Override
    public @NotNull String getJdbcPassword() {
        return password;
    }

    @Override
    public @NotNull String getJdbcUrl() {
        return url;
    }

    @Override
    public @NotNull String getJdbcDriver() {
        return driver;
    }
}
