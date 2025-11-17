package com.anderson.wallet_wise.domain.services;

public interface IEmailSenderService {
    void sendEmail(String to, String subject, String body);
}
