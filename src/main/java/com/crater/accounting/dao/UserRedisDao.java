package com.crater.accounting.dao;

import com.crater.accounting.bean.database.UserRedisDataPojo;

public interface UserRedisDao {
    void updateUser(UserRedisDataPojo userDataPojo);
    UserRedisDataPojo getUsers(String username);
}
