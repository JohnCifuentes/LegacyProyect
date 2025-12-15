package com.legacybridge.mapper;

import com.legacybridge.dto.category.CreateCategoryDTO;
import com.legacybridge.dto.category.DeleteCategoryDTO;
import com.legacybridge.dto.category.ReadCategoryDTO;
import com.legacybridge.dto.category.UpdateCategoryDTO;
import com.legacybridge.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    /**
     * CREATE
     */
    Category toEntity(CreateCategoryDTO categoryDTO);

    /**
     * UPDATE
     */
    void updateEntityFromDTO(UpdateCategoryDTO categoryDTO, @MappingTarget Category category);

    /**
     * DELETE/INACTIVE
     */
    //void deleteEntityFromDTO(DeleteCategoryDTO categoryDTO, @MappingTarget Category category);

    /**
     * GET
     */
    ReadCategoryDTO toDTO(Category category);

}
