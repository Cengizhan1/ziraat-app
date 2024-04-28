package com.ziraat.app.bank.repository;

import com.ziraat.app.bank.model.Atm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<Atm, Long> {
}
