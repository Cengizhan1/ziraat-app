package com.ziraat.app.account.service;

import com.ziraat.app.account.dto.PersonalAccountCreateRequest;
import com.ziraat.app.account.dto.PersonalAccountDeleteRequest;
import com.ziraat.app.account.dto.PersonalAccountDto;
import com.ziraat.app.account.model.PersonalAccount;
import com.ziraat.app.account.repository.PersonalAccountRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalAccountService {
    private final PersonalAccountRepository personalAccountRepository;
   // private final List<PersonalAccount> accounts = new ArrayList<>();


    public PersonalAccountService(PersonalAccountRepository repository) {
        this.personalAccountRepository = repository;
    }

   

    public List<PersonalAccountDto> showById(Long userId) {
        List<PersonalAccountDto> accountDtos = new ArrayList<>();
        List<PersonalAccount> personalAccounts = personalAccountRepository.findByUserId((userId));
        for (PersonalAccount personalAccount : personalAccounts) {
            PersonalAccountDto accountDto = PersonalAccountDto.convert(personalAccount);
            accountDtos.add(accountDto);
        }
        return accountDtos;
    }

    public PersonalAccountDto create(PersonalAccountCreateRequest request){
        LocalDateTime now=LocalDateTime.now();
        PersonalAccount personalAccount=new PersonalAccount();
        personalAccount.setAccountState(request.accountState());
        personalAccount.setCreatedDate(now);
        return PersonalAccountDto.convert(personalAccountRepository.save(personalAccount));
    }


    public String delete(PersonalAccountDeleteRequest request) {
        personalAccountRepository.delet(
                request.accountNumber(),
                request.accountHolderName()
        );
        return "Hesap başarıyla silindi";
    }

    public double summary(String userId) {
        return personalAccountRepository.calculateTotalBalanceByUserId(userId) != null
                ? personalAccountRepository.calculateTotalBalanceByUserId(userId)
                : 0.0;
    }

}
