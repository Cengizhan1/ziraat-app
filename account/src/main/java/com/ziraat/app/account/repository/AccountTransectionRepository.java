package com.ziraat.app.account.repository;

import com.ziraat.app.account.model.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransectionRepository extends JpaRepository<AccountTransaction, Long> {

}
