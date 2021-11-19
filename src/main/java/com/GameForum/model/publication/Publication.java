package com.gameforum.model.publication;

import com.gameforum.model.category.Category;
import com.gameforum.model.tag.Tag;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publication")
@Data
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

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
    private StringBuilder content;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToMany
    @JoinTable(name = "publication_category",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "publication_tag",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    Set<Tag> tags = new HashSet<>();

    public Publication() {
    }
}
