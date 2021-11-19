package com.gameforum.service.publication;

import com.gameforum.dto.publication.CategoryDto;
import com.gameforum.dto.publication.PublicationDto;
import com.gameforum.dto.publication.TagDto;
import com.gameforum.model.category.Category;
import com.gameforum.model.publication.Publication;
import com.gameforum.model.publication.PublicationStatus;
import com.gameforum.model.tag.Tag;
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

    public PublicationDto addPublication(PublicationDto publicationDto) {
        Publication publication = new Publication();
        publication.setStatus(PublicationStatus.EXISTS);
        publication.setShipDate(LocalDateTime.now());
        publication.setLikes(0L);
        intiPublication(publicationDto, publication);
        publicationRepos.save(publication);
        return convertPublicationToPublicationDto(publication);
    }

    public void updatePublication(PublicationDto publicationDto) {
        Publication publication = new Publication();
        publication.setId(publicationDto.getId());
        publication.setStatus(PublicationStatus.EDITED);
        publication.setShipDate(publicationDto.getShipDate());
        publication.setLikes(publicationDto.getLikes());
        intiPublication(publicationDto, publication);
        publicationRepos.save(publication);
    }

    public void deletePublication(Long publicationId) {
        publicationRepos.deleteById(publicationId);
    }

    private void intiPublication(PublicationDto publicationDto, Publication entityPub) {
        entityPub.setTitle(publicationDto.getTitle());
        entityPub.setContent(publicationDto.getContent());
        entityPub.setPhotoUrl(publicationDto.getPhotoUrl());
        publicationDto.getCategories()
                .forEach(category -> entityPub.getCategories()
                        .add(new Category(category.getCategory())));
        publicationDto.getTags()
                .forEach(category -> entityPub.getTags()
                        .add(new Tag(category.getTag())));
    }

    private PublicationDto convertPublicationToPublicationDto(Publication publication) {
        PublicationDto publicationDto = new PublicationDto();
        publicationDto.setId(publication.getId());
        publicationDto.setTitle(publication.getTitle());
        publicationDto.setStatus(publication.getStatus());
        publicationDto.setShipDate(publication.getShipDate());
        publicationDto.setLikes(publication.getLikes());
        publicationDto.setContent(publication.getContent());
        publicationDto.setPhotoUrl(publication.getPhotoUrl());
        publication.getCategories()
                .forEach(category -> publicationDto.getCategories()
                        .add(new CategoryDto(category.getId(), category.getCategory())));
        publication.getTags()
                .forEach(tag -> publicationDto.getTags()
                        .add(new TagDto(tag.getId(), tag.getTag())));
        return publicationDto;
    }


}