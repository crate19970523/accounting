package com.crater.accounting.bean.request.transactionController;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AddNewTransactionRequest(@NotNull Integer categorySerialNo, @NotNull @NotEmpty String name, @NotNull
                                       BigDecimal amount, String transactionTime) {
}
