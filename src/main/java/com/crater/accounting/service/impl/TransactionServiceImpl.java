package com.crater.accounting.service.impl;

import com.crater.accounting.bean.database.TransactionPojo;
import com.crater.accounting.bean.service.transactionService.AddTransactionDto;
import com.crater.accounting.bean.service.transactionService.GetTransactionDto;
import com.crater.accounting.bean.service.transactionService.GetTransactionResultDto;
import com.crater.accounting.bean.service.transactionService.UpdateTransactionDto;
import com.crater.accounting.dao.TransactionDao;
import com.crater.accounting.exception.DbException;
import com.crater.accounting.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    @Override
    @Transactional
    public void addTransaction(AddTransactionDto addTransactionDto) {
        var insertTransactionPojo = generateAddTransactionPojo(addTransactionDto);
        callDaoToInsertTransaction(insertTransactionPojo);
    }

    private TransactionPojo generateAddTransactionPojo(AddTransactionDto addTransactionDto) {
        return new TransactionPojo(null, addTransactionDto.categorySerialNo(), addTransactionDto.name(),
                addTransactionDto.amount(), LocalDateTime.now(), addTransactionDto.userId(), null,
                null, addTransactionDto.transactionTime(), null, null,
                addTransactionDto.userId());
    }

    private void callDaoToInsertTransaction(TransactionPojo transactionPojo) {
        try {
            transactionDao.insert(transactionPojo);
        } catch (Exception e) {
            throw new DbException("Failed to insert transaction", e);
        }
    }

    @Override
    @Transactional
    public void deleteTransaction(int transactionSerialNo) {
        try {
            transactionDao.delete(new TransactionPojo(transactionSerialNo, null, null,
                    null, null, null, null, null, null,
                    null, null, null));
        } catch (Exception e) {
            throw new DbException("Failed to delete transaction", e);
        }
    }

    @Override
    public List<GetTransactionResultDto> getTransaction(GetTransactionDto transactionDto) {
        var queryTransactionPojo = generateQueryTransactionPojo(transactionDto);
        var transactionPojos = callDaoToQueryTransaction(queryTransactionPojo);
        return generateGetTransactionResultDtos(transactionPojos);
    }

    private TransactionPojo generateQueryTransactionPojo(GetTransactionDto transactionDto) {
        var startDateForQueryPojo = transactionDto.queryStartDate() == null ? null : transactionDto.queryStartDate().atStartOfDay();
        var endDateForQueryPojo = transactionDto.queryEndDate() == null ? null : transactionDto.queryEndDate().atTime(23, 59, 59);
        return new TransactionPojo(transactionDto.serNo(), transactionDto.consumptionCategorySerNo(), transactionDto.name(),
                null, null, null, null, null,
                null, startDateForQueryPojo, endDateForQueryPojo, transactionDto.userId());
    }

    private List<TransactionPojo> callDaoToQueryTransaction(TransactionPojo transactionPojo) {
        try {
            return transactionDao.select(transactionPojo);
        } catch (Exception e) {
            throw new DbException("Failed to query transaction", e);
        }
    }

    private List<GetTransactionResultDto> generateGetTransactionResultDtos(List<TransactionPojo> transactionPojos) {
        return transactionPojos.stream().map(t -> new GetTransactionResultDto(t.serNo(), t.consumptionCategorySerNo(),
                t.name(), t.amount(), t.createTime(), t.createUser(), t.updateTime(), t.updateUser(), t.transactionTime())).toList();
    }

    @Override
    @Transactional
    public void updateTransaction(UpdateTransactionDto updateTransactionDto) {
        var updateTransactionPojo = generateUpdateTransactionPojo(updateTransactionDto);
        callDaoToUpdateTransaction(updateTransactionPojo);
        transactionDao.update(updateTransactionPojo);
    }

    private TransactionPojo generateUpdateTransactionPojo(UpdateTransactionDto updateTransactionDto) {
        return new TransactionPojo(updateTransactionDto.serialNo(), updateTransactionDto.categorySerialNo(),
                updateTransactionDto.name(), updateTransactionDto.amount(), null, null,
                LocalDateTime.now(), updateTransactionDto.userId(),updateTransactionDto.transactionTime(),
                null, null, updateTransactionDto.userId());
    }

    private void callDaoToUpdateTransaction(TransactionPojo transactionPojo) {
        try {
            transactionDao.update(transactionPojo);
        } catch (Exception e) {
            throw new DbException("Failed to update transaction", e);
        }
    }

    @Autowired
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
}
