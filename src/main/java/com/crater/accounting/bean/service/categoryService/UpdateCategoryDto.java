package com.crater.accounting.bean.service.categoryService;

public record UpdateCategoryDto(int serialNo, String name, boolean isForSaving, String user, Boolean isActive) {
}
