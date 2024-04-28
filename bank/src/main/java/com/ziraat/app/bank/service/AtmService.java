package com.ziraat.app.bank.service;

import com.ziraat.app.bank.repository.AtmRepository;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    private final AtmRepository repository;

    public AtmService(AtmRepository repository) {
        this.repository = repository;
    }
}
