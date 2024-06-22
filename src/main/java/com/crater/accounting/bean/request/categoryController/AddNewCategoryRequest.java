package com.crater.accounting.bean.request.categoryController;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record AddNewCategoryRequest(@Schema(description = "category name", example = "test") @NotNull @NotEmpty String name,
                                    @Schema(description = "是否用於存錢", example = "false") @NotNull @NotEmpty boolean isForSaving,
                                    @Schema(description = "是否自動存錢，如果是 autoSavingDate can't be empty", example = "false") @NotNull @NotEmpty boolean isAutoSaving,
                                    @Schema(description = "每月的哪一天存錢, if isAutoSaving false 不會讀取這個參數", example = "5") Integer autoSavingDate) {
}
