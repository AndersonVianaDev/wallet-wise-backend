package com.anderson.wallet_wise.domain.services.impl;

import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.IPasswordEncoderService;
import com.anderson.wallet_wise.domain.services.IUserService;
import com.anderson.wallet_wise.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final IPasswordEncoderService passwordEncoderService;

    @Override
    public User save(User user) {
        final String hash = passwordEncoderService.encode(user.getPassword());
        user.setPassword(hash);

        return repository.save(user);
    }
}
