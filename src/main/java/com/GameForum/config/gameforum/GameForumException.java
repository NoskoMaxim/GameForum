package com.GameForum.config.gameforum;

import lombok.Getter;

import java.util.Map;

public class GameForumException extends RuntimeException {

    @Getter
    private Map<String,String> failures;
    public GameForumException(Map<String, String> failures) {
        this.failures = failures;
    }
}
