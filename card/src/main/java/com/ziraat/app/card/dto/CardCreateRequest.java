package com.ziraat.app.card.dto;

import com.ziraat.app.card.model.enums.CardType;

import java.time.LocalDateTime;

public record CardCreateRequest(
        CardType cardType,
        String accountId
) {
}
