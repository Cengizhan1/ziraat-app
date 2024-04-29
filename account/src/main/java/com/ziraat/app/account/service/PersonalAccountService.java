package com.ziraat.app.account.service;

import com.ziraat.app.account.dto.PersonalAccountDto;
import com.ziraat.app.account.dto.SummaryPersonalAccountDto;
import com.ziraat.app.account.exception.PersonalAccountNotFoundException;
import com.ziraat.app.account.model.PersonalAccount;
import com.ziraat.app.account.model.enums.AccountState;
import com.ziraat.app.account.repository.PersonalAccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PersonalAccountService {

    private final PersonalAccountRepository repository;

    public PersonalAccountService(PersonalAccountRepository repository) {
        this.repository = repository;
    }


    public List<PersonalAccountDto> listByUserId(String userId) {
        return repository.findByUserId(userId).stream()
                .map(PersonalAccountDto::convert)
                .collect(Collectors.toList());
    }

    public PersonalAccountDto create(HttpServletRequest request) {
        LocalDateTime now = LocalDateTime.now();
        PersonalAccount personalAccount = new PersonalAccount();
        personalAccount.setAccountNumber(generateAccountNumber());
        personalAccount.setAccountHolderName(generateHolderName(request));
        personalAccount.setAvailableBalance(0.0);
        personalAccount.setBalance(0.0);
        personalAccount.setCreatedDate(now);
        personalAccount.setAccountState(AccountState.ACTIVE);
//        personalAccount.setIBAN(generateIBAN()); // TODO generateIBAN metodu eklenecek
        personalAccount.setUserId(request.getAttribute("userId").toString());
        return PersonalAccountDto.convert(repository.save(personalAccount));
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public SummaryPersonalAccountDto summary(String userId) {
        List<PersonalAccount> accounts = repository.findByUserId(userId);
        if (accounts.size() > 0) {
            double totalBalance = accounts.stream().map(PersonalAccount::getBalance).reduce(Double::sum).get();
            double totalAvailableBalance = accounts.stream().map(PersonalAccount::getAvailableBalance).reduce(Double::sum).get();
            return new SummaryPersonalAccountDto(
                    accounts.get(0).getAccountHolderName(),
                    totalAvailableBalance,
                    totalBalance
            );
        }else throw new PersonalAccountNotFoundException("Personal account not found");
    }

    private String generateHolderName(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getAttribute("name").toString() + " " +
                httpServletRequest.getAttribute("surname").toString();
    }

    private String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = Long.toString(new Random().nextLong()).substring(1, 13);
        }while (validateAccountNumber(accountNumber));
        return accountNumber;
    }

    private Boolean validateAccountNumber(String accountNumber) {
        return repository.existsByAccountNumber(accountNumber);
    }
}
