package com.gameforum.controller.category;

import com.gameforum.dto.publication.CategoryDto;
import com.gameforum.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-forum/category")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto) {
        service.addCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCategory(@RequestBody CategoryDto categoryDto) {
        service.updateCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/category")
    public ResponseEntity deleteCategoryById(@RequestParam Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCategoryByName(@RequestParam String categoryName) {
        service.deleteCategoryByName(categoryName);
        return ResponseEntity.ok().build();
    }
}
