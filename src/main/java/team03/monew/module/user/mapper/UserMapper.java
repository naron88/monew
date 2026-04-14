package team03.monew.module.user.mapper;

import org.mapstruct.Mapper;
import team03.monew.module.user.dto.UserDto;
import team03.monew.module.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toDto(User user);
}

