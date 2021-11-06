package com.GameForum.dto.publication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContentDto {
    private String text;
    @JsonProperty("photoUrl")
    private List<String> photoUrl;
}
