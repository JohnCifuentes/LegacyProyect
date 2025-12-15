package com.legacybridge.dto.caregorydetail;

public record CreateCategoryDetailDTO(
        Long categoryId,
        String value,
        String status
) {
}
