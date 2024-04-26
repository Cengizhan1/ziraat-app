package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.enums.AccountState;

public record PersonalAccountCreateRequest(
        AccountState accountState,  // Enum
        String accountHolderName

        ) {

}
