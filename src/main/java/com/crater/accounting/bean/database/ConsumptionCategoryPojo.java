package com.crater.accounting.bean.database;

import java.time.LocalDateTime;
import java.util.Objects;

public class ConsumptionCategoryPojo {
    private Integer serNo;
    private String name;
    private Boolean isForSaving;
    private Boolean isAutoSaving;
    private LocalDateTime createTime;
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;
    private Integer autoSavingDate;

    public ConsumptionCategoryPojo() {
    }

    public ConsumptionCategoryPojo(Integer serNo, String name, Boolean isForSaving, Boolean isAutoSaving,
                                   LocalDateTime createTime, String createUser, LocalDateTime updateTime,
                                   String updateUser, Integer autoSavingDate) {
        this.serNo = serNo;
        this.name = name;
        this.isForSaving = isForSaving;
        this.isAutoSaving = isAutoSaving;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.autoSavingDate = autoSavingDate;
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

    public ConsumptionCategoryPojo setAutoSaving(Boolean autoSaving) {
        isAutoSaving = autoSaving;
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

    public ConsumptionCategoryPojo setAutoSavingDate(Integer autoSavingDate) {
        this.autoSavingDate = autoSavingDate;
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

    public Boolean isAutoSaving() {
        return isAutoSaving;
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

    public Integer autoSavingDate() {
        return autoSavingDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ConsumptionCategoryPojo) obj;
        return Objects.equals(this.serNo, that.serNo) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.isForSaving, that.isForSaving) &&
                Objects.equals(this.isAutoSaving, that.isAutoSaving) &&
                Objects.equals(this.createTime, that.createTime) &&
                Objects.equals(this.createUser, that.createUser) &&
                Objects.equals(this.updateTime, that.updateTime) &&
                Objects.equals(this.updateUser, that.updateUser) &&
                Objects.equals(this.autoSavingDate, that.autoSavingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serNo, name, isForSaving, isAutoSaving, createTime, createUser, updateTime, updateUser, autoSavingDate);
    }

    @Override
    public String toString() {
        return "ConsumptionCategoryPojo[" +
                "serNo=" + serNo + ", " +
                "name=" + name + ", " +
                "isForSaving=" + isForSaving + ", " +
                "isAutoSaving=" + isAutoSaving + ", " +
                "createTime=" + createTime + ", " +
                "createUser=" + createUser + ", " +
                "updateTime=" + updateTime + ", " +
                "updateUser=" + updateUser + ", " +
                "autoSavingDate=" + autoSavingDate + ']';
    }

}
