package com.gs.springbootrestfulwebservices.controller;


import com.gs.springbootrestfulwebservices.dto.UserDto;
import com.gs.springbootrestfulwebservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/users")
@RestController
public class UserController {


    @Autowired(required = true)
    @Qualifier("userServiceMapStruct")
    UserService userService;
    // http://localhost:8080/api/users/create
    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user1 = userService.createUser(userDto);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    // http://localhost:8080/api/users/10
    @GetMapping("{id}")
    public ResponseEntity<UserDto> findByUserId(@PathVariable("id") Long userId){
        UserDto userById = userService.getUserById(userId);
        return ResponseEntity.ok(userById);
    }
   // http://localhost:808/api/users/
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
    // http://localhost:808/api/users/1/update
    @PutMapping("{id}/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Long userId){
        userDto.setUserId(userId);
        UserDto existingUser = userService.updateUser(userDto);
        return ResponseEntity.ok(existingUser);
    }
    // http://localhost:808/api/users/1/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted Successfully::"+userId);
    }

 /*
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException exception,
                                                                  WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    */

}
