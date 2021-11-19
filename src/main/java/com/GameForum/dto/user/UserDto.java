package com.gameforum.dto.user;

import com.gameforum.model.user.UserRole;
import com.gameforum.model.user.UserStatus;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserStatus userStatus;
    private UserRole userRole;
}
