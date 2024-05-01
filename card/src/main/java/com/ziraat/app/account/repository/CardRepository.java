package com.ziraat.app.account.repository;

import com.ziraat.app.account.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByCardNumber(String cardNumber);

    Optional<Card> findByCardNumber(String cardNumber);
}
