package com.ziraat.app.account.service;

import com.ziraat.app.account.model.AccountTransection;
import com.ziraat.app.account.repository.AccountTransectionRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionService {
    private final AccountTransectionRepository repository;

    public AccountTransactionService(AccountTransectionRepository repository) {
        this.repository = repository;
    }

//AccountService taşı
    public void transfer(String fromUserId, String toUserId, double amount) {
        AccountTransection fromAccount = repository.findByUserId(fromUserId);
        AccountTransection toAccount = repository.findByUserId(toUserId);

        if (fromAccount == null) {
            throw new IllegalArgumentException("Account not found for user ID: " + fromUserId);
        }

        if (toAccount == null) {
            throw new IllegalArgumentException("Account not found for user ID: " + toUserId);
        }

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        repository.save(fromAccount);
        repository.save(toAccount);
    }



}
