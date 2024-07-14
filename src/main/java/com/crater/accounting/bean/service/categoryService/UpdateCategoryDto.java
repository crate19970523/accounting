package com.crater.accounting.bean.service.categoryService;

public record UpdateCategoryDto(int serialNo, String name, Boolean isForSaving, String authorization, Boolean isActive) {
}
