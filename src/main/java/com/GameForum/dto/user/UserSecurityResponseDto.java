package com.gameforum.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSecurityResponseDto {
    private String username;
    private String token;
}
