package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.enums.CardType;

public record CardCreateRequest(
        CardType cardType,
        String accountId
) {
}
