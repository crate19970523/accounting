package com.crater.accounting.bean.response;

public record Status(boolean isSuccess, String errorType, String errorDetail) {
    public static Status generateSuccessStatus() {
        return new Status(true, "success", null);
    }
}
