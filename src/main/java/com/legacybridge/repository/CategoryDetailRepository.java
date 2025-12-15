package com.legacybridge.repository;

import com.legacybridge.dto.caregorydetail.ReadCategoryDetailDTO;
import com.legacybridge.model.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {

    List<ReadCategoryDetailDTO> findByCategory_CategoryId(Long categoryCategoryId);

    List<CategoryDetail> findByOrderByCategoryDetailIdAsc();

}
