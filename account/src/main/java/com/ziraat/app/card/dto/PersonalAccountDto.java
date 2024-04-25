package com.ziraat.app.card.dto;

import com.ziraat.app.card.model.PersonalAccount;
import com.ziraat.app.card.model.enums.AccountState;

import java.time.LocalDateTime;

public record PersonalAccountDto(
         String accountNumber,
         String accountHolderName,
         double availableBalance,
         double balance,
         LocalDateTime createdDate,
         AccountState accountState,  // Enum
         String IBAN
) {
    public static PersonalAccountDto convert(PersonalAccount personalAccount) {
        return new PersonalAccountDto(
                personalAccount.getAccountNumber(),
                personalAccount.getAccountHolderName(),
                personalAccount.getAvailableBalance(),
                personalAccount.getBalance(),
                personalAccount.getCreatedDate(),
                personalAccount.getAccountState(),
                personalAccount.getIBAN()
        );
    }

}
