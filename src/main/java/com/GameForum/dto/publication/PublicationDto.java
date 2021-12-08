package com.gameforum.dto.publication;

import com.gameforum.model.publication.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PublicationDto {
    private Long publicationId;
    private Long userId;
    private String username;
    private String title;
    private String content;
    private byte[] photo;
    private String categoryName;
    private Long categoryId;
    private PublicationStatus status;
    private LocalDateTime shipDate;
    private Long likes;
}
