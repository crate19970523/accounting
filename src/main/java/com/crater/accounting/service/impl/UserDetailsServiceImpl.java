package com.crater.accounting.service.impl;

import com.crater.accounting.bean.database.UserDataPojo;
import com.crater.accounting.bean.service.userDetailsService.UserDetailsServiceDto;
import com.crater.accounting.dao.UserDataDao;
import com.crater.accounting.dao.UserRedisDao;
import com.crater.accounting.exception.DbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDataDao userDataDao;

    private UserRedisDao userRedisDao;

    @Override
    public UserDetailsServiceDto loadUserByUsername(String username) throws UsernameNotFoundException {
        var userData = callRedisGetUserData(username);
        if (userData.isEmpty()) {
            userData = callDbSelectUserData(username);
        }
        return userData.orElse(null);
    }

    private Optional<UserDetailsServiceDto> callRedisGetUserData(String username) {
        try {
            var userData = userRedisDao.getUsers(username);
            return userData == null ? Optional.empty() : Optional.of(new UserDetailsServiceDto(userData.userName(),
                    userData.password(), List.of(new SimpleGrantedAuthority("admin"))));
        } catch (Exception e) {
            throw new DbException("Error getting user data from redis", e);
        }
    }

    private Optional<UserDetailsServiceDto> callDbSelectUserData(String username) {
        try {
            var userData = userDataDao.select(new UserDataPojo().setUserName(username)).getFirst();
            return Optional.of(new UserDetailsServiceDto(userData.userName(), userData.password(),
                    List.of(new SimpleGrantedAuthority("admin"))));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DbException("Error selecting user data from database", e);
        }
    }

    @Autowired
    public void setUserDataDao(UserDataDao userDataDao) {
        this.userDataDao = userDataDao;
    }

    @Autowired
    public void setUserRedisDao(UserRedisDao userRedisDao) {
        this.userRedisDao = userRedisDao;
    }
}
