package com.gs.springbootrestfulwebservices.mappers;

import com.gs.springbootrestfulwebservices.dto.UserDto;
import com.gs.springbootrestfulwebservices.entity.User;

public class UserMapper {

    //convert User entity to userDto

    public static UserDto maptoUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmailId()
        );
        return userDto;
    }

    //convert dto to entity
    public static User maptoUserEntity(UserDto userDto) {
        User user = new User(userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmailId());
        return user;
    }
}
