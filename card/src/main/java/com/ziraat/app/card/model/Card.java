package com.ziraat.app.card.model;

import com.ziraat.app.card.model.enums.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cards")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CardType cardType;
    private String cardNumber;
    private String cvc;
    private String expireDate;
    private String cardHolderName;
    private LocalDateTime createdDate;
    private String userId;
    private String accountId;
    private String accountNumber;
}
