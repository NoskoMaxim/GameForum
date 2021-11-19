package com.gameforum.repository.publication;

import com.gameforum.model.publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepos extends JpaRepository<Publication, Long> {

    List<Publication> findPublicationsByTitle(String titleName);
}