package com.ziraat.app.bank.service;

import com.ziraat.app.account.dto.CashTransactionRequest;
import com.ziraat.app.account.service.CardService;
import com.ziraat.app.account.service.PersonalAccountService;
import com.ziraat.app.bank.exception.CardInformationNotValidException;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    private final CardService cardService;
    private final PersonalAccountService accountService;

    public AtmService(CardService cardService, PersonalAccountService accountService) {
        this.cardService = cardService;
        this.accountService = accountService;
    }

    public void cashWithdrawal(CashTransactionRequest request) {
        validateCardInformation(request);
        accountService.withdraw(request.accountNumber(), request.amount() * -1);
    }

    public void cashDeposit(CashTransactionRequest request) {
        validateCardInformation(request);
        accountService.deposit(request.accountNumber(), request.amount());
    }

    private void validateCardInformation(CashTransactionRequest request) {
        if (!cardService.validateCard(request)) {
            throw new CardInformationNotValidException("Card information is not valid");
        }
    }
}
