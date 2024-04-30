package com.ziraat.app.account.repository;

import com.ziraat.app.account.model.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
    List<PersonalAccount> findAllByUserId(String userId);
    Optional<PersonalAccount> findByUserId(String userId);

    @Query("SELECT SUM(p.availableBalance + p.balance) FROM PersonalAccount p WHERE p.userId = :userId")
    Double calculateTotalBalanceByUserId(@Param("userId") String userId);

    boolean existsByAccountNumber(String accountNumber);
}
