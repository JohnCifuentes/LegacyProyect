package com.legacybridge.mapper;

import com.legacybridge.dto.caregorydetail.CreateCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.ReadCategoryDetailDTO;
import com.legacybridge.dto.caregorydetail.UpdateCategoryDetailDTO;
import com.legacybridge.model.CategoryDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryDetailMapper {

    /**
     * CREATE
     */
    CategoryDetail toEntity(CreateCategoryDetailDTO categoryDTO);

    /**
     * UPDATE
     */
    void updateEntityFromDTO(UpdateCategoryDetailDTO categoryDTO, @MappingTarget CategoryDetail categoryDetail);

    /**
     * GET
     */
    ReadCategoryDetailDTO toDTO(CategoryDetail categoryDetail);

}
