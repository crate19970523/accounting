package com.crater.accounting.bean.database;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoSavingPojo {
    private Integer serialNo;
    private Integer categorySerialNo;
    private Integer autoSavingDate;
    private BigDecimal autoSavingAmount;
    private LocalDateTime createTime;
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;
    private String userId;

    public AutoSavingPojo() {
    }

    public AutoSavingPojo(int serialNo, int categorySerialNo, int autoSavingDate, BigDecimal autoSavingAmount,
                          LocalDateTime createTime, String createUser, LocalDateTime updateTime, String updateUser,
                          String userId) {
        this.serialNo = serialNo;
        this.categorySerialNo = categorySerialNo;
        this.autoSavingDate = autoSavingDate;
        this.autoSavingAmount = autoSavingAmount;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.userId = userId;
    }

    public int serialNo() {
        return serialNo;
    }

    public AutoSavingPojo setSerialNo(int serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public int categorySerialNo() {
        return categorySerialNo;
    }

    public AutoSavingPojo setCategorySerialNo(int categorySerialNo) {
        this.categorySerialNo = categorySerialNo;
        return this;
    }

    public int autoSavingDate() {
        return autoSavingDate;
    }

    public AutoSavingPojo setAutoSavingDate(int autoSavingDate) {
        this.autoSavingDate = autoSavingDate;
        return this;
    }

    public BigDecimal autoSavingAmount() {
        return autoSavingAmount;
    }

    public AutoSavingPojo setAutoSavingAmount(BigDecimal autoSavingAmount) {
        this.autoSavingAmount = autoSavingAmount;
        return this;
    }

    public LocalDateTime createTime() {
        return createTime;
    }

    public AutoSavingPojo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String createUser() {
        return createUser;
    }

    public AutoSavingPojo setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public LocalDateTime updateTime() {
        return updateTime;
    }

    public AutoSavingPojo setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String updateUser() {
        return updateUser;
    }

    public AutoSavingPojo setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public String userId() {
        return userId;
    }

    public AutoSavingPojo setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
