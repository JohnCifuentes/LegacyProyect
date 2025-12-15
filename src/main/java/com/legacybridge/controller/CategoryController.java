package com.legacybridge.controller;

import com.legacybridge.dto.category.CreateCategoryDTO;
import com.legacybridge.dto.category.DeleteCategoryDTO;
import com.legacybridge.dto.category.ReadCategoryDTO;
import com.legacybridge.dto.category.UpdateCategoryDTO;
import com.legacybridge.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public void createCategory(@RequestBody CreateCategoryDTO createCategoryDTO) {
        categoryService.createCategory(createCategoryDTO);
    }

    @PutMapping
    public void updateCategory(@RequestBody UpdateCategoryDTO updateCategoryDTO) {
        categoryService.updateCategory(updateCategoryDTO);
    }

    @DeleteMapping
    public void deleteCategory(@RequestBody DeleteCategoryDTO deleteCategoryDTO) {
        categoryService.deleteCategory(deleteCategoryDTO);
    }

    @GetMapping("/get/category")
    public ReadCategoryDTO getCategory(Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @GetMapping("/get/categories")
    public List<ReadCategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

}
