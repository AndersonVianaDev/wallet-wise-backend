package com.anderson.wallet_wise.controller.dtos.response;

import com.anderson.wallet_wise.domain.model.Category;

public record CategoryResponseDTO(Long id, String name, String color, String icon) {
    public static CategoryResponseDTO of(Category category) {
        return new CategoryResponseDTO(category.getId(),
                category.getName(),
                category.getColor(),
                category.getIcon());
    }
}
