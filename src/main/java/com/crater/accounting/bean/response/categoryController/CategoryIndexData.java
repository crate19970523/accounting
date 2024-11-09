package com.crater.accounting.bean.response.categoryController;

public record CategoryIndexData(int serialNo, String name, boolean isForSaving, boolean isActive, String lastUpdateTime) {
}
