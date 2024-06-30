package com.crater.accounting.config;

import com.crater.accounting.interceptor.VerifyAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private VerifyAuthorizationInterceptor verifyAuthorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(verifyAuthorizationInterceptor);
    }

    @Autowired
    public void setVerifyAuthorizationInterceptor(VerifyAuthorizationInterceptor verifyAuthorizationInterceptor) {
        this.verifyAuthorizationInterceptor = verifyAuthorizationInterceptor;
    }
}
