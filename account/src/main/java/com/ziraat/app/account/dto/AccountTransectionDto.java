package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.AccountTransection;

import java.time.LocalDateTime;

public record AccountTransectionDto(
        String transactionType,  // Enum
        String description,
        double amount,
        double availableBalance,
        LocalDateTime transactionDate,
        String senderAccountNumber,
        String IBAN,
        String userId,
        double balance

        ) {
    public static AccountTransectionDto convert(AccountTransection accountTransection){
        return new AccountTransectionDto(
                accountTransection.getTransactionType(),
                accountTransection.getDescription(),
                accountTransection.getAmount(),
                accountTransection.getAvailableBalance(),
                accountTransection.getTransactionDate(),
                accountTransection.getSenderAccountNumber(),
                accountTransection.getIBAN(),
                accountTransection.getUserId(),
                accountTransection.getBalance()
        );
    }
}
