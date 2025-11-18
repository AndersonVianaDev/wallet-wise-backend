package com.anderson.wallet_wise.domain.services.impl;

import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.IPasswordEncoderService;
import com.anderson.wallet_wise.domain.services.IUserService;
import com.anderson.wallet_wise.infra.database.repositories.UserRepository;
import com.anderson.wallet_wise.infra.exceptions.NotFoundException;
import com.anderson.wallet_wise.infra.exceptions.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final IPasswordEncoderService passwordEncoderService;

    @Override
    public User save(User user) {
        if(repository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email already linked to another account.");
        }

        final String hash = passwordEncoderService.encode(user.getPassword());
        user.setPassword(hash);

        return repository.save(user);
    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }
}
