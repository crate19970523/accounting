package com.crater.accounting.interceptor;

import com.crater.accounting.dao.TokenDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class VerifyAuthorizationInterceptor implements HandlerInterceptor {

    private TokenDao tokenDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().matches("/swagger-ui.*") || request.getRequestURI().matches(".*api-docs.*")
                || "/open-api".equals(request.getRequestURI())
                || "/account/login".equals(request.getRequestURI())) {
            return true;
        }
        var errorMessage = "{\"status\": {\"isSuccess\": false,\"errorType\": \"verification failed\",\"errorDetail\": \"verification failed please login and use token in Authorization\"}}";
        var authorization = request.getHeader("Authorization");
        if (authorization == null) {
            response.getWriter().write(errorMessage);
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        var isTokenExists = tokenDao.exists(authorization.split(" ")[1]);
        if (!isTokenExists) {
            response.getWriter().write(errorMessage);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }
        return true;
    }

    @Autowired
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }
}
