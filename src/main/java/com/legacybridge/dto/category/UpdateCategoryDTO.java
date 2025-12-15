package com.legacybridge.dto.category;

public record UpdateCategoryDTO(
        Long categoryId,
        String name,
        String description,
        String status
) {
}
