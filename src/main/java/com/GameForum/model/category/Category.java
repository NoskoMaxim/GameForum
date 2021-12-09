package com.gameforum.model.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameforum.model.publication.Publication;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category", schema = "public")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @PrimaryKeyJoinColumn
    private Long categoryId;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "publicationCategory", fetch = FetchType.LAZY)
    List<Publication> publications = new ArrayList<>();

    public Category() {
    }

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}