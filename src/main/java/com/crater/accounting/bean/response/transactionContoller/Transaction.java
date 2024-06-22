package com.crater.accounting.bean.response.transactionContoller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(int serialNo, int categorySerialNo, String name, BigDecimal amount, LocalDateTime createTime,
                          String createUserId, LocalDateTime updateTime, String updateUserId, LocalDateTime transactionTime) {
}
