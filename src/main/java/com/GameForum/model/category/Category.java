package com.gameforum.model.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameforum.model.publication.Publication;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")

    private Long categoryId;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    List<Publication> publications = new ArrayList<>();

    public Category() {
    }

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}