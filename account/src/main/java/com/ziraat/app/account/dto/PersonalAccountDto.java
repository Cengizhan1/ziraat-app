package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.PersonalAccount;
import com.ziraat.app.account.model.enums.AccountState;

import java.time.LocalDateTime;

public record PersonalAccountDto(
         String accountNumber,
         String accountHolderName,
         double availableBalance,
         double balance,
         LocalDateTime createdDate,
         AccountState accountState,  // Enum
         String IBAN,
         String userId,
         String countryCode
) {
    public static PersonalAccountDto convert(PersonalAccount personalAccount) {
        return new PersonalAccountDto(
                personalAccount.getAccountNumber(),
                personalAccount.getAccountHolderName(),
                personalAccount.getAvailableBalance(),
                personalAccount.getBalance(),
                personalAccount.getCreatedDate(),
                personalAccount.getAccountState(),
                personalAccount.getIBAN(),
                personalAccount.getUserId(),
                personalAccount.getCountryCode()
        );
    }
}
