package com.gs.springbootrestfulwebservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    Long userId;

    // User first name should not be null or empty
    @NotEmpty(message = "User first name should not be null or empty")
    String firstName ;

    @NotEmpty(message = "User last name should not be null or empty")
    String lastName ;

    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address should be valid")
    String emailId;
}
