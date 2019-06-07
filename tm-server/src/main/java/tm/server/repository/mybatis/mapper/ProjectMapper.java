package tm.server.repository.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.Project;

import java.util.List;

public interface ProjectMapper {

    String FIND_ALL  = "SELECT * FROM `project`";
    String FIND_ALL_SORTED  = "SELECT * FROM `project` WHERE `userId` = #{userId} ORDER BY #{sortMethod}";
    String FIND_BY_USER_ID  = "SELECT * FROM `project` WHERE `userId` = #{userId}";
    String FIND_BY_NAME  = "SELECT * FROM `project` WHERE `userId` = #{userId} AND `name` = #{name}";
    String FIND_BY_NAME_SORTED  = "SELECT * FROM `project` WHERE `userId` = #{userId} AND `name` = #{name} ORDER BY #{sortMethod}";
    String FIND_BY_ID  = "SELECT * FROM `project` WHERE `userId` = #{userId} AND `id` = #{id}";
    String SEARCH = "SELECT * FROM `project` WHERE ( `userId` = #{userId} ) AND ( `name` LIKE CONCAT(\"%\", #{line}, \"%\") OR `description` LIKE CONCAT(\"%\", #{line}, \"%\") )";
    String PERSIST  = "INSERT INTO `project` (`id`, `userId`, `status`, `name`, `description`, `creationDate`, `startDate`, `endDate`) " +
            "VALUES (#{id}, #{userId}, #{status}, #{name}, #{description}, #{creationDate}, #{startDate}, #{endDate})";
    String REMOVE_BY_ID  = "DELETE FROM `project` WHERE `userId` = #{userId} AND `id` = #{id}";
    String REMOVE_BY_NAME  = "DELETE FROM `project`, WHERE `userId` = #{userId} AND `name` = #{name}";
    String REMOVE_ALL  = "DELETE FROM `project`, WHERE `userId` = #{userId}";

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
    List<Project> findAll() throws Exception;

    @Select(FIND_ALL_SORTED)
    List<Project> findAllAndSort(@NotNull @Param("userId") String userId, @NotNull @Param("sortMethod") String sortMethod);

    @Select(FIND_BY_USER_ID)
    List<Project> findByUserId(@NotNull String userId) throws Exception;

    @Select(FIND_BY_NAME)
    List<Project> findByName(@NotNull @Param("userId") String userId, @NotNull @Param("name") String name) throws Exception;

    @Select(FIND_BY_NAME_SORTED)
    List<Project> findByNameAndSort(@NotNull @Param("userId") String userId, @NotNull @Param("name") String name, @NotNull @Param("sortMethod") String sortMethod) throws Exception;

    @Select(FIND_BY_ID)
    Project findOne(@NotNull @Param("userId") String userId, @NotNull @Param("id") String id) throws Exception;

    @Select(SEARCH)
    List<Project> search(@NotNull @Param("userId") String userId, @NotNull @Param("line") String line) throws Exception;

    @Insert(PERSIST)
    void persist(@NotNull Project project) throws Exception;

    @Delete(REMOVE_BY_ID)
    void removeById(@NotNull @Param("userId") String userId, @NotNull @Param("id") String id) throws Exception;

    @Delete(REMOVE_BY_NAME)
    void removeByName(@NotNull @Param("userId") String userId, @NotNull @Param("name") String name) throws Exception;

    @Delete(REMOVE_ALL)
    void clear(@NotNull String userId) throws Exception;

}
