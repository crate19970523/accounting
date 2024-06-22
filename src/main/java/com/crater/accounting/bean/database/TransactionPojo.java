package com.crater.accounting.bean.database;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionPojo {
    private Integer serNo;
    private Integer consumptionCategorySerNo;
    private String name;
    private BigDecimal amount;
    private LocalDateTime createTime;
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;
    private LocalDateTime transactionTime;
    private LocalDateTime queryTransactionStartTime;
    private LocalDateTime queryTransactionEndTime;

    public TransactionPojo() {
    }

    public TransactionPojo setSerNo(Integer serNo) {
        this.serNo = serNo;
        return this;
    }

    public TransactionPojo setConsumptionCategorySerNo(Integer consumptionCategorySerNo) {
        this.consumptionCategorySerNo = consumptionCategorySerNo;
        return this;
    }

    public TransactionPojo setName(String name) {
        this.name = name;
        return this;
    }

    public TransactionPojo setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionPojo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public TransactionPojo setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public TransactionPojo setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public TransactionPojo setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public TransactionPojo setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
        return this;
    }

    public TransactionPojo setQueryTransactionStartTime(LocalDateTime queryTransactionStartTime) {
        this.queryTransactionStartTime = queryTransactionStartTime;
        return this;
    }

    public TransactionPojo setQueryTransactionEndTime(LocalDateTime queryTransactionEndTime) {
        this.queryTransactionEndTime = queryTransactionEndTime;
        return this;
    }

    public TransactionPojo(Integer serNo, Integer consumptionCategorySerNo, String name, BigDecimal amount,
                           LocalDateTime createTime, String createUser, LocalDateTime updateTime, String updateUser,
                           LocalDateTime transactionTime, LocalDateTime queryTransactionStartTime,
                           LocalDateTime queryTransactionEndTime) {
        this.serNo = serNo;
        this.consumptionCategorySerNo = consumptionCategorySerNo;
        this.name = name;
        this.amount = amount;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.transactionTime = transactionTime;
        this.queryTransactionStartTime = queryTransactionStartTime;
        this.queryTransactionEndTime = queryTransactionEndTime;
    }

    public Integer serNo() {
        return serNo;
    }

    public Integer consumptionCategorySerNo() {
        return consumptionCategorySerNo;
    }

    public String name() {
        return name;
    }

    public BigDecimal amount() {
        return amount;
    }

    public LocalDateTime createTime() {
        return createTime;
    }

    public String crateUser() {
        return createUser;
    }

    public LocalDateTime updateTime() {
        return updateTime;
    }

    public String updateUser() {
        return updateUser;
    }

    public LocalDateTime transactionTime() {
        return transactionTime;
    }

    public LocalDateTime queryTransactionStartTime() {
        return queryTransactionStartTime;
    }

    public LocalDateTime queryTransactionEndTime() {
        return queryTransactionEndTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TransactionPojo) obj;
        return Objects.equals(this.serNo, that.serNo) &&
                Objects.equals(this.consumptionCategorySerNo, that.consumptionCategorySerNo) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.amount, that.amount) &&
                Objects.equals(this.createTime, that.createTime) &&
                Objects.equals(this.createUser, that.createUser) &&
                Objects.equals(this.updateTime, that.updateTime) &&
                Objects.equals(this.updateUser, that.updateUser) &&
                Objects.equals(this.transactionTime, that.transactionTime) &&
                Objects.equals(this.queryTransactionStartTime, that.queryTransactionStartTime) &&
                Objects.equals(this.queryTransactionEndTime, that.queryTransactionEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serNo, consumptionCategorySerNo, name, amount, createTime, createUser, updateTime, updateUser, transactionTime, queryTransactionStartTime, queryTransactionEndTime);
    }

    @Override
    public String toString() {
        return "TransactionPojo[" +
                "serNo=" + serNo + ", " +
                "consumptionCategorySerNo=" + consumptionCategorySerNo + ", " +
                "name=" + name + ", " +
                "amount=" + amount + ", " +
                "createTime=" + createTime + ", " +
                "crateUser=" + createUser + ", " +
                "updateTime=" + updateTime + ", " +
                "updateUser=" + updateUser + ", " +
                "transactionTime=" + transactionTime + ", " +
                "queryTransactionStartTime=" + queryTransactionStartTime + ", " +
                "queryTransactionEndTime=" + queryTransactionEndTime + ']';
    }

}