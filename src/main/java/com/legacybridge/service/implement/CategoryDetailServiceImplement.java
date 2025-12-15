package com.legacybridge.service.implement;

import com.legacybridge.dto.caregorydetail.CreateCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.DeleteCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.ReadCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.UpdateCategoryDetailDTO;
import com.legacybridge.mapper.CategoryDetailMapper;
import com.legacybridge.model.Category;
import com.legacybridge.model.CategoryDetail;
import com.legacybridge.repository.CategoryDetailRepository;
import com.legacybridge.repository.CategoryRepository;
import com.legacybridge.service.CategoryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryDetailServiceImplement implements CategoryDetailService {
    private final CategoryDetailRepository categoryDetailRepository;
    private final CategoryDetailMapper categoryDetailMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public void createCategoryDetail(CreateCategoryDetailDTO categoryDetailDTO) {
        System.out.println("createCategoryDetail :" + categoryDetailDTO);
        Category category = categoryRepository.findById(categoryDetailDTO.categoryId()).orElseThrow(()
            -> new RuntimeException("Category not found")
        );
        CategoryDetail categoryDetail = categoryDetailMapper.toEntity(categoryDetailDTO);
        categoryDetail.setCategory(category);
        categoryDetailRepository.save(categoryDetail);
    }

    @Override
    public void updateCategoryDetail(UpdateCategoryDetailDTO categoryDTO) {
        CategoryDetail categoryDetail = categoryDetailRepository.findById(categoryDTO.categoryDetailId()).orElseThrow(()
            -> new RuntimeException("Category not found")
        );
        categoryDetailMapper.updateEntityFromDTO(categoryDTO, categoryDetail);
        categoryDetailRepository.save(categoryDetail);
    }

    @Override
    public void deleteCategoryDetail(DeleteCategoryDetailDTO categoryDTO) {
        try{
            categoryDetailRepository.deleteById(categoryDTO.categoryDetailId());
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar la categoría detalle, existen registros asociados.");
        }
    }

    @Override
    public ReadCategoryDetailDTO getCategoryDetail(Long categoryDetailId) {
        CategoryDetail categoryDetail = categoryDetailRepository.findById(categoryDetailId).orElseThrow(()
                -> new RuntimeException("Category not found")
        );
        return categoryDetailMapper.toDTO(categoryDetail);
    }

    @Override
    public List<ReadCategoryDetailDTO> getCategoryDetailsByCategory(Long categoryId) {
        return categoryDetailRepository.findByCategory_CategoryId(categoryId);
    }

    @Override
    public List<ReadCategoryDetailDTO> getAllCategoryDetails() {
        return categoryDetailRepository.findByOrderByCategoryDetailIdAsc()
                .stream()
                .filter(object -> "A".equals(object.getStatus()))
                .map(categoryDetailMapper::toDTO)
                .toList();
    }

}
