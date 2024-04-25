package com.ziraat.app.account.repository;

import com.ziraat.app.account.model.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
}
