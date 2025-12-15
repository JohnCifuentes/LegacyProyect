package com.legacybridge.controller;

import com.legacybridge.dto.caregorydetail.CreateCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.DeleteCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.ReadCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.UpdateCategoryDetailDTO;
import com.legacybridge.service.CategoryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category/detail")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryDetailController {
    private final CategoryDetailService categoryDetailService;

    @PostMapping
    public void createCategoryDetail(@RequestBody CreateCategoryDetailDTO categoryDTO){
        categoryDetailService.createCategoryDetail(categoryDTO);
    }

    @PutMapping
    public void updateCategoryDetail(@RequestBody UpdateCategoryDetailDTO categoryDTO){
        categoryDetailService.updateCategoryDetail(categoryDTO);
    }

    @DeleteMapping
    public void deleteCategoryDetail(@RequestBody DeleteCategoryDetailDTO categoryDTO){
        categoryDetailService.deleteCategoryDetail(categoryDTO);
    }

    @GetMapping("/get/category/detail")
    public ReadCategoryDetailDTO getCategoryDetail(@RequestParam Long categoryDetailId){
        return categoryDetailService.getCategoryDetail(categoryDetailId);
    }

    @GetMapping("/get/category/details")
    public List<ReadCategoryDetailDTO> getCategoryDetailsByCategory(@RequestParam Long categoryId){
        return categoryDetailService.getCategoryDetailsByCategory(categoryId);
    }

    @GetMapping("/get/category/details/all")
    public List<ReadCategoryDetailDTO> getAllCategoryDetails(){
        return categoryDetailService.getAllCategoryDetails();
    }

}
