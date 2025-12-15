package com.legacybridge.dto.category;

public record ReadCategoryDTO(
        Long categoryId,
        String name,
        String description,
        String status
) {
}
