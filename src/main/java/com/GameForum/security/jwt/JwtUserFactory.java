package com.gameforum.security.jwt;

import com.gameforum.model.user.User;
import com.gameforum.model.user.UserRole;
import com.gameforum.model.user.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getUserStatus().equals(UserStatus.ONLINE),
                convertToGrantedAuthority(user.getUserRole())
        );
    }

    private static List<GrantedAuthority> convertToGrantedAuthority(UserRole userRole){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole.name()));
        return authorities;
    }
}
