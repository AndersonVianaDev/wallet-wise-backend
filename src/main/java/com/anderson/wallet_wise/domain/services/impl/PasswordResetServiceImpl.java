package com.anderson.wallet_wise.domain.services.impl;

import com.anderson.wallet_wise.domain.model.User;
import com.anderson.wallet_wise.domain.services.IEmailSenderService;
import com.anderson.wallet_wise.domain.services.IPasswordEncoderService;
import com.anderson.wallet_wise.domain.services.IPasswordResetService;
import com.anderson.wallet_wise.domain.services.IRedisCodeService;
import com.anderson.wallet_wise.infra.exceptions.InvalidDataException;
import com.anderson.wallet_wise.infra.exceptions.NotFoundException;
import com.anderson.wallet_wise.infra.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements IPasswordResetService {

    private final UserRepository repository;
    private final IPasswordEncoderService passwordEncoderService;
    private final IRedisCodeService redisCodeService;
    private final IEmailSenderService emailSenderService;

    @Override
    public void sendResetCode(String email) {
        if(repository.findByEmail(email).isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        final String code = String.format("%06d", new Random().nextInt(999999));
        redisCodeService.save(email, code);

        emailSenderService.sendEmail(email,
                "Recuperação de senha",
                "Seu código de recuperação é: " + code);
    }

    @Override
    public void resetPassword(String email, String code, String newPassword) {
        final String storedCode = redisCodeService.getCode(email);
        if (isNull(storedCode) || !storedCode.equals(code)) {
            throw new InvalidDataException("Invalid or Expired Code");
        }

        final User user = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        final String newHash = passwordEncoderService.encode(newPassword);
        user.setPassword(newHash);

        repository.save(user);
    }



}
