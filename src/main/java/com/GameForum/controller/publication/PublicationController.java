package com.GameForum.controller.publication;

import com.GameForum.dto.publication.PublicationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game-forum/publication")
public class PublicationController {

    private Map<Long, PublicationDto> publications = new HashMap<>();

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity addPublication(@RequestBody PublicationDto publication) {
        publication.setId((long) publications.size());
        publications.put((long) publications.size(), publication);
        return ResponseEntity.ok(publication);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity updatePublication(@RequestBody PublicationDto publication) {
        publications.put(publication.getId(), publication);
        return ResponseEntity.ok().build();
    }

//    @GetMapping(value = "/findByTitle", produces = "application/json") //Дописать
//    public ResponseEntity findPublicationsByTitle(@PathVariable List<String> findByTitle) {
//        //publications.entrySet().stream().filter(publication -> publication.getValue().getTitle().equals());
//        Map<Long, PublicationDto> pub = findByTitle.forEach(tit -> publications.entrySet().stream().filter(publication -> publication.getValue().getTitle().equals(tit)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
//       return ResponseEntity.ok(pub);
//    }

    @GetMapping(value = "{publicationId}", produces = "application/json")
    public ResponseEntity getPublicationById(@PathVariable Long publicationId) {
        PublicationDto publication = publications.get(publicationId);
        return ResponseEntity.ok(publication);
    }

    @DeleteMapping(value = "{publicationId}", produces = "application/json")
    public ResponseEntity deletePublication(@PathVariable Long publicationId) {
        PublicationDto publication = publications.remove(publicationId);
        return ResponseEntity.ok(publication);
    }
}
