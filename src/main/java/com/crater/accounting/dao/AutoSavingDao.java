package com.crater.accounting.dao;

import com.crater.accounting.bean.database.AutoSavingPojo;

import java.util.List;

public interface AutoSavingDao {
    List<AutoSavingPojo> select(AutoSavingPojo autoSavingPojo);
    void insert(AutoSavingPojo autoSavingPojo);
    void update(AutoSavingPojo autoSavingPojo);
    void delete(AutoSavingPojo autoSavingPojo);
}
