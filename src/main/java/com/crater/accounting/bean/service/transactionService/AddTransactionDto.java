package com.crater.accounting.bean.service.transactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AddTransactionDto(int categorySerialNo, String name, BigDecimal amount, LocalDateTime transactionTime,
                                String userId) {
}
