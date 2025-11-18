package com.anderson.wallet_wise.domain.services;

import com.anderson.wallet_wise.domain.model.User;

import java.util.UUID;

public interface IUserService {
    User save(User user);
    User findById(UUID id);
    User findByEmail(String email);
}
