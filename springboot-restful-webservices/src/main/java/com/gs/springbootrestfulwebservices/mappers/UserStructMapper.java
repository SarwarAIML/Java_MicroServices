package com.gs.springbootrestfulwebservices.mappers;

import com.gs.springbootrestfulwebservices.dto.UserDto;
import com.gs.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserStructMapper {
    UserStructMapper USER_STRUCT_MAPPER = Mappers.getMapper(UserStructMapper.class);
    UserDto mapToUserDTO(User user);
    User mapToUser(UserDto userDto);
}
