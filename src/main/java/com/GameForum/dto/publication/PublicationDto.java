package com.GameForum.dto.publication;

import com.GameForum.model.publication.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PublicationDto {
    private Long id;
    private CategoryDto category;
    private String title;
    private ContentDto content;
    private List<TagDto> tag;
    private PublicationStatus status;
}
