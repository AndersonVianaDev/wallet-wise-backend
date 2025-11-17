package com.anderson.wallet_wise.infra.database.cache;

import com.anderson.wallet_wise.domain.services.IRedisCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisCodeServiceImpl implements IRedisCodeService {

    @Value("${spring.redis.expiration}")
    private final long expiration;

    private final StringRedisTemplate redisTemplate;

    @Override
    public void save(String email, String code) {
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(expiration));
    }

    @Override
    public String getCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    @Override
    public void delete(String email) {
        redisTemplate.delete(email);
    }
}
