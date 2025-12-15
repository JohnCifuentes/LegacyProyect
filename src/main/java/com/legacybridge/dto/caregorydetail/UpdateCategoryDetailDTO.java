package com.legacybridge.dto.caregorydetail;

public record UpdateCategoryDetailDTO(
        Long categoryDetailId,
        Long categoryId,
        String value,
        String status
) {
}
