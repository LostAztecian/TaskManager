package tm.server.configuration;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabasePropertyServiceImpl implements tm.server.api.service.DatabasePropertyService {

    private String username = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/taskmanager?serverTimezone=UTC&useSSL=false";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String hibernateDialect = "org.hibernate.dialect.MySQL8Dialect";

    public DatabasePropertyServiceImpl() {
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("mysql-database.properties")) {
//            Properties prop = new Properties();
//            if (input == null) {
//                return;
//            }
//            //load a properties file from class path, inside static method
//            prop.load(input);
//            //get the property value and print it out
//            username = prop.getProperty("database.login");
//            password = prop.getProperty("database.password");
//            url = prop.getProperty("database.url");
//            driver = prop.getProperty("connector.class");
//            hibernateDialect = prop.getProperty("hibernate.dialect");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    @Override @NotNull
    public String getJdbcUsername() {
        return username;
    }

    @Override @NotNull
    public String getJdbcPassword() {
        return password;
    }

    @Override @NotNull
    public String getJdbcUrl() {
        return url;
    }

    @Override @NotNull
    public String getJdbcDriver() {
        return driver;
    }

    @Override
    public @NotNull String getHibernateDialect() { return hibernateDialect; }
}
