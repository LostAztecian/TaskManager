package tm.server.repository.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.SessionDTO;

import java.util.List;

public interface SessionMapper {

    String FIND_ALL = "SELECT * FROM `session`";
    String FIND_BY_ID = "SELECT * FROM `session` WHERE `id` = #{id}";
    String FIND_BY_USER_ID = "SELECT * FROM `session` WHERE `userId` = #{userId}";
    String PERSIST = "INSERT INTO `session` (`id`, `userId`, `userLogin`, `creationDate`, `sortMethod`, `hash`) " +
            "VALUES (#{id}, #{userId}, #{userLogin}, #{creationDate}, #{sortMethod}, #{hash})";
    String REMOVE_BY_ID = "DELETE FROM `session` WHERE `id` = #{id}";
    String REMOVE_BY_USER_ID = "DELETE FROM `session` WHERE `userId` = #{userId}";
    String CLEAR = "DELETE FROM `session`";

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userLogin", column = "userLogin"),
            @Result(property = "creationDate", column = "creationDate"),
            @Result(property = "sortMethod", column = "sortMethod"),
            @Result(property = "hash", column = "hash")
    })
    @Select(FIND_ALL)
    List<SessionDTO> findAll() throws Exception;

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userLogin", column = "userLogin"),
            @Result(property = "creationDate", column = "creationDate"),
            @Result(property = "sortMethod", column = "sortMethod"),
            @Result(property = "hash", column = "hash")
    })
    @Select(FIND_BY_USER_ID)
    List<SessionDTO> findByUserId(@NotNull String userId) throws Exception;

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userLogin", column = "userLogin"),
            @Result(property = "creationDate", column = "creationDate"),
            @Result(property = "sortMethod", column = "sortMethod"),
            @Result(property = "hash", column = "hash")
    })
    @Select(FIND_BY_ID)
    SessionDTO findById(@NotNull String id) throws Exception;

    @Insert(PERSIST)
    void persist(@NotNull SessionDTO session) throws Exception;

    @Delete(REMOVE_BY_ID)
    void removeById(@NotNull String id) throws Exception;

    @Delete(REMOVE_BY_USER_ID)
    void removeByUserId(@NotNull String userId) throws Exception;

    @Delete(CLEAR)
    void removeAll() throws Exception;

}
