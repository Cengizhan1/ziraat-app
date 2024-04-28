package com.ziraat.app.bank.service;

import com.ziraat.app.bank.dto.CashTransactionRequest;
import com.ziraat.app.bank.repository.AtmRepository;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    private final AtmRepository repository;

    public AtmService(AtmRepository repository) {
        this.repository = repository;
    }

    public void cashWithdrawal(CashTransactionRequest request) {
        validateCardInformation(request);
        cashTransaction(request.accountNumber(), request.amount()*-1);
    }

    public void cashDeposit(CashTransactionRequest request) {
        validateCardInformation(request);
        cashTransaction(request.accountNumber(), request.amount());
    }

    private void validateCardInformation(CashTransactionRequest request) {
     // todo
    }

    private void cashTransaction(String accountNumber, double amount) {
        // todo
    }
}
