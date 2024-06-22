package com.crater.accounting.bean.service.transactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetTransactionResultDto(Integer serNo, Integer consumptionCategorySerNo, String name, BigDecimal amount,
                                      LocalDateTime createTime, String crateUser, LocalDateTime updateTime, String updateUser,
                                      LocalDateTime transactionTime) {

}
