package com.crater.accounting.service.impl;

import com.crater.accounting.bean.database.AutoSavingPojo;
import com.crater.accounting.bean.database.TransactionPojo;
import com.crater.accounting.dao.AutoSavingDao;
import com.crater.accounting.dao.TransactionDao;
import com.crater.accounting.service.AutoSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AutoSavingServiceImpl implements AutoSavingService {

    private AutoSavingDao autoSavingDao;
    private TransactionDao transactionDao;

    @Override
    @Transactional
    public void autoSave() {
        var autoSavingDatas = callDaoGetAutoSaving();
        var insertTransactionDatas = generateWillInsertTransaction(autoSavingDatas);
        callDaoInsertTransaction(insertTransactionDatas);
    }

    private List<AutoSavingPojo> callDaoGetAutoSaving() {
        try {
            return autoSavingDao.select(new AutoSavingPojo());
        } catch (Exception e) {
            throw new RuntimeException("Error calling DAO to get auto saving data", e);
        }
    }

    private List<TransactionPojo> generateWillInsertTransaction(List<AutoSavingPojo> autoSavingDatas) {
        return autoSavingDatas.stream().filter(o -> LocalDateTime.now().getDayOfMonth() == o.autoSavingDate())
                .map(o -> new TransactionPojo(null, o.categorySerialNo(), "saving", o.autoSavingAmount(),
                        LocalDateTime.now(), "system", null, null, LocalDateTime.now(),
                        null, null, o.userId())).toList();
    }

    private void callDaoInsertTransaction(List<TransactionPojo> insertTransactionDatas) {
        try {
            insertTransactionDatas.forEach(o -> transactionDao.insert(o));
        } catch (Exception e) {
            throw new RuntimeException("Error calling DAO to insert transaction data", e);
        }
    }

    @Autowired
    public void setAutoSavingDao(AutoSavingDao autoSavingDao) {
        this.autoSavingDao = autoSavingDao;
    }

    @Autowired
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
}
