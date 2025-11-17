package com.anderson.wallet_wise.domain.services;

public interface IPasswordResetService {
    void sendResetCode(String email);
    void resetPassword(String email, String code, String newPassword);
}
