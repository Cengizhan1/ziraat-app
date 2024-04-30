package com.ziraat.app.bank.controller;

import com.ziraat.app.account.dto.CashTransactionRequest;
import com.ziraat.app.bank.service.AtmService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/atm")
public class AtmController {

    private final AtmService service;

    public AtmController(AtmService service) {
        this.service = service;
    }

    @PostMapping("/cashWithdrawal")
    public ResponseEntity<Void> cashWithdrawal(@RequestBody @Valid CashTransactionRequest request) {
        service.cashWithdrawal(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cashDeposit")
    public ResponseEntity<Void> cashDeposit(@RequestBody @Valid CashTransactionRequest request) {
        service.cashDeposit(request);
        return ResponseEntity.ok().build();
    }
}
