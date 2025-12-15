package com.legacybridge.service;

import com.legacybridge.dto.category.CreateCategoryDTO;
import com.legacybridge.dto.category.DeleteCategoryDTO;
import com.legacybridge.dto.category.ReadCategoryDTO;
import com.legacybridge.dto.category.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {

    void createCategory(CreateCategoryDTO categoryDTO);

    void updateCategory(UpdateCategoryDTO categoryDTO);

    void deleteCategory(DeleteCategoryDTO categoryDTO);

    ReadCategoryDTO getCategory(Long categoryId);

    List<ReadCategoryDTO> getCategories();

}
