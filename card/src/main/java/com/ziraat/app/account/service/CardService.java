package com.ziraat.app.account.service;

import com.ziraat.app.account.dto.CardCreateRequest;
import com.ziraat.app.account.dto.CardDto;
import com.ziraat.app.account.dto.CashTransactionRequest;
import com.ziraat.app.account.exception.CardNotFoundException;
import com.ziraat.app.account.model.Card;
import com.ziraat.app.account.repository.CardRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class CardService {
    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public CardDto createCard(CardCreateRequest request, HttpServletRequest httpServletRequest) {
        LocalDateTime now = LocalDateTime.now();
        Card card = new Card();
        card.setCardType(request.cardType());
        card.setCardNumber(generateCardNumber());
        card.setCvc(generateCvc());
        card.setExpireDate(generateExpireDate(now));
        card.setCardHolderName(generateHolderName(httpServletRequest));
        card.setCreatedDate(now);
        card.setCardPin(request.cardPin());
        card.setUserId(httpServletRequest.getAttribute("userId").toString());
        card.setAccountId(request.accountId());
        return CardDto.convert(repository.save(card));
    }

    public boolean validateCard(CashTransactionRequest request) {
        Card card = getCardByCardNumber(request.cardNumber());
        return card.getCardPin().equals(request.cardPin()) &&
                card.getCvc().equals(request.cvc()) &&
                card.getExpireDate().equals(request.expireDate());
    }

    private Card getCardByCardNumber(String cardNumber) {
        return repository.findByCardNumber(cardNumber).orElseThrow(
                () -> new CardNotFoundException("Card not found with card number " + cardNumber));
    }

    private String generateHolderName(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getAttribute("name").toString() + " " +
                httpServletRequest.getAttribute("surname").toString();
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
