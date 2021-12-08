package com.gameforum.controller.publication;

import com.gameforum.dto.publication.PublicationDto;
import com.gameforum.service.publication.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-forum/publication")
public class PublicationController {

    private final PublicationService service;

    @Autowired
    public PublicationController(PublicationService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPublication(@RequestBody PublicationDto publicationDto) {
        service.addPublication(publicationDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePublication(@RequestBody PublicationDto publicationDto) {
        service.updatePublication(publicationDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{publicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePublication(@PathVariable Long publicationId) {
        service.deletePublication(publicationId);
        return ResponseEntity.ok().build();
    }
}
