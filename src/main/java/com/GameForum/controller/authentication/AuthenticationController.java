package com.gameforum.controller.authentication;

import com.gameforum.config.gameforum.exception.gameforumexception.GameForumException;
import com.gameforum.dto.OperationMessageDto;
import com.gameforum.dto.user.UserDto;
import com.gameforum.dto.user.UserSecurityDto;
import com.gameforum.dto.user.UserSecurityResponseDto;
import com.gameforum.model.user.User;
import com.gameforum.model.user.UserRole;
import com.gameforum.security.jwt.JwtTokenProvider;
import com.gameforum.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/game-forum/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity loginUser(@RequestBody UserSecurityDto userSecurityDto) {
        try {
            String username = userSecurityDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,userSecurityDto.getPassword()));
            Optional<User> user = userService.findUserByUsername(username);
            if (user.isEmpty()) {
                Map<String, String> failures = new HashMap<>();
                failures.put("username", "Nonexistent username");
                failures.put("password", "Invalid password");
                throw new GameForumException(failures);
            }
            List<UserRole> userRoleAsList = new ArrayList<>();
            userRoleAsList.add(user.get().getUserRole());
            String token = jwtTokenProvider.createToken(username,userRoleAsList);
            UserSecurityResponseDto userSecurityResponseDto = new UserSecurityResponseDto();
            userSecurityResponseDto.setUsername(username);
            userSecurityResponseDto.setToken(token);
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Rate-Limit", "1800");
            headers.add("X-Expires-After", LocalDateTime.now().plusHours(1).toString());
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(userSecurityResponseDto);
        }catch (AuthenticationException authenticationException){
            Map<String, String> failures = new HashMap<>();
            failures.put("username", "Nonexistent username");
            failures.put("password", "Invalid password");
            throw new GameForumException(failures);
        }
    }

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody UserSecurityDto userSecurityDto) {
        userService.registerUser(userSecurityDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }
}
