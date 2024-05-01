package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.enums.CardType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CardCreateRequest(
        @NotNull
        CardType cardType,
        @NotNull
        String accountId,
        @NotNull @NotEmpty
        String cardPin
) {
}
