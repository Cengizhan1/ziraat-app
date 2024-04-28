package com.ziraat.app.bank.controller;

import com.ziraat.app.bank.service.AtmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/atm")
public class AtmController {

    private final AtmService service;

    public AtmController(AtmService service) {
        this.service = service;
    }
}
