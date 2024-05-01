package com.ziraat.app.account.repository;

import com.ziraat.app.account.model.AccountTransection;
import com.ziraat.app.account.model.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransectionRepository extends JpaRepository<AccountTransection, Long> {
    AccountTransection findByUserId(String userId);
}
