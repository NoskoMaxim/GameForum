package com.gameforum.controller.user;

import com.gameforum.config.gameforum.GameForumException;
import com.gameforum.dto.OperationMessageDto;
import com.gameforum.dto.user.UserSecurityDto;
import com.gameforum.dto.user.UserDto;
import com.gameforum.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game-forum/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody UserSecurityDto userSecurityDto) {
        userService.createUser(userSecurityDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @PutMapping(value = "/update-user",
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

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity loginUser(@RequestBody UserSecurityDto userSecurityDto) {
        Boolean isUserExistsAndPasswordMatches = userService.loginUser(userSecurityDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Rate-Limit", "1800");
        headers.add("X-Expires-After", LocalDateTime.now().plusHours(1).toString());

        if (!isUserExistsAndPasswordMatches) {
            Map<String, String> failures = new HashMap<>();
            failures.put("username", "Nonexistent username");
            failures.put("password", "Invalid password");
            throw new GameForumException(failures);
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(new OperationMessageDto("Successful operation"));
    }

    @PostMapping(value = "/logout", consumes = "application/json", produces = "application/json")
    public ResponseEntity logoutUser(@RequestBody UserDto userDto) {
        userService.logoutUser(userDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }
}