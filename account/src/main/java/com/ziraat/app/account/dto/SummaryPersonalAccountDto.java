package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.PersonalAccount;
import com.ziraat.app.account.model.enums.AccountState;

import java.time.LocalDateTime;

public record SummaryPersonalAccountDto(
         String accountHolderName,
         double availableBalance,
         double balance
) {
    public static SummaryPersonalAccountDto convert(PersonalAccount personalAccount) {
        return new SummaryPersonalAccountDto(
                personalAccount.getAccountHolderName(),
                personalAccount.getAvailableBalance(),
                personalAccount.getBalance()
        );
    }
}
