package com.crater.accounting.service.impl;

import com.crater.accounting.bean.database.UserDataPojo;
import com.crater.accounting.bean.service.account.AddNewAccountDto;
import com.crater.accounting.bean.service.account.LoginDto;
import com.crater.accounting.bean.service.account.LoginResultDto;
import com.crater.accounting.dao.UserDataDao;
import com.crater.accounting.exception.AccountException;
import com.crater.accounting.exception.DbException;
import com.crater.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class AccountServiceImpl implements AccountService {

    private UserDataDao userDataDao;
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
        var token = generateToken(loginDto);
        return new LoginResultDto(token);
    }

    private boolean checkIsUserExist(String userName, String password) {
        try {
            var result = false;
            var userData = userDataDao.select(new UserDataPojo(userName, null, true, null, null, null, null));
            if (!userData.isEmpty()) {
                result = passwordEncoder.matches(password, userData.getFirst().password());
            }
            return result;
        } catch (Exception e) {
            throw new DbException("select db to check authorization fail", e);
        }
    }

    private String generateToken(LoginDto loginDto) {
        var encoder = Base64.getEncoder();
        return encoder.encodeToString((loginDto.userName() + ":" + loginDto.password()).getBytes());
    }

    @Autowired
    public void setUserDataDao(UserDataDao userDataDao) {
        this.userDataDao = userDataDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
