package com.gameforum.security.jwtservice;

import com.gameforum.model.user.User;
import com.gameforum.security.jwt.JwtUser;
import com.gameforum.security.jwt.JwtUserFactory;
import com.gameforum.service.user.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isEmpty()){
            System.out.println("User with username: " + username + "not found");
            throw new UsernameNotFoundException("User with username: " + username + "not found");
        }
        return JwtUserFactory.create(user.get());
    }
}
