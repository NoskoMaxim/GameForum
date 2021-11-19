package com.gameforum.service.forum;

import com.gameforum.dto.publication.CategoryDto;
import com.gameforum.dto.publication.PublicationDto;
import com.gameforum.dto.publication.TagDto;
import com.gameforum.dto.user.UserDto;
import com.gameforum.model.publication.Publication;
import com.gameforum.model.user.User;
import com.gameforum.repository.publication.PublicationRepos;
import com.gameforum.repository.user.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    private final PublicationRepos publicationRepos;
    private final UserRepos userRepos;

    @Autowired
    public ForumService(PublicationRepos publicationRepos, UserRepos userRepos) {
        this.publicationRepos = publicationRepos;
        this.userRepos = userRepos;
    }

    public List<PublicationDto> getAllPublications() {
        List<Publication> publications = publicationRepos.findAll();
        return convertPublicationListToPublicationDtoList(publications);
    }

    public List<PublicationDto> findPublicationsByTitle(String titleName) {
        List<Publication> publications = publicationRepos.findPublicationsByTitle(titleName);
        return convertPublicationListToPublicationDtoList(publications);
    }

    public PublicationDto findPublicationById(Long publicationId) {
        Optional<Publication> publication = publicationRepos.findById(publicationId);
        return publication.map(this::convertPublicationToPublicationDto).orElse(null);
    }

    public Optional<UserDto> findUserByName(String username) {
        Optional<User> user = userRepos.findUserByUsername(username);
        return user.map(this::convertUserToUserDto);
    }


    private List<PublicationDto> convertPublicationListToPublicationDtoList(List<Publication> publications) {
        List<PublicationDto> publicationsDto = new ArrayList<>();
        publications.forEach(publication -> publicationsDto
                .add(convertPublicationToPublicationDto(publication)));
        return publicationsDto;
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

    private UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setUserStatus(user.getUserStatus());
        userDto.setUserRole(user.getUserRole());
        return userDto;
    }
}
