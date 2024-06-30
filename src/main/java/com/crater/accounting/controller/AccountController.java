package com.crater.accounting.controller;

import com.crater.accounting.bean.request.account.AddNewAccountRequest;
import com.crater.accounting.bean.request.account.LoginRequest;
import com.crater.accounting.bean.response.Status;
import com.crater.accounting.bean.response.account.AddNewAccountResponse;
import com.crater.accounting.bean.response.account.LoginResponse;
import com.crater.accounting.bean.service.account.AddNewAccountDto;
import com.crater.accounting.bean.service.account.LoginDto;
import com.crater.accounting.exception.AccountException;
import com.crater.accounting.exception.RequestFormatException;
import com.crater.accounting.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class AccountController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private AccountService accountService;

    @PostMapping("accountController/account")
    public AddNewAccountResponse addNewAccount(@RequestBody AddNewAccountRequest request) {
        try {
            validateRequest(request);
            var addAccountDto = generateAddAccountDto(request);
            accountService.insertAccount(addAccountDto);
            return new AddNewAccountResponse(Status.generateSuccessStatus());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateRequest(AddNewAccountRequest request) {
        var errorMessage = new ArrayList<String>();
        if (StringUtils.isBlank(Optional.ofNullable(request).orElseThrow(() ->
                new RequestFormatException("AddNewRequest cant be null")).userName())) {
            errorMessage.add("userName cant be blank");
        }
        if (StringUtils.isBlank(request.password())) {
            errorMessage.add("password cant be blank");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(String.join("\n", errorMessage));
        }
    }

    private AddNewAccountDto generateAddAccountDto(AddNewAccountRequest request) {
        return new AddNewAccountDto(request.userName(), request.password(), request.isActive());
    }

    @PostMapping("account/login")
    public LoginResponse login(@RequestBody LoginRequest request) throws AccountException {
        try {
            validateRequest(request);
            var loginDto = generateLoginDto(request);
            var loginResultDto = accountService.login(loginDto);
            return new LoginResponse(Status.generateSuccessStatus(), loginResultDto.token());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    private void validateRequest(LoginRequest request) {
        if (request == null) {
            throw new RequestFormatException("LoginRequest cant be null");
        }
        var errorMessage = new ArrayList<String>();
        if (StringUtils.isBlank(request.username())) {
            errorMessage.add("username cant be blank");
        }
        if (StringUtils.isBlank(request.password())) {
            errorMessage.add("password cant be blank");
        }
        if (!errorMessage.isEmpty()) {
            throw new RequestFormatException(String.join("\n", errorMessage));
        }
    }

    private LoginDto generateLoginDto(LoginRequest request) {
        return new LoginDto(request.username(), request.password());
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
