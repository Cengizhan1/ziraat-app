package com.ziraat.app.card.repository;

import com.ziraat.app.card.model.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
}
