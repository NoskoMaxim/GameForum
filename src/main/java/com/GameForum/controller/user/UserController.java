package com.GameForum.controller.user;

import com.GameForum.config.gameforum.GameForumException;
import com.GameForum.dto.OperationMessageDto;
import com.GameForum.dto.user.UserDataDto;
import com.GameForum.dto.user.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/game-forum")
public class UserController {

    private final Map<String, UserDto> users = new HashMap<>();

    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity createUser(@RequestBody UserDto user) {
        saveUser(user);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }


    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity createUsers(@RequestBody List<UserDto> users) {
        users.forEach(this::saveUser);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @PostMapping(value = "/user/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity loginUser(@RequestBody UserDataDto userData) {
        Boolean isUserExistsAndPasswordMatches = Optional.ofNullable(this.users.get(userData.getUsername()))
                .map(user -> user.getUserData().getPassword().equals(userData.getPassword())).orElse(false);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Rate-Limit", "1800");
        headers.add("X-Expires-After", LocalDateTime.now().plusHours(1).toString());

        if (!isUserExistsAndPasswordMatches) {
            HashMap<String, String> failures = new HashMap<>();
            failures.put("username", "Nonexistent username");
            failures.put("password", "Invalid password");
            throw new GameForumException(failures);
        }

        return ResponseEntity.ok().headers(headers).body(new OperationMessageDto("Successful operation"));
    }

    @PostMapping(value = "/user/logout", consumes = "application/json", produces = "application/json")
    public ResponseEntity logoutUser(@RequestBody(required = false) UserDataDto userData) {
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @GetMapping(value = "/user/{username}", produces = "application/json")
    public ResponseEntity getUserByName(@PathVariable String username) {
        Optional<UserDto> foundUser = Optional.ofNullable(this.users.get(username));
        return foundUser.isPresent()
                ? ResponseEntity.ok(foundUser.get())
                : ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/user/{username}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateUser(@PathVariable String username, @RequestBody UserDto user){
        this.users.put(username,user);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @DeleteMapping(value = "/user/{username}", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable String username){
        UserDto user = this.users.remove(username);
        return ResponseEntity.ok(user);
    }

    private void saveUser(@RequestBody UserDto user) {
        user.setId((long) this.users.size());
        this.users.put(user.getUserData().getUsername(), user);
    }

}
