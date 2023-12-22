package com.gs.springbootrestfulwebservices.service.simpleMapper.impl;

import com.gs.springbootrestfulwebservices.dto.UserDto;
import com.gs.springbootrestfulwebservices.entity.User;
import com.gs.springbootrestfulwebservices.mappers.UserMapper;
import com.gs.springbootrestfulwebservices.repository.UserRepository;
import com.gs.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service(value = "UserService")
@Qualifier("UserService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.maptoUserEntity(userDto);
        User saveUser = userRepository.save(user);
        UserDto savedUserDto = UserMapper.maptoUserDto(saveUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user  = userRepository.findById(userId).get();
        return  UserMapper.maptoUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::maptoUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getUserId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmailId(userDto.getEmailId());
        User savedUser = userRepository.save(existingUser);
        return UserMapper.maptoUserDto(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
