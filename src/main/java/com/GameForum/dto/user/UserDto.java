package com.GameForum.dto.user;

import com.GameForum.model.user.UserStatus;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private UserDataDto userData;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserStatus userStatus;
}
