package tm.server.repository.mysql;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.Status;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Project;
import tm.server.api.repository.ProjectRepository;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ProjectRepositoryMySQL implements ProjectRepository {

    final Connection connection;

    public ProjectRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @NotNull
    private Project fetch(@NotNull final ResultSet resultSet) throws SQLException {
        final Project project = new Project();
        project.setId(resultSet.getString("id"));
        project.setUserId(resultSet.getString("userId"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        project.setCreationDate(resultSet.getDate("creationDate"));
        project.setStartDate(resultSet.getDate("startDate"));
        project.setEndDate(resultSet.getDate("endDate"));
        project.setStatus(Status.valueOf(resultSet.getString("status")));
        return project;
    }

    @Override @NotNull
    public Collection<Project> findAll(@NotNull final String userId) {
        final Collection<Project> projects = new HashSet<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `project` WHERE `userId` = ?");
            statement.setString(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override @NotNull
    public Collection<Project> findAllAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType) {
        final Collection<Project> projects = new LinkedHashSet<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `project` WHERE `userId` = ? ORDER BY ?");
            statement.setString(1, userId);
            switch (comparatorType) {
                case BY_STATUS: {
                    statement.setString(2, "status");
                    break;
                }
                case BY_END_DATE: {
                    statement.setString(2, "endDate");
                    break;
                }
                case BY_START_DATE: {
                    statement.setString(2, "startDate");
                    break;
                }
                default: {
                    statement.setString(2, "creationDate");
                }
            }
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override @NotNull
    public Collection<Project> findByName(@NotNull final String userId, @NotNull final String name) {
        final Collection<Project> projects = new HashSet<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `project` WHERE `userId` = ? AND `name` = ?");
            statement.setString(1, userId);
            statement.setString(2, name);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override @NotNull
    public Collection<Project> findByNameAndSort(@NotNull final String userId, @NotNull final ComparatorType comparatorType, @NotNull final String name) {
        final Collection<Project> projects = new LinkedHashSet<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `project` WHERE `userId` = ? AND `name` = ? ORDER BY ?");
            statement.setString(1, userId);
            statement.setString(2, name);
            switch (comparatorType) {
                case BY_STATUS: {
                    statement.setString(3, "status");
                    break;
                }
                case BY_END_DATE: {
                    statement.setString(3, "endDate");
                    break;
                }
                case BY_START_DATE: {
                    statement.setString(3, "startDate");
                    break;
                }
                default: {
                    statement.setString(3, "creationDate");
                }
            }
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override @NotNull
    public Collection<Project> search(@NotNull final String userId, @NotNull final String searchLine) {
        final Collection<Project> projects = new HashSet<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `project` WHERE (`userId` = ?) AND (`name` LIKE %?% OR `description` LIKE %?%)");
            statement.setString(1, userId);
            statement.setString(2, searchLine);
            statement.setString(3, searchLine);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override @Nullable
    public Project findOne(@NotNull final String userId, @NotNull final String id) {
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `project` WHERE `userId` = ? AND `id` = ? LIMIT 1");
            statement.setString(1, userId);
            statement.setString(2, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fetch(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override @NotNull
    public Boolean persist(@NotNull final Project project) {
        try {
            final PreparedStatement statement = connection.prepareStatement("INSERT IGNORE INTO `project` " +
                    "(`id`, `userId`, `name`, `description`, `creationDate`, `startDate`, `endDate`, `status`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, project.getId());
            statement.setString(2, project.getUserId());
            statement.setString(3, project.getName());
            statement.setString(4, project.getDescription());
            statement.setTimestamp(5, new Timestamp(project.getCreationDate().getTime()), Calendar.getInstance());
            statement.setTimestamp(6, new Timestamp(Optional.ofNullable(project.getStartDate()).orElse(new Date()).getTime()), Calendar.getInstance());
            statement.setTimestamp(7, new Timestamp(Optional.ofNullable(project.getEndDate()).orElse(new Date()).getTime()), Calendar.getInstance());
            statement.setString(8, project.getStatus().toString());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override @NotNull
    public Boolean merge(@NotNull final String userId, @NotNull final Project project) {
        try {
            final PreparedStatement statement = connection.prepareStatement("INSERT REPLACE INTO `project` " +
                    "(`id`, `userId`, `name`, `description`, `creationDate`, `startDate`, `endDate`, `status`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, project.getId());
            statement.setString(2, project.getUserId());
            statement.setString(3, project.getName());
            statement.setString(4, project.getDescription());
            statement.setTimestamp(5, new Timestamp(project.getCreationDate().getTime()), Calendar.getInstance());
            statement.setTimestamp(6, new Timestamp(Optional.ofNullable(project.getStartDate()).orElse(new Date()).getTime()), Calendar.getInstance());
            statement.setTimestamp(7, new Timestamp(Optional.ofNullable(project.getEndDate()).orElse(new Date()).getTime()), Calendar.getInstance());
            statement.setString(8, project.getStatus().toString());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final String id) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `project` WHERE `userId` = ? AND `id` = ?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, id);
            if (preparedStatement.executeUpdate() > 0) return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override @Nullable
    public String remove(@NotNull final String userId, @NotNull final Project project) {
        return remove(userId, project.getId());
    }

    @Override @NotNull
    public Collection<String> removeByName(@NotNull final String userId, @NotNull final String name) {
        final Collection<String> ids = new HashSet<>();
        try {
            final PreparedStatement getIdsStatement = connection.prepareStatement("SELECT `id` FROM `project` WHERE `userId` = ? AND `name` = ?");
            final PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM `project` WHERE `userId` = ? AND `name` = ?");
            getIdsStatement.setString(1, userId);
            getIdsStatement.setString(2, name);
            removeStatement.setString(1, userId);
            removeStatement.setString(2, name);
            final ResultSet resultSet = getIdsStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getString("id"));
            }
            removeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    @Override @NotNull
    public Collection<String> removeAll(@NotNull final String userId) {
        final Collection<String> ids = new HashSet<>();
        try {
            final PreparedStatement getIdsStatement = connection.prepareStatement("SELECT `id` FROM `project` WHERE `userId` = ?");
            final PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM `project` WHERE `userId` = ?");
            getIdsStatement.setString(1, userId);
            removeStatement.setString(1, userId);
            final ResultSet resultSet = getIdsStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getString("id"));
            }
            removeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

}
