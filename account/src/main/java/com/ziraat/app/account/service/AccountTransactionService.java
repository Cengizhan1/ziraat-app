package com.ziraat.app.account.service;

import com.ziraat.app.account.model.AccountTransection;
import com.ziraat.app.account.model.enums.TransactionType;
import com.ziraat.app.account.repository.AccountTransectionRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionService {
    private final AccountTransectionRepository repository;

    public AccountTransactionService(AccountTransectionRepository repository) {
        this.repository = repository;
    }


    public void createTransaction(TransactionType transactionType) { // TODO

    }



}
