package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.PersonalAccount;
import com.ziraat.app.account.model.enums.AccountState;

import java.time.LocalDateTime;

public record PersonalAccountDto(
         String accountNumber,
         String accountHolderName,
         double availableBalance,
         double balance,
         double totalBalance,
         LocalDateTime createdDate,
         AccountState accountState,  // Enum
         String IBAN,
         String userId
) {
    public static PersonalAccountDto convert(PersonalAccount personalAccount) {
        return new PersonalAccountDto(
                personalAccount.getAccountNumber(),
                personalAccount.getAccountHolderName(),
                personalAccount.getAvailableBalance(),
                personalAccount.getBalance(),
                0.0, // Varsayılan değer
                personalAccount.getCreatedDate(),
                personalAccount.getAccountState(),
                personalAccount.getIBAN(),
                personalAccount.getUserId()
        );
    }
}
