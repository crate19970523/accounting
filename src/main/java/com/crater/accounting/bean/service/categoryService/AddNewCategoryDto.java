package com.crater.accounting.bean.service.categoryService;

public record AddNewCategoryDto(String name, String userId, boolean isForSaving, boolean isActive) {
}
