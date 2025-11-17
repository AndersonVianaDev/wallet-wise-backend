package com.anderson.wallet_wise.domain.services;

public interface IRedisCodeService {
    void save(String email, String code);
    String getCode(String email);
    void delete(String email);
}
