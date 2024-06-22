package com.crater.accounting.dao;

import com.crater.accounting.bean.database.TransactionPojo;

import java.util.List;

public interface TransactionDao {
    void insert(TransactionPojo transaction);
    void update(TransactionPojo transaction);
    void delete(TransactionPojo transaction);
    List<TransactionPojo> select(TransactionPojo transaction);
}
