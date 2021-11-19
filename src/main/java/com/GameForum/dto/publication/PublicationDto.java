package com.gameforum.dto.publication;

import com.gameforum.model.publication.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PublicationDto {
    private Long id;
    private Long userId;
    private String title;
    private StringBuilder content;
    private String photoUrl;
    private Set<TagDto> tags = new HashSet<>();
    private Set<CategoryDto> categories = new HashSet<>();
    private PublicationStatus status;
    private LocalDateTime shipDate;
    private Long likes;
}
