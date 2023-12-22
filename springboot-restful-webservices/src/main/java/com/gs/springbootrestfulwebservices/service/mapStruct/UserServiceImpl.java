package com.gs.springbootrestfulwebservices.service.mapStruct;

import com.gs.springbootrestfulwebservices.dto.UserDto;
import com.gs.springbootrestfulwebservices.entity.User;
import com.gs.springbootrestfulwebservices.exception.EmailAlreadyExistException;
import com.gs.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.gs.springbootrestfulwebservices.mappers.UserStructMapper;
import com.gs.springbootrestfulwebservices.repository.UserRepository;
import com.gs.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service(value = "userServiceMapStruct")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByEmailId(userDto.getEmailId());
        if (optionalUser.isPresent())
            throw new EmailAlreadyExistException("Email already exist for the user "+userDto.getFirstName());
        User user =UserStructMapper.USER_STRUCT_MAPPER.mapToUser(userDto);
        User saveUser = userRepository.save(user);
        return    UserStructMapper.USER_STRUCT_MAPPER.mapToUserDTO(saveUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user  = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","Id",userId));
        return    UserStructMapper.USER_STRUCT_MAPPER.mapToUserDTO(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserStructMapper.USER_STRUCT_MAPPER.mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User existingUser = userRepository.findById(userDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User","Id",(userDto.getUserId())));
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmailId(userDto.getEmailId());
        User savedUser = userRepository.save(existingUser);
       return UserStructMapper.USER_STRUCT_MAPPER.mapToUserDTO(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user  = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","Id",userId));
        userRepository.deleteById(userId);
    }
}
