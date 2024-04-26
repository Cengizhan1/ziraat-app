package com.ziraat.app.account.dto;

public record PersonalAccountDeleteRequest(
        String accountNumber,
        String accountHolderName
        ) {
}
