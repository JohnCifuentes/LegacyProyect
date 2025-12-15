package com.legacybridge.service;

import com.legacybridge.dto.caregorydetail.CreateCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.DeleteCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.ReadCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.UpdateCategoryDetailDTO;

import java.util.List;

public interface CategoryDetailService {

    void createCategoryDetail(CreateCategoryDetailDTO categoryDTO);

    void updateCategoryDetail(UpdateCategoryDetailDTO categoryDTO);

    void deleteCategoryDetail(DeleteCategoryDetailDTO categoryDTO);

    ReadCategoryDetailDTO getCategoryDetail(Long categoryDetailId);

    List<ReadCategoryDetailDTO> getCategoryDetailsByCategory(Long categoryId);

    List<ReadCategoryDetailDTO> getAllCategoryDetails();

}
