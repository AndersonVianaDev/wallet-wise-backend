package com.anderson.wallet_wise.controller.dtos.request;

import com.anderson.wallet_wise.domain.model.User;

public record UserRequestDTO(String name, String email, String password) {

    public static User from(UserRequestDTO request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
