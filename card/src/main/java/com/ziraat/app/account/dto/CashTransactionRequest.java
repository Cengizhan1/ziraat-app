package com.ziraat.app.account.dto;

public record CashTransactionRequest(
         String cardNumber,
         String cvc,
         String expireDate,
         String cardPin,
         double amount,
         String accountNumber
) {}
