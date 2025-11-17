package com.anderson.wallet_wise.controller.dtos.request;

public record ResetPasswordRequestDTO(String email, String code, String newPassword) {
}
