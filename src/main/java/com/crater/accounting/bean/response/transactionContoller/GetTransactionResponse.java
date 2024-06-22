package com.crater.accounting.bean.response.transactionContoller;

import com.crater.accounting.bean.response.Status;

import java.util.List;

public record GetTransactionResponse(Status status, List<Transaction> transactions) {
}
