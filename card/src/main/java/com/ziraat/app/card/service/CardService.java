package com.ziraat.app.card.service;

import com.ziraat.app.card.dto.CardCreateRequest;
import com.ziraat.app.card.dto.CardDto;
import com.ziraat.app.card.model.Card;
import com.ziraat.app.card.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class CardService {
    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public CardDto createCard(CardCreateRequest request){
        LocalDateTime now = LocalDateTime.now();
        Card card = new Card();
        card.setCardType(request.cardType());
        card.setCardNumber(generateCardNumber());
        card.setCvc(generateCvc());
        card.setExpireDate(generateExpireDate(now));
        card.setCardHolderName("John Doe"); // TODO
        card.setCreatedDate(now);
        card.setUserId("15151"); // TODO
        card.setAccountId(request.accountId());
        return CardDto.convert(repository.save(card));
    }


    private String generateCvc() {
        return Long.toString(new Random().nextLong()).substring(1, 4);
    }
    private String generateExpireDate(LocalDateTime now) {
        LocalDateTime expireDate = now.plusYears(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return expireDate.format(formatter);

    }
    private String generateCardNumber() {
        String cardNumber;
        do {
            cardNumber = Long.toString(new Random().nextLong()).substring(1, 17);
        }while (validateCardNumber(cardNumber));
        return cardNumber;
    }
    private Boolean validateCardNumber(String cardNumber) {
        return repository.existsByCardNumber(cardNumber);
    }
}
