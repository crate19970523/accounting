package com.crater.accounting.bean.response.categoryController;

import com.crater.accounting.bean.response.Status;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCategoryResponse(@Schema(description = "API 狀態說明") Status status) {
}
