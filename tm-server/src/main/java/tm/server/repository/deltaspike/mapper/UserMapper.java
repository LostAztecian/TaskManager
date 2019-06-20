package tm.server.repository.deltaspike.mapper;

import org.apache.deltaspike.data.api.mapping.SimpleQueryInOutMapperBase;
import tm.common.entity.UserDTO;
import tm.server.entity.User;

public class UserMapper extends SimpleQueryInOutMapperBase<User, UserDTO> {

    @Override
    protected Object getPrimaryKey(UserDTO userDTO) {
        return userDTO.getId();
    }

    @Override
    protected UserDTO toDto(User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPasswordHash(user.getPasswordHash());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Override
    protected User toEntity(User user, UserDTO userDTO) {
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPasswordHash(userDTO.getPasswordHash());
        user.setRole(userDTO.getRole());
        return user;
    }

}
