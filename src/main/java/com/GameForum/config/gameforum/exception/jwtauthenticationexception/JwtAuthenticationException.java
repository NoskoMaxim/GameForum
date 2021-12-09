package com.gameforum.config.gameforum.exception.jwtauthenticationexception;

import com.gameforum.config.gameforum.exception.gameforumexception.GameForumException;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationException extends AuthenticationException {


    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);

    }

    public JwtAuthenticationException(String msg) {
        super(msg);
        Map<String, String> failures = new HashMap<>();
        failures.put("JWT",msg);
        throw new GameForumException(failures);
    }
}
