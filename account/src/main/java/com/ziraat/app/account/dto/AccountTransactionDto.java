package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.AccountTransection;
import com.ziraat.app.account.model.enums.TransactionType;

import java.time.LocalDateTime;

public record AccountTransactionDto(
        TransactionType transactionType,  // Enum
        String description,
        double amount,
        double availableBalance,
        LocalDateTime transactionDate,
        String senderAccountNumber,
        String IBAN,
        String userId,
        double balance

        ) {
    public static AccountTransactionDto convert(AccountTransection accountTransection){
        return new AccountTransactionDto(
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
