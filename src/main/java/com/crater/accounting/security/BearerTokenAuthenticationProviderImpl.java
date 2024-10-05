package com.crater.accounting.security;

import com.crater.accounting.dao.TokenDao;
import com.crater.craterlogin.bean.entity.redis.TokenPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class BearerTokenAuthenticationProviderImpl implements AuthenticationProvider {
    private TokenDao tokenDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin")); //目前沒有要做關於權限的功能，所以寫死 admin
        var token = authentication.getCredentials().toString();
        var queryResult = selectToken(token);
        var userData = queryResult.orElseThrow(() -> new InvalidBearerTokenException("Invalid token"));
        return new UsernamePasswordAuthenticationToken(userData.userId(), token, authorities);
    }

    private Optional<TokenPojo> selectToken(String token) {
        var queryResult = tokenDao.getByToken(token);
        return Optional.ofNullable(queryResult);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Autowired
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }
}
