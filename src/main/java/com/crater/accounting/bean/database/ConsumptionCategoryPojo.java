package com.crater.accounting.bean.database;

import java.time.LocalDateTime;
import java.util.Objects;

public class ConsumptionCategoryPojo {
    private Integer serNo;
    private String name;
    private Boolean isForSaving;
    private LocalDateTime createTime;
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;
    private Boolean isActive;

    public ConsumptionCategoryPojo() {
    }

    public ConsumptionCategoryPojo(Integer serNo, String name, Boolean isForSaving,
                                   LocalDateTime createTime, String createUser, LocalDateTime updateTime,
                                   String updateUser, Boolean isActive) {
        this.serNo = serNo;
        this.name = name;
        this.isForSaving = isForSaving;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.isActive = isActive;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ConsumptionCategoryPojo) obj;
        return Objects.equals(this.serNo, that.serNo) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.isForSaving, that.isForSaving) &&
                Objects.equals(this.createTime, that.createTime) &&
                Objects.equals(this.createUser, that.createUser) &&
                Objects.equals(this.updateTime, that.updateTime) &&
                Objects.equals(this.updateUser, that.updateUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serNo, name, isForSaving, createTime, createUser, updateTime, updateUser);
    }

    @Override
    public String toString() {
        return "ConsumptionCategoryPojo[" +
                "serNo=" + serNo + ", " +
                "name=" + name + ", " +
                "isForSaving=" + isForSaving + ", " +
                "createTime=" + createTime + ", " +
                "createUser=" + createUser + ", " +
                "updateTime=" + updateTime + ", " +
                "updateUser=" + updateUser + ", " + ']';
    }

}
