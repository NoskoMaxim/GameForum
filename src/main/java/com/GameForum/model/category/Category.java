package com.gameforum.model.category;

import com.gameforum.model.publication.Publication;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @ManyToMany(mappedBy = "categories")
    private Set<Publication> publications = new HashSet<>();

    public Category(String category) {
        this.category = category;
    }

    public Category() {
    }
}