package com.crater.accounting.bean.response.categoryController;

public record CategoryData(int serialNo, String name, String createTime,
                           String createUser, String updateTime, String updateUser, boolean isForSaving,
                           boolean isAutoSaving, Integer autoSavingDate) {
}
