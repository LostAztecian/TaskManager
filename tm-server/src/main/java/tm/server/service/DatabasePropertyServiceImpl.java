package tm.server.service;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabasePropertyServiceImpl implements tm.server.api.service.DatabasePropertyService {

    private String username = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/taskmanager?serverTimezone=UTC&useSSL=false";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public DatabasePropertyServiceImpl() {
        try (InputStream input = DatabasePropertyServiceImpl.class.getResourceAsStream("mysql-database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            username = prop.getProperty("database.login");
            password = prop.getProperty("database.password");
            url = prop.getProperty("database.url");
            driver = prop.getProperty("connector.class");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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
