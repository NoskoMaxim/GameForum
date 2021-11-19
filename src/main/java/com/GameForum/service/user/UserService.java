package com.gameforum.service.user;

import com.gameforum.dto.user.UserDto;
import com.gameforum.dto.user.UserSecurityDto;
import com.gameforum.model.user.User;
import com.gameforum.model.user.UserRole;
import com.gameforum.model.user.UserStatus;
import com.gameforum.repository.user.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepos userRepos;

    @Autowired
    public UserService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    public void createUser(UserSecurityDto userSecurityDto) {
        User user = new User();
        user.setUserStatus(UserStatus.ONLINE);
        user.setUserRole(UserRole.USER);
        user.setUsername(userSecurityDto.getUsername());
        user.setPassword(userSecurityDto.getPassword());
        userRepos.save(user);
    }

    public void updateUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUserStatus(userDto.getUserStatus());
        user.setUserRole((userDto.getUserRole()));
        userRepos.save(user);
    }

    public void deleteUser(String username) {
        userRepos.deleteUserByUsername(username);
    }

    public Boolean loginUser(UserSecurityDto userSecurityDto) {
        Boolean isUserExistsAndPasswordMatches = true;
        Optional<User> user = userRepos.findUserByUsername(userSecurityDto.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(userSecurityDto.getPassword())) {
            user.get().setUserStatus(UserStatus.ONLINE);
            userRepos.save(user.get());
        } else {
            isUserExistsAndPasswordMatches = false;
        }
        return isUserExistsAndPasswordMatches;
    }

    public void logoutUser(UserDto userDto) {
        Optional<User> user = userRepos.findById(userDto.getId());
        user.ifPresent(user1 -> {
            user1.setUserStatus(UserStatus.OFFLINE);
            userRepos.save(user1);
        });
    }
}
