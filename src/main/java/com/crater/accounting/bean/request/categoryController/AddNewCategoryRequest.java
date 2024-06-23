package com.crater.accounting.bean.request.categoryController;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record AddNewCategoryRequest(@Schema(description = "category name", example = "test") @NotNull @NotEmpty String name,
                                    @Schema(description = "是否用於存錢", example = "false") @NotNull @NotEmpty Boolean isForSaving,
                                    @Schema(description = "狀態, active 或 inactive", example = "active")Boolean isActive) {
}
