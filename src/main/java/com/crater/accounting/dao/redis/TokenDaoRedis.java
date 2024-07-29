package com.crater.accounting.dao.redis;

import com.crater.accounting.bean.database.TokenPojo;
import com.crater.accounting.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TokenDaoRedis implements TokenDao {
    private RedisTemplate<String, TokenPojo> tokenRedisTemplate;


    @Override
    public TokenPojo getByToken(String token) {
        return tokenRedisTemplate.opsForValue().get("token:" + token);
    }

    @Override
    public TokenPojo getByUserName(String userName) {
        return tokenRedisTemplate.opsForValue().get("userName:" + userName);
    }

    @Override
    public void update(TokenPojo tokenPojo) {
        var valueOps = tokenRedisTemplate.opsForValue();
        valueOps.set("token:" + tokenPojo.token(), tokenPojo, tokenPojo.timeout(), TimeUnit.MINUTES);
        valueOps.set("userName:" + tokenPojo.userName(), tokenPojo);
    }

    @Override
    public boolean exists(String token) {
        var valueOps = tokenRedisTemplate.opsForValue();
        return valueOps.get("token:" + token) != null;
    }

    @Override
    public void deleteTokenByToken(String token) {
        tokenRedisTemplate.delete("token:" + token);
    }

    @Override
    public void deleteTokenByUserName(String userName) {
        var tokenInfo = getByUserName(userName);
        deleteTokenByToken(tokenInfo.token());
    }

    @Autowired
    public void setTokenRedisTemplate(RedisTemplate<String, TokenPojo> tokenRedisTemplate) {
        this.tokenRedisTemplate = tokenRedisTemplate;
    }
}
