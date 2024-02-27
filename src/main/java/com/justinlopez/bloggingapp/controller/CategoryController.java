package com.justinlopez.bloggingapp.controller;

import com.justinlopez.bloggingapp.domain.dto.CategoryDTO;
import com.justinlopez.bloggingapp.domain.usecase.ICategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryUseCase iCategoryUseCase;

    // Create
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCategoryUseCase.createCategory(categoryDTO));
    }

    // Update
    @PatchMapping
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(iCategoryUseCase.updateCategory(categoryDTO));
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(iCategoryUseCase.getCategoryById(id));
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(iCategoryUseCase.getAllCategories());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long id) {
        return new ResponseEntity<>(iCategoryUseCase.deleteCategory(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
