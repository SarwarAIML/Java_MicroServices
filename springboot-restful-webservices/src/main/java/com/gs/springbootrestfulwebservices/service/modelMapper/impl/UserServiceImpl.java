package com.gs.springbootrestfulwebservices.service.modelMapper.impl;

import com.gs.springbootrestfulwebservices.dto.UserDto;
import com.gs.springbootrestfulwebservices.entity.User;
import com.gs.springbootrestfulwebservices.mappers.UserMapper;
import com.gs.springbootrestfulwebservices.repository.UserRepository;
import com.gs.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;
import java.util.stream.Collectors;


@Service(value = "UserServiceModelMapper")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User saveUser = userRepository.save(user);
        return  modelMapper.map(saveUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user  = userRepository.findById(userId).get();
        return   modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getUserId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmailId(userDto.getEmailId());
        User savedUser = userRepository.save(existingUser);
       return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
