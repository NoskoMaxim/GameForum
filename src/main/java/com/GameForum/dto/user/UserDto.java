package com.gameforum.dto.user;

import com.gameforum.model.user.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
}
