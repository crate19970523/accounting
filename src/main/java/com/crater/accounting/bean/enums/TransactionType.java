package com.crater.accounting.bean.enums;

public enum TransactionType {
    normal, isForSaving, isTakeFromSaving, isManualSaving;

    @Override
    public String toString() {
        String result;
        switch (this) {
            case normal -> result = "normal";
            case isForSaving -> result = "for saving";
            case isTakeFromSaving -> result = "take from saving";
            case isManualSaving -> result = "manual saving";
            default -> result = "unknown";
        }
        return result;
    }
}
