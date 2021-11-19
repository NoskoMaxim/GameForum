package com.gameforum.model.tag;

import com.gameforum.model.publication.Publication;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<Publication> publications = new HashSet<>();

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag() {
    }
}
