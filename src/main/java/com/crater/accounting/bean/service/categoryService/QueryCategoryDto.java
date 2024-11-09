package com.crater.accounting.bean.service.categoryService;

public record QueryCategoryDto(Integer serialNo, String name, boolean isActive, boolean isForSaving, String userId) {
}
