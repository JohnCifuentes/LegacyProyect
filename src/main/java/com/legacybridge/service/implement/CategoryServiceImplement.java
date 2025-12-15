package com.legacybridge.service.implement;

import com.legacybridge.dto.category.CreateCategoryDTO;
import com.legacybridge.dto.category.DeleteCategoryDTO;
import com.legacybridge.dto.category.ReadCategoryDTO;
import com.legacybridge.dto.category.UpdateCategoryDTO;
import com.legacybridge.mapper.CategoryMapper;
import com.legacybridge.model.Category;
import com.legacybridge.repository.CategoryRepository;
import com.legacybridge.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplement implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(@RequestBody CreateCategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(@RequestBody UpdateCategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.categoryId()).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        categoryMapper.updateEntityFromDTO(categoryDTO, category);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(@RequestBody DeleteCategoryDTO categoryDTO) {
        try{
            categoryRepository.deleteById(categoryDTO.categoryId());
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar la categoría, existen registros asociados.");
        }
    }

    @Override
    public ReadCategoryDTO getCategory(@RequestParam Long categoryDetailId) {
        Category category = categoryRepository.findById(categoryDetailId).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        return categoryMapper.toDTO(category);
    }

    @Override
    public List<ReadCategoryDTO> getCategories() {
        return categoryRepository.findByOrderByCategoryIdAsc()
                .stream()
                .filter(object -> "A".equals(object.getStatus()))
                .map(categoryMapper::toDTO)
                .toList();
    }

}
