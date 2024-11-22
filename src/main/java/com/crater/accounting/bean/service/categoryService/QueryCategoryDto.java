package com.crater.accounting.bean.service.categoryService;

public record QueryCategoryDto(Integer serialNo, String name, Boolean isActive, Boolean isForSaving, String userId) {
}
