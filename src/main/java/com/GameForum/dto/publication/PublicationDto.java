package com.GameForum.dto.publication;

import com.GameForum.model.publication.PublicationStatus;
import lombok.Data;

import java.util.List;

@Data
public class PublicationDto {
    private Long id;
    private CategoryDto category;
    private String title;
    private String content;
    private List<String> photoUrl;
    private List<TagDto> tag;
    private PublicationStatus status;
}
