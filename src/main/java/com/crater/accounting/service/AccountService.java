package com.crater.accounting.service;

import com.crater.accounting.bean.service.account.AddNewAccountDto;
import com.crater.accounting.bean.service.account.LoginDto;
import com.crater.accounting.bean.service.account.LoginResultDto;
import com.crater.accounting.exception.AccountException;

public interface AccountService {
    void insertAccount(AddNewAccountDto addNewAccountDto);
    LoginResultDto login(LoginDto loginDto) throws AccountException;

}
