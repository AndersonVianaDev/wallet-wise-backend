package com.anderson.wallet_wise.controller.dtos.response;

import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.model.enums.UserRole;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, UserRole role) {

    public static UserResponseDTO of (User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}