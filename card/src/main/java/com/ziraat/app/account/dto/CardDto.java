package com.ziraat.app.account.dto;

import com.ziraat.app.account.model.Card;
import com.ziraat.app.account.model.enums.CardType;

import java.time.LocalDateTime;

public record CardDto(
        CardType cardType,
        String cardNumber,
        String cvc,
        String expireDate,
        String cardHolderName,
        LocalDateTime createdDate,
        String userId,
        String accountId
) {
    public static CardDto convert(Card card) {
        return new CardDto(
                card.getCardType(),
                card.getCardNumber(),
                card.getCvc(),
                card.getExpireDate(),
                card.getCardHolderName(),
                card.getCreatedDate(),
                card.getUserId(),
                card.getAccountId()
        );
    }
}
