package com.crater.accounting.bean.response.categoryController;

import com.crater.accounting.bean.response.Status;

import java.util.List;

public record GetCategoryIndexResponse(Status status, List<CategoryIndexData> categoryIndexData) {
}
