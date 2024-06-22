package com.crater.accounting.service;

import com.crater.accounting.bean.service.transactionService.AddTransactionDto;
import com.crater.accounting.bean.service.transactionService.GetTransactionDto;
import com.crater.accounting.bean.service.transactionService.GetTransactionResultDto;
import com.crater.accounting.bean.service.transactionService.UpdateTransactionDto;

import java.util.List;

public interface TransactionService {
    void addTransaction(AddTransactionDto addTransactionDto);
    void deleteTransaction(int transactionSerialNo);
    List<GetTransactionResultDto> getTransaction(GetTransactionDto transactionDto);
    void updateTransaction(UpdateTransactionDto updateTransactionDto);
}
