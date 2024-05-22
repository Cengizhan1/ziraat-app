package com.ziraat.app.account.model;

import com.ziraat.app.account.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account_transections")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;
    private double amount;
    private double availableBalance;
    private LocalDateTime transactionDate;
    private String senderAccountNumber;
    private String IBAN;
    private String userId;
    private double balance; // TODO kullanılmıyorsa kaldırılsın
}
