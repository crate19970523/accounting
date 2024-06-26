package com.crater.accounting.bean.database;

import java.time.LocalDateTime;

public class ConsumptionCategoryPojo {
    private Integer serNo;
    private String name;
    private Boolean isForSaving;
    private LocalDateTime createTime;
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;
    private Boolean isActive;
    private String userId;

    public ConsumptionCategoryPojo() {
    }

    public ConsumptionCategoryPojo(Integer serNo, String name, Boolean isForSaving,
                                   LocalDateTime createTime, String createUser, LocalDateTime updateTime,
                                   String updateUser, Boolean isActive, String userId) {
        this.serNo = serNo;
        this.name = name;
        this.isForSaving = isForSaving;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.isActive = isActive;
        this.userId = userId;
    }

    public ConsumptionCategoryPojo setSerNo(Integer serNo) {
        this.serNo = serNo;
        return this;
    }

    public ConsumptionCategoryPojo setName(String name) {
        this.name = name;
        return this;
    }

    public ConsumptionCategoryPojo setForSaving(Boolean forSaving) {
        isForSaving = forSaving;
        return this;
    }

    public ConsumptionCategoryPojo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public ConsumptionCategoryPojo setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public ConsumptionCategoryPojo setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ConsumptionCategoryPojo setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public ConsumptionCategoryPojo setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public ConsumptionCategoryPojo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer serNo() {
        return serNo;
    }

    public String name() {
        return name;
    }

    public Boolean isForSaving() {
        return isForSaving;
    }

    public LocalDateTime createTime() {
        return createTime;
    }

    public String createUser() {
        return createUser;
    }

    public LocalDateTime updateTime() {
        return updateTime;
    }

    public String updateUser() {
        return updateUser;
    }

    public Boolean isActive() {
        return isActive;
    }

    public String userId() {
        return userId;
    }
}
