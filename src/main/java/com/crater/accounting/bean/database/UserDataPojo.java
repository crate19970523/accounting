package com.crater.accounting.bean.database;

import java.time.LocalDateTime;

public class UserDataPojo {
    String userName;
    String password;
    Boolean isActive;
    LocalDateTime createTime;
    String createUser;
    LocalDateTime updateTime;
    String updateUser;

    public UserDataPojo() {
    }

    public UserDataPojo(String userName, String password, Boolean isActive, LocalDateTime createTime, String createUser,
                        LocalDateTime updateTime, String updateUser) {
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }

    public String userName() {
        return userName;
    }

    public UserDataPojo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String password() {
        return password;
    }

    public UserDataPojo setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean isActive() {
        return isActive;
    }

    public UserDataPojo setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public LocalDateTime createTime() {
        return createTime;
    }

    public UserDataPojo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String createUser() {
        return createUser;
    }

    public UserDataPojo setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public LocalDateTime updateTime() {
        return updateTime;
    }

    public UserDataPojo setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String updateUser() {
        return updateUser;
    }

    public UserDataPojo setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }
}
