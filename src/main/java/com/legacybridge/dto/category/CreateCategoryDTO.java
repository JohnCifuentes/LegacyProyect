package com.legacybridge.dto.category;

public record CreateCategoryDTO(
        String name,
        String description,
        String status
) {
}
