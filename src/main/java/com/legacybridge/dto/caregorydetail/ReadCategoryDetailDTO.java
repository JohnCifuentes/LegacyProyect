package com.legacybridge.dto.caregorydetail;

import com.legacybridge.model.Category;

public record ReadCategoryDetailDTO(
        Long categoryDetailId,
        Category category,
        String value,
        String status
) {
}
