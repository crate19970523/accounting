package com.crater.accounting.bean.response.categoryController;

import com.crater.accounting.bean.response.Status;
import io.swagger.v3.oas.annotations.media.Schema;

public record AddNewCategoryResponse(@Schema(description = "API 狀態") Status status) {
}
