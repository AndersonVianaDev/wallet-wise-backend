package com.anderson.wallet_wise.controller.dtos.request;

import com.anderson.wallet_wise.domain.model.Category;
import com.anderson.wallet_wise.domain.model.User;

public record CategoryRequestDTO(String name, String color, String icon) {
    public static Category from(User user, CategoryRequestDTO request) {
        return Category.builder()
                .name(request.name())
                .color(request.color())
                .icon(request.icon())
                .owner(user)
                .build();
    }
}
