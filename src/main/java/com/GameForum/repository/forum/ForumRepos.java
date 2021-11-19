package com.gameforum.repository.forum;

import com.gameforum.model.publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepos extends JpaRepository<Publication, Long> {
}