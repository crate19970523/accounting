package com.crater.accounting.dao;

import com.crater.accounting.bean.database.ConsumptionCategoryPojo;

import java.util.List;

public interface ConsumptionCategoryDao {
    void delete(ConsumptionCategoryPojo consumptionCategoryPojo);
    void insert(ConsumptionCategoryPojo consumptionCategoryPojo);

    void update(ConsumptionCategoryPojo consumptionCategoryPojo);

    List<ConsumptionCategoryPojo> selectSerNoAndName(ConsumptionCategoryPojo consumptionCategoryPojo);

    List<ConsumptionCategoryPojo> selectAllColumn(ConsumptionCategoryPojo consumptionCategoryPojo);
}
