package com.crater.accounting.bean.response.categoryController;

import com.crater.accounting.bean.response.Status;

public record GetCategoryResponse(Status status, CategoryData categoryData) {
}
