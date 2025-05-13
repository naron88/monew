package com.monew.global.util.mapper.user;

import com.monew.domain.user.dto.UserDto;
import com.monew.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toDto(User user);
}

