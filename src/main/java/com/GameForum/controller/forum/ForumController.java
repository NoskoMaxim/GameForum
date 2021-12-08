package com.gameforum.controller.forum;

import com.gameforum.dto.publication.CategoryDto;
import com.gameforum.dto.publication.PublicationDto;
import com.gameforum.dto.user.UserDto;
import com.gameforum.service.forum.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game-forum/forum")
public class ForumController {

    private final ForumService service;

    @Autowired
    public ForumController(ForumService service) {
        this.service = service;
    }

    @GetMapping(value = "/all-publications", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllPublications() {
        List<PublicationDto> publications = service.getAllPublications();
        return ResponseEntity.ok(publications);
    }

    @GetMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRatingPublications() {
        List<PublicationDto> publications = service.getRatingPublications();
        return ResponseEntity.ok(publications);
    }

    @GetMapping(value = "/all-category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCategories() {
        List<CategoryDto> categories = service.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/post/category", produces = MediaType.APPLICATION_JSON_VALUE) //Дописать
    public ResponseEntity findPublicationsByCategory(@RequestParam Long id) {
        List<PublicationDto> publicationsDto = service.findPublicationsByCategory(id);
        return ResponseEntity.ok(publicationsDto);
    }

    @GetMapping(value = "/title", produces = MediaType.APPLICATION_JSON_VALUE) //Дописать
    public ResponseEntity findPublicationsByTitle(@RequestParam String titleName) {
        List<PublicationDto> publicationsDto = service.findPublicationsByTitle(titleName);
        return ResponseEntity.ok(publicationsDto);
    }

    @GetMapping(value = "{publicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findPublicationById(@PathVariable Long publicationId) {
        Optional<PublicationDto> publicationDto = service.findPublicationById(publicationId);
        return publicationDto.isPresent()
                ? ResponseEntity.ok(publicationDto)
                : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/name", produces = "application/json")
    public ResponseEntity findUserByName(@RequestParam String username) {
        Optional<UserDto> userDto = service.findUserByName(username);
        return userDto.isPresent()
                ? ResponseEntity.ok(userDto.get())
                : ResponseEntity.noContent().build();
    }
}
