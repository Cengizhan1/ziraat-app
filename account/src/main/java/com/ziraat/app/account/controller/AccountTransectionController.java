package com.ziraat.app.account.controller;

import com.ziraat.app.account.service.AccountTransectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/transection")
public class AccountTransectionController {

    private final AccountTransectionService service;

    public AccountTransectionController(AccountTransectionService service) {
        this.service = service;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String userId, @RequestParam double amount) {
        service.deposit(userId, amount);
        return ResponseEntity.status(HttpStatus.OK).body("Deposit successful.");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String userId, @RequestParam double amount) {
        service.withdraw(userId, amount);
        return ResponseEntity.status(HttpStatus.OK).body("Withdrawal successful.");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromUserId, @RequestParam String toUserId, @RequestParam double amount) {
        service.transfer(fromUserId, toUserId, amount);
        return ResponseEntity.status(HttpStatus.OK).body("Transfer successful.");
    }
}
