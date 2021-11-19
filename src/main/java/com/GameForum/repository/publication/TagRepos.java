package com.gameforum.repository.publication;

import com.gameforum.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepos extends JpaRepository<Tag,Long> {

}