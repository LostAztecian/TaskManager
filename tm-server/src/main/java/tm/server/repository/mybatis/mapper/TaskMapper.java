package tm.server.repository.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.Task;

import java.util.List;

public interface TaskMapper {

    String FIND_ALL  = "SELECT * FROM `task`";
    String FIND_ALL_SORTED  = "SELECT * FROM `task` ORDER BY #{sortMethod}";
    String FIND_BY_USER_ID  = "SELECT * FROM `task` WHERE `userId` = #{userId}";
    String FIND_BY_PROJECT_ID  = "SELECT * FROM `task` WHERE `projectId` = #{projectId}";
    String FIND_BY_NAME  = "SELECT * FROM `task` WHERE `userId` = #{userId} AND `name` = #{name}";
    String FIND_BY_NAME_SORTED  = "SELECT * FROM `task` WHERE `userId` = #{userId} AND `name` = #{name} ORDER BY #{sortMethod}";
    String FIND_BY_ID  = "SELECT * FROM `task` WHERE `id` = #{id}";
    String PERSIST  = "INSERT INTO `task` (`id`, `userId`, `projectId`, `status`, `name`, `description`, `creationDate`, `startDate`, `endDate`) " +
            "VALUES (#{id}, #{userId}, #{projectId}, #{status}, #{name}, #{description}, #{creationDate}, #{startDate}, #{endDate})";
    String REMOVE_BY_ID  = "DELETE FROM `task` WHERE `id` = #{id}";
    String REMOVE_BY_PROJECT_ID  = "DELETE FROM `task` WHERE `projectId` = #{projectId}";
    String REMOVE_BY_NAME  = "DELETE FROM `task`, WHERE `userId` = #{userId} AND `name` = #{name}";
    String REMOVE_ALL  = "DELETE FROM `task`, WHERE `userId` = #{userId}";

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "status", column = "status"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "creationDate", column = "creationDate"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
    })
    @Select(FIND_ALL)
    List<Task> findAll() throws Exception;

    @Select(FIND_ALL_SORTED)
    List<Task> findByUserId(@NotNull String userId) throws Exception;

    @Select(FIND_BY_USER_ID)
    List<Task> findAllAndSort(@NotNull String sortMethod) throws Exception;

    @Select(FIND_BY_PROJECT_ID)
    List<Task> findByProjectId(@NotNull String projectId) throws Exception;

    @Select(FIND_BY_NAME)
    List<Task> findByName(@NotNull @Param("userId") String userId, @NotNull @Param("name") String name) throws Exception;

    @Select(FIND_BY_NAME_SORTED)
    List<Task> findByNameAndSort(@NotNull @Param("userId") String userId, @NotNull @Param("name") String name, @NotNull @Param("sortMethod") String sortMethod) throws Exception;

    @Select(FIND_BY_ID)
    Task findOne(@NotNull String id) throws Exception;

    @Insert(PERSIST)
    void persist(@NotNull Task project) throws Exception;

    @Delete(REMOVE_BY_ID)
    void removeById(@NotNull String id) throws Exception;

    @Delete(REMOVE_BY_PROJECT_ID)
    void removeByProjectId(@NotNull String projectId) throws Exception;

    @Delete(REMOVE_BY_NAME)
    void removeByName(@NotNull @Param("userId") String userId, @NotNull @Param("name") String name) throws Exception;

    @Delete(REMOVE_ALL)
    void clear(@NotNull String userId) throws Exception;
    
}
