package com.crater.accounting.security;

import com.crater.accounting.bean.database.UserRedisDataPojo;
import com.crater.accounting.dao.UserRedisDao;
import com.crater.accounting.exception.DbException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private UserRedisDao userRedisDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            var username = authentication.getName();
            var password = authentication.getCredentials().toString();
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                throw new AuthenticationServiceException("Username or password is missing");
            }
            var userData = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userData.getPassword())) {
                throw new AuthenticationServiceException("Invalid password");
            }
            updateUserDetailsToRedis(userData);
            return new UsernamePasswordAuthenticationToken(username, password, userData.getAuthorities());
        } catch (Exception e) {
            log.error("authentication failed", e);
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    private void updateUserDetailsToRedis(UserDetails user) {
        try {
            var userData = new UserRedisDataPojo(user.getUsername(), user.getPassword(), true);
            userRedisDao.updateUser(userData);
        } catch (Exception e) {
            throw new DbException("Failed to update user details to redis", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setUserRedisDao(UserRedisDao userRedisDao) {
        this.userRedisDao = userRedisDao;
    }
}
