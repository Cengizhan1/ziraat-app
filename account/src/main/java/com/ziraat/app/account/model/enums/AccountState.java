package com.ziraat.app.account.model.enums;

public enum AccountState {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    BLOCKED("Blocked"),
    CLOSED("Closed");

    private final String label;

    AccountState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
