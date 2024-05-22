package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.AccountTransaction;
import com.ziraat.app.account.model.enums.TransactionType;

import java.time.LocalDateTime;

public record AccountTransactionDto(
        TransactionType transactionType,  // Enum
        String description,
        double amount,
        double availableBalance,
        String senderAccountNumber,
        String receiverAccountNumber

        ) {
}
