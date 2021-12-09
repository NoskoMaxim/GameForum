package com.gameforum.controller.user;

import com.gameforum.config.gameforum.exception.gameforumexception.GameForumException;
import com.gameforum.dto.OperationMessageDto;
import com.gameforum.dto.user.UserSecurityDto;
import com.gameforum.dto.user.UserDto;
import com.gameforum.dto.user.UserSecurityResponseDto;
import com.gameforum.model.user.User;
import com.gameforum.model.user.UserRole;
import com.gameforum.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/game-forum/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @PostMapping(value = "/logout", consumes = "application/json", produces = "application/json")
    public ResponseEntity logoutUser(@RequestBody UserDto userDto) {
        userService.logoutUser(userDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }
}