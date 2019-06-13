package tm.server.repository.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import tm.common.entity.UserDTO;

import java.util.List;

public interface UserMapper {

    String FIND_ALL = "SELECT * FROM `user`";
    String FIND_BY_ID = "SELECT * FROM `user` WHERE `id` = #{id}";
    String FIND_BY_NAME = "SELECT * FROM `user` WHERE `login` = #{name}";
    String PERSIST = "INSERT INTO `user` (`id`, `login`, `pwdHash`, `role`) VALUES (#{id}, #{login}, #{passwordHash}, #{role})";
    String REMOVE_BY_ID = "DELETE FROM `user` WHERE `id` = #{id}";
    String REMOVE_BY_LOGIN = "DELETE FROM `user` WHERE `login` = #{login}";
    String CLEAR = "DELETE FROM `user`";
    String VALIDATE = "SELECT * FROM `user` WHERE `login` = #{login} AND `pwdHash` = #{pwdHash}";

    @Select(FIND_ALL)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "passwordHash", column = "pwdHash"),
            @Result(property = "role", column = "role")
    })
    List<UserDTO> findAll() throws Exception;

    @Select(FIND_BY_NAME)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "passwordHash", column = "pwdHash"),
            @Result(property = "role", column = "role")
    })
    List<UserDTO> findByName(@NotNull String name) throws Exception;

    @Select(FIND_BY_ID)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "passwordHash", column = "pwdHash"),
            @Result(property = "role", column = "role")
    })
    UserDTO findOne(@NotNull String id) throws Exception;

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "passwordHash", column = "pwdHash"),
            @Result(property = "role", column = "role")
    })
    @Select(VALIDATE)
    UserDTO validate(@NotNull @Param("login") String name, @NotNull @Param("pwdHash") String pwdHash) throws Exception;

    @Insert(PERSIST)
    void persist(@NotNull UserDTO user) throws Exception;

    @Delete(REMOVE_BY_ID)
    void remove(@NotNull String id) throws Exception;

    @Delete(REMOVE_BY_LOGIN)
    void removeByName(@NotNull String login);

    @Delete(CLEAR)
    void removeAll() throws Exception;

}
