package tm.server.utils;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.server.api.service.DatabasePropertyService;
import tm.server.graph.Project;
import tm.server.graph.Session;
import tm.server.graph.Task;
import tm.server.graph.User;
import tm.server.repository.mybatis.mapper.ProjectMapper;
import tm.server.repository.mybatis.mapper.SessionMapper;
import tm.server.repository.mybatis.mapper.TaskMapper;
import tm.server.repository.mybatis.mapper.UserMapper;
import tm.server.service.MySqlPropertyService;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseConnectionUtil {

    @Nullable
    public static Connection getJDBCConnection() {
        Connection connection = null;
        try (InputStream input = DatabaseConnectionUtil.class.getClassLoader().getResourceAsStream("mysql-database.properties")){
            Properties prop = new Properties();
            if (input == null) return null;
            prop.load(input);
            Class.forName(prop.getProperty("connector.class"));
            connection = DriverManager.getConnection(
                    prop.getProperty("database.url"),
                    prop.getProperty("database.login"),
                    prop.getProperty("database.password"));

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("[DATABASE CONNECTION ESTABLISHED]");
        return connection;
    }

    @NotNull
    public static SqlSessionFactory getSessionFactory() throws Exception {
//        final String resource = "mybatis-config.xml";
//        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
//            return new SqlSessionFactoryBuilder().build(inputStream);
//        }
        final MySqlPropertyService propertyService = new MySqlPropertyService();
        @Nullable final String user = propertyService.getJdbcUsername();
        @Nullable final String password = propertyService.getJdbcPassword();
        @Nullable final String url = propertyService.getJdbcUrl();
        @Nullable final String driver = propertyService.getJdbcDriver();
        final DataSource dataSource =
                new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        final org.apache.ibatis.mapping.Environment environment =
                new org.apache.ibatis.mapping.Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        configuration.addMapper(ProjectMapper.class);
        configuration.addMapper(SessionMapper.class);
        configuration.addMapper(TaskMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);

    }

    @NotNull
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

}
