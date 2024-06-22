package com.crater.accounting.bean.service.transactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateTransactionDto(int serialNo, Integer categorySerialNo, String name, BigDecimal amount, String userId,
                                   LocalDateTime transactionTime) {
}
