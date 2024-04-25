package com.ziraat.app.account.service;

import com.ziraat.app.account.dto.PersonalAccountDto;
import com.ziraat.app.account.model.PersonalAccount;
import com.ziraat.app.account.repository.PersonalAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalAccountService {
    private final PersonalAccountRepository personalAccountRepository;

    public PersonalAccountService(PersonalAccountRepository repository) {
        this.personalAccountRepository = repository;
    }


    public PersonalAccount createPersonalAccount(PersonalAccountDto personalAccountDto) {
        PersonalAccount personalAccount = new PersonalAccount();
      //  personalAccount.setAccountNumber(personalAccountDto.getAccountNumber());  //ide getAccountNumberi tanımıyor
        return personalAccountRepository.save(personalAccount);
    }

    public double getTotalBalance() { //kullanıcı hesap toplamı
        List<PersonalAccount> accounts = personalAccountRepository.findAll(); // filtreleme yapılsın mı ?
        return accounts.stream()
                .mapToDouble(account -> account.getAvailableBalance() + account.getBalance())
                .sum();
    }

    public List<PersonalAccount> listAccounts() { //Tüm accountları getirsin
        return personalAccountRepository.findAll();
    }

    public List<String> listAccountNames() { //Tüm account isimlerini getirsin
        return personalAccountRepository.findAll()
                .stream()
                .map(PersonalAccount::getAccountHolderName)
                .collect(Collectors.toList());
    }



}
