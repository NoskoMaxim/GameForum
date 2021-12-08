package com.gameforum.service.forum;

import com.gameforum.dto.publication.CategoryDto;
import com.gameforum.dto.publication.PublicationDto;
import com.gameforum.dto.user.UserDto;
import com.gameforum.model.category.Category;
import com.gameforum.model.publication.Publication;
import com.gameforum.model.user.User;
import com.gameforum.repository.publication.CategoryRepos;
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
    private final CategoryRepos categoryRepos;

    @Autowired
    public ForumService(PublicationRepos publicationRepos, UserRepos userRepos, CategoryRepos categoryRepos) {
        this.publicationRepos = publicationRepos;
        this.userRepos = userRepos;
        this.categoryRepos = categoryRepos;
    }

    public List<PublicationDto> getAllPublications() {
        List<Publication> publications = publicationRepos.findAll();
        return convertPublicationListToPublicationDtoList(publications);
    }

    public List<PublicationDto> getRatingPublications() {
        List<Publication> publications = publicationRepos.findPublicationsByLikesIsBefore(5L);
        return convertPublicationListToPublicationDtoList(publications);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepos.findAll();
        return convertCategoryListToCategoryDtoList(categories);
    }

    public List<PublicationDto> findPublicationsByTitle(String titleName) {
        List<Publication> publications = publicationRepos.findPublicationsByTitle(titleName);
        return convertPublicationListToPublicationDtoList(publications);
    }

    public List<PublicationDto> findPublicationsByCategory(Long categoryId) {
        List<Publication> publications = publicationRepos.findPublicationsByCategory_CategoryId(categoryId);
        return convertPublicationListToPublicationDtoList(publications);
    }

    public Optional<PublicationDto> findPublicationById(Long publicationId) {
        Optional<Publication> publication = publicationRepos.findById(publicationId);
        return publication.map(this::convertPublicationToPublicationDto);
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
        publicationDto.setPublicationId(publication.getPublicationId());
        publicationDto.setUserId(publication.getUser().getUserId());
        publicationDto.setUsername(publication.getUser().getUsername());
        publicationDto.setTitle(publication.getTitle());
        publicationDto.setContent(publication.getContent());
        publicationDto.setPhoto(publication.getPhoto());
        publicationDto.setCategoryName(publication.getCategory().getCategoryName());
        publicationDto.setCategoryId(publication.getCategory().getCategoryId());
        publicationDto.setStatus(publication.getStatus());
        publicationDto.setShipDate(publication.getShipDate());
        publicationDto.setLikes(publication.getLikes());

        return publicationDto;
    }

    private List<CategoryDto> convertCategoryListToCategoryDtoList(List<Category> categories) {
        List<CategoryDto> categoriesDto = new ArrayList<>();
        categories.forEach(category -> categoriesDto
                .add(convertCategoryToCategoryDto(category)));
        return categoriesDto;
    }

    private CategoryDto convertCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }

    private UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setUserRole(user.getUserRole());
        return userDto;
    }
}
