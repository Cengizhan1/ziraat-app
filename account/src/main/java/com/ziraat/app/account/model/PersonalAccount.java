package com.ziraat.app.account.model;

import com.ziraat.app.account.model.enums.AccountState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "personel_accounts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String accountHolderName;
    private double availableBalance;
    private double balance;
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private AccountState accountState;  // Enum
    private String IBAN;
    private String userId;
    private String countryCode;
}
