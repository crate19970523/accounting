package com.crater.accounting.bean.request.transactionController;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AddNewTransactionRequest(@Schema(description = "category 編號", defaultValue = "1") @NotNull Integer categorySerialNo,
                                       @Schema(description = "交易名稱", defaultValue = "test") @NotNull @NotEmpty String name, @NotNull
                                       @Schema(description = "交易金額", defaultValue = "100") BigDecimal amount,
                                       @Schema(description = "交易時間，格式： ISO_LOCAL_DATE_TIME", defaultValue = "2011-12-03T10:15:30") String transactionTime) {
}
