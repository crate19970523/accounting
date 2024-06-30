package com.crater.accounting.urils;

import com.crater.accounting.context.ApplicationContextProvider;
import com.crater.accounting.dao.TokenDao;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    public static String getUserFromAuthorization(String authorization) {
        var tokenDao = ApplicationContextProvider.getApplicationContext().getBean(TokenDao.class);
        return tokenDao.getByToken(authorization.split(" ")[1]).userName();
    }
}
