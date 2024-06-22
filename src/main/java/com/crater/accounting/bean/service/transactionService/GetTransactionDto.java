package com.crater.accounting.bean.service.transactionService;

import java.time.LocalDate;

public record GetTransactionDto(Integer serNo, Integer consumptionCategorySerNo, String name,
                                LocalDate queryStartDate, LocalDate queryEndDate) {
}
