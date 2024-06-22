package com.crater.accounting.bean.request.transactionController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateTransactionRequest (Integer serialNo, Integer categorySerialNo, String name, BigDecimal amount,
                                        LocalDateTime transactionTime) {
}
