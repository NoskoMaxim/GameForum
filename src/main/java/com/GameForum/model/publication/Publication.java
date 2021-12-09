package com.gameforum.model.publication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameforum.model.category.Category;
import com.gameforum.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publication", schema = "public")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publication_id")
    private Long publicationId;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PublicationStatus status;

    @Column(name = "ship_date")
    private LocalDateTime shipDate;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "content")
    private String content;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false, insertable = false, updatable = false)
    private Category publicationCategory;

    public Publication() {
    }
}
