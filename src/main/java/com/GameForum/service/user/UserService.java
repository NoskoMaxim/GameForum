package com.gameforum.service.user;

import com.gameforum.dto.user.UserDto;
import com.gameforum.dto.user.UserSecurityDto;
import com.gameforum.model.user.User;
import com.gameforum.model.user.UserRole;
import com.gameforum.model.user.UserStatus;
import com.gameforum.repository.user.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {

    private final UserRepos userRepos;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepos userRepos, BCryptPasswordEncoder passwordEncoder) {
        this.userRepos = userRepos;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserSecurityDto userSecurityDto) {
        User user = new User();
        user.setUserRole(UserRole.USER);
        user.setUsername(userSecurityDto.getUsername());
        user.setPassword(passwordEncoder.encode(userSecurityDto.getPassword()));
        user.setUserStatus(UserStatus.ONLINE);
        userRepos.save(user);
    }

    public void updateUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUserRole((userDto.getUserRole()));
        userRepos.save(user);
    }

    public void deleteUser(String username) {
        userRepos.deleteUserByUsername(username);
    }

    public void logoutUser(UserDto userDto) {
        Optional<User> user = userRepos.findById(userDto.getUserId());
        user.ifPresent(userRepos::save);
    }

    public Optional<User> findUserByUsername(String username){
        return userRepos.findUserByUsername(username);
    }
}
