package com.crater.accounting.service.impl;

import com.crater.accounting.bean.database.TokenPojo;
import com.crater.accounting.bean.database.UserDataPojo;
import com.crater.accounting.bean.service.account.AddNewAccountDto;
import com.crater.accounting.bean.service.account.LoginDto;
import com.crater.accounting.bean.service.account.LoginResultDto;
import com.crater.accounting.dao.TokenDao;
import com.crater.accounting.dao.UserDataDao;
import com.crater.accounting.exception.AccountException;
import com.crater.accounting.exception.DbException;
import com.crater.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private UserDataDao userDataDao;
    private TokenDao tokenDao;
    private Integer tokenTimeout;
    private PasswordEncoder passwordEncoder;

    @Override
    public void insertAccount(AddNewAccountDto addNewAccountDto) {
        var addUserDataPojo = generateAddUserPojo(addNewAccountDto);
        callDaoInsertUser(addUserDataPojo);
    }

    private UserDataPojo generateAddUserPojo(AddNewAccountDto addNewAccountDto) {
        var password = passwordEncoder.encode(addNewAccountDto.password());
        return new UserDataPojo(addNewAccountDto.userName(), password, addNewAccountDto.isActive(),
                LocalDateTime.now(), "add", null, null);
    }

    private void callDaoInsertUser(UserDataPojo userDataPojo) {
        try {
            userDataDao.insert(userDataPojo);
        } catch (Exception e) {
            throw new DbException("insert authorization failed", e);
        }
    }

    @Override
    public LoginResultDto login(LoginDto loginDto) throws AccountException {
        if (!checkIsUserExist(loginDto.userName(), loginDto.password())) {
            throw new AccountException("使用者不存在或帳號密碼錯誤");
        }
        if (checkIsTokenStillActive(loginDto.userName())) {
            deleteToken(loginDto.userName());
        }
        var token = generateToken();
        callDaoUpdateToken(loginDto.userName(), token);
        return new LoginResultDto(token);
    }

    private boolean checkIsUserExist(String userName, String password) {
        try {
            return !userDataDao.select(new UserDataPojo(userName, password, true, LocalDateTime.now(), userName,
                    LocalDateTime.now(), userName)).isEmpty();
        } catch (Exception e) {
            throw new DbException("select db to check authorization fail", e);
        }
    }

    private boolean checkIsTokenStillActive(String userName) {
        try {
            var result = false;
            var tokenData = tokenDao.getByUserName(userName);
            if (tokenData != null) {
                result = (tokenDao.getByToken(tokenData.token()) != null);
            }
            return result;
        } catch (Exception e) {
            throw new DbException("check token active failed", e);
        }
    }

    private void deleteToken(String userName) {
        try {
            tokenDao.deleteTokenByUserName(userName);
        } catch (Exception e) {
            throw new DbException("delete token failed", e);
        }
    }

    private String generateToken() {
        while (true) {
            var token = UUID.randomUUID().toString();
            if (!tokenDao.exists(token)) {
                return token;
            }
        }
    }

    private void callDaoUpdateToken(String userName, String token) {
        try {
            tokenDao.update(new TokenPojo(userName, token, tokenTimeout));
        } catch (Exception e) {
            throw new DbException("update token failed", e);
        }
    }

    @Autowired
    public void setUserDataDao(UserDataDao userDataDao) {
        this.userDataDao = userDataDao;
    }

    @Autowired
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${accounting.setting.token-timeout}")
    public void setTokenTimeout(Integer tokenTimeout) {
        this.tokenTimeout = tokenTimeout;
    }
}
