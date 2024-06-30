package com.crater.accounting.dao;

import com.crater.accounting.bean.database.TokenPojo;

public interface TokenDao {
    TokenPojo getByToken(String token);
    TokenPojo getByUserName(String userName);
    void update(TokenPojo tokenPojo);
    boolean exists(String token);
    void deleteTokenByToken(String token);
    void deleteTokenByUserName(String userName);
}
