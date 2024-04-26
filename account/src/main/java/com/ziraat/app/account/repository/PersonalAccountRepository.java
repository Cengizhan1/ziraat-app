package com.ziraat.app.account.repository;

import com.ziraat.app.account.model.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
    List<PersonalAccount> findByUserId(Long userId);
    void delet(String accountNumber, String accountHolderName);
    @Query("SELECT SUM(p.availableBalance + p.balance) FROM PersonalAccount p WHERE p.userId = :userId")
    Double calculateTotalBalanceByUserId(@Param("userId") String userId);

}
