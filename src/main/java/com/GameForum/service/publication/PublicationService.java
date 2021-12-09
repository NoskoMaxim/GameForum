package com.gameforum.service.publication;

import com.gameforum.dto.publication.PublicationDto;
import com.gameforum.model.category.Category;
import com.gameforum.model.publication.Publication;
import com.gameforum.model.publication.PublicationStatus;
import com.gameforum.repository.publication.PublicationRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PublicationService {

    private final PublicationRepos publicationRepos;

    @Autowired
    public PublicationService(PublicationRepos publicationRepos) {
        this.publicationRepos = publicationRepos;
    }

    public void addPublication(PublicationDto publicationDto) {
        Publication publication = new Publication();
        publication.setStatus(PublicationStatus.EXISTS);
        publication.setShipDate(LocalDateTime.now());
        publication.setLikes(0L);
        intiPublication(publicationDto, publication);
        publicationRepos.save(publication);
    }

    public void updatePublication(PublicationDto publicationDto) {
        Publication publication = new Publication();
        publication.setPublicationId(publicationDto.getPublicationId());
        publication.setStatus(PublicationStatus.EDITED);
        publication.setShipDate(publicationDto.getShipDate());
        publication.setLikes(publicationDto.getLikes());
        intiPublication(publicationDto, publication);
        publicationRepos.save(publication);
    }

    public void deletePublication(Long publicationId) {
        publicationRepos.deleteById(publicationId);
    }

    private void intiPublication(PublicationDto publicationDto, Publication publication) {
        publication.setTitle(publicationDto.getTitle());
        publication.setContent(publicationDto.getContent());
        publication.setPhoto(publicationDto.getPhoto());
        publication.setPublicationCategory(new
                Category(publicationDto.getCategoryId(),publicationDto.getCategoryName()));
    }
}