package com.ziraat.app.account.service;

import com.ziraat.app.account.dto.PersonalAccountDto;
import com.ziraat.app.account.utils.Module97;
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
       personalAccount.setIBAN(generateIBAN(personalAccount.getCountryCode()));
        personalAccount.setUserId(request.getAttribute("userId").toString());
        return PersonalAccountDto.convert(repository.save(personalAccount));
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }

    public SummaryPersonalAccountDto summary(String userId) {
        List<PersonalAccount> accounts = repository.findByUserIdList(userId);
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
    public static String generateIBAN(String countryCode) {
        if (countryCode == null || countryCode.length() != 2 || !countryCode.matches("[A-Z]{2}")) {
            throw new IllegalArgumentException("Invalid country code.");
        }

        StringBuilder ibanBuilder = new StringBuilder(countryCode);
        ibanBuilder.append("00"); // İlk iki rakam check digits için yer açar

        Random random = new Random();
        for (int i = 0; i < 14; i++) {
            ibanBuilder.append(random.nextInt(10));
        }

        String ibanWithoutCheckDigits = ibanBuilder.toString();
        int checkDigits = 98 - (calculateCheckDigits(ibanWithoutCheckDigits + "00") % 97); //TODO standartlara göre Module97 sınıfı yazılacak
        String checkDigitsStr = String.format("%02d", checkDigits);

        return countryCode + checkDigitsStr + ibanWithoutCheckDigits.substring(4);
    }
    public static int calculateCheckDigits(String input) {
        input = input.trim().toUpperCase().replaceAll("\\s", "");

        String formattedInput = input.substring(4) + input.substring(0, 4) + "00";

        int remainder = 0;
        for (int i = 0; i < formattedInput.length(); i++) {
            int digit = Character.getNumericValue(formattedInput.charAt(i));
            remainder = (remainder * 10 + digit) % 97;
        }

        return 98 - remainder;
    }

    public void deposit(String userId, double amount) {
        PersonalAccount account = repository.findByUserId(userId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found for user ID: " + userId);
        }
        account.setBalance(account.getBalance() + amount);
        repository.save(account);
    }

    public void withdraw(String userId, double amount) {
        PersonalAccount account = repository.findByUserId(userId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found for user ID: " + userId);
        }
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        account.setBalance(account.getBalance() - amount);
        repository.save(account);
    }

}
