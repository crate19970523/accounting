package com.crater.accounting.dao;

import com.crater.accounting.bean.database.UserDataPojo;

import java.util.List;

public interface UserDataDao {
    void insert(UserDataPojo userDataPojo);
    void update(UserDataPojo userDataPojo);
    List<UserDataPojo> select(UserDataPojo userDataPojo);
    void delete(UserDataPojo userDataPojo);
}
