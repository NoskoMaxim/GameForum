package com.gameforum.controller.forum;

import com.gameforum.dto.publication.PublicationDto;
import com.gameforum.dto.user.UserDto;
import com.gameforum.service.forum.ForumService;
import com.gameforum.service.publication.PublicationService;
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

    @GetMapping(value = "/titleName", produces = MediaType.APPLICATION_JSON_VALUE) //Дописать
    public ResponseEntity findPublicationsByTitle(@RequestParam String titleName) {
        List<PublicationDto> publicationsDto = service.findPublicationsByTitle(titleName);
        return ResponseEntity.ok(publicationsDto);
    }

    @GetMapping(value = "{publicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findPublicationById(@PathVariable Long publicationId) {
        PublicationDto publicationDto = service.findPublicationById(publicationId);
        return ResponseEntity.ok(publicationDto);
    }

    @GetMapping(value = "/user/username", produces = "application/json")
    public ResponseEntity findUserByName(@RequestParam String username) {
        Optional<UserDto> user = service.findUserByName(username);
        return user.isPresent()
                ? ResponseEntity.ok(user.get())
                : ResponseEntity.noContent().build();
    }
}
