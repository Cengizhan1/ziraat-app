package com.ziraat.app.account.service;

import com.ziraat.app.account.dto.AccountTransactionDto;
import com.ziraat.app.account.model.AccountTransaction;
import com.ziraat.app.account.model.enums.TransactionType;
import com.ziraat.app.account.repository.AccountTransectionRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionService {
    private final AccountTransectionRepository repository;

    public AccountTransactionService(AccountTransectionRepository repository) {
        this.repository = repository;
    }

    public void createTransaction(AccountTransactionDto accountTransactionDto) {
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setTransactionType(accountTransactionDto.transactionType());
        accountTransaction.setAmount(accountTransactionDto.amount());
        accountTransaction.setAvailableBalance(accountTransactionDto.availableBalance());
        accountTransaction.setSenderAccountNumber(accountTransactionDto.senderAccountNumber());
        accountTransaction.setReceiverAccountNumber(accountTransactionDto.receiverAccountNumber());

        repository.save(accountTransaction);

        // TODO logger will be added
    }



}
