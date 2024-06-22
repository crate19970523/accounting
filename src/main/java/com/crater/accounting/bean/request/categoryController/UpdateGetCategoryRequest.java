package com.crater.accounting.bean.request.categoryController;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateGetCategoryRequest(@Schema(description = "will update category serialNo") @NotNull @NotEmpty Integer serialNo,
                                       @Schema(description = "want update category name", example = "test") @NotNull @NotEmpty String name,
                                       @Schema(description = "is for saving", example = "false") @NotNull @NotEmpty boolean isForSaving,
                                       @Schema(description = "is auto saving", example = "false")@NotNull @NotEmpty boolean isAutoSaving,
                                       @Schema(description = "auto saving date", example = "null") Integer autoSavingDate) {
}
