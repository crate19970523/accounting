package com.crater.accounting.service.impl;

import com.crater.accounting.bean.database.ConsumptionCategoryPojo;
import com.crater.accounting.bean.database.TransactionPojo;
import com.crater.accounting.bean.service.categoryService.AddNewCategoryDto;
import com.crater.accounting.bean.service.categoryService.QueryCategoryDto;
import com.crater.accounting.bean.service.categoryService.QueryCategoryResultDto;
import com.crater.accounting.bean.service.categoryService.UpdateCategoryDto;
import com.crater.accounting.dao.ConsumptionCategoryDao;
import com.crater.accounting.dao.TransactionDao;
import com.crater.accounting.exception.DataNotFoundException;
import com.crater.accounting.exception.DbException;
import com.crater.accounting.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private ConsumptionCategoryDao consumptionCategoryDao;
    private TransactionDao transactionDao;

    @Override
    @Transactional
    public void addCategory(AddNewCategoryDto addNewCategoryDto) {
        var categoryPojo = generateCategoryPojo(addNewCategoryDto);
        callDaoToInsertCategory(categoryPojo);
    }

    private ConsumptionCategoryPojo generateCategoryPojo(AddNewCategoryDto addNewCategoryDto) {
        return new ConsumptionCategoryPojo(null, addNewCategoryDto.name(), addNewCategoryDto.isForSaving(),
                LocalDateTime.now(), addNewCategoryDto.userId(),
                null, null, addNewCategoryDto.isActive(),
                addNewCategoryDto.userId());
    }

    private void callDaoToInsertCategory(ConsumptionCategoryPojo categoryPojo) {
        try {
            consumptionCategoryDao.insert(categoryPojo);
        } catch (Exception e) {
            throw new DbException("insert category failed", e);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(int serialNo) {
        if (checkIsCanDeleteCategory(serialNo)) {
            throw new RuntimeException("have transaction data, can't delete category");
        }
        callDaoToDeleteCategory(new ConsumptionCategoryPojo().setSerNo(serialNo));
    }

    private boolean checkIsCanDeleteCategory(int categorySerialNo) {
        return !transactionDao.select(new TransactionPojo().setConsumptionCategorySerNo(categorySerialNo)).isEmpty();
    }

    private void callDaoToDeleteCategory(ConsumptionCategoryPojo categoryPojo) {
        try {
            consumptionCategoryDao.delete(categoryPojo);
        } catch (Exception e) {
            throw new DbException("delete category failed", e);
        }
    }

    @Override
    @Transactional
    public void updateCategory(UpdateCategoryDto updateCategoryDto) {
        var categoryPojo = generateCategoryPojo(updateCategoryDto);
        callDaoToUpdateCategory(categoryPojo);
    }

    private ConsumptionCategoryPojo generateCategoryPojo(UpdateCategoryDto updateCategoryDto) {
        return new ConsumptionCategoryPojo(updateCategoryDto.serialNo(), updateCategoryDto.name(),
                updateCategoryDto.isForSaving(), null, null,
                LocalDateTime.now(), updateCategoryDto.userId(),
                updateCategoryDto.isActive(), updateCategoryDto.userId());
    }

    private void callDaoToUpdateCategory(ConsumptionCategoryPojo categoryPojo) {
        try {
            consumptionCategoryDao.update(categoryPojo);
        } catch (Exception e) {
            throw new DbException("update category failed", e);
        }
    }

    @Override
    public QueryCategoryResultDto queryCategory(int serialNo) {
        var categoryPojo = generateCategoryPojo(serialNo);
        var queryResult = callDaoQueryCategory(categoryPojo);
        return generateQueryCategoryResultDto(queryResult);
    }

    private ConsumptionCategoryPojo generateCategoryPojo(int serialNo) {
        return new ConsumptionCategoryPojo(serialNo, null, null, null, null,
                null, null, null, null);
    }

    private ConsumptionCategoryPojo callDaoQueryCategory(ConsumptionCategoryPojo categoryPojo) {
        try {
            var queryResult = consumptionCategoryDao.selectAllColumn(categoryPojo);
            if (queryResult.isEmpty()) {
                throw new DataNotFoundException("cant found category serialNo: " + categoryPojo.serNo() + " data");
            }
            return queryResult.getFirst();
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DbException("query category failed", e);
        }
    }

    private QueryCategoryResultDto generateQueryCategoryResultDto(ConsumptionCategoryPojo categoryPojo) {
        return new QueryCategoryResultDto(categoryPojo.serNo(), categoryPojo.name(), categoryPojo.createTime(),
                categoryPojo.createUser(), categoryPojo.updateTime(), categoryPojo.updateUser(),
                categoryPojo.isForSaving(), categoryPojo.isActive());
    }

    @Override
    public List<QueryCategoryResultDto> queryCategoriesIndex(QueryCategoryDto queryCategoryResultDto) {
        var categoryPojo = generateCategoryPojo(queryCategoryResultDto);
        var queryResult = callDaoQueryCategoriesIndex(categoryPojo);
        return generateQueryCategoryResultDtoList(queryResult);
    }

    private ConsumptionCategoryPojo generateCategoryPojo(QueryCategoryDto queryCategoryDto) {
        return new ConsumptionCategoryPojo(queryCategoryDto.serialNo(), queryCategoryDto.name(), null,
                null, null, null, null, null,
                queryCategoryDto.userId());
    }

    private List<ConsumptionCategoryPojo> callDaoQueryCategoriesIndex(ConsumptionCategoryPojo categoryPojo) {
        try {
            return consumptionCategoryDao.selectSerNoAndName(categoryPojo);
        } catch (Exception e) {
            throw new DbException("query categories index failed", e);
        }
    }

    private List<QueryCategoryResultDto> generateQueryCategoryResultDtoList(List<ConsumptionCategoryPojo> consumptionCategoryPojos) {
        return consumptionCategoryPojos.stream().map(c -> new QueryCategoryResultDto(c.serNo(), c.name(), c.createTime(),
                c.createUser(), c.updateTime(), c.updateUser(), c.isForSaving(), c.isActive())).toList();
    }

    @Autowired
    public void setConsumptionCategoryDao(ConsumptionCategoryDao consumptionCategoryDao) {
        this.consumptionCategoryDao = consumptionCategoryDao;
    }

    @Autowired
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
}
