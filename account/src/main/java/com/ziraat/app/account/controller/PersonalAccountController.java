package com.ziraat.app.account.controller;

import com.ziraat.app.account.dto.PersonalAccountCreateRequest;
import com.ziraat.app.account.dto.PersonalAccountDto;
import com.ziraat.app.account.service.PersonalAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/account")
public class PersonalAccountController {

    private final PersonalAccountService service;

    public PersonalAccountController(PersonalAccountService service) {
        this.service = service;
    }

    @PostMapping("create")
    public ResponseEntity<PersonalAccountDto> createAccount(PersonalAccountCreateRequest request){
        return ResponseEntity.ok((service.create(request)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PersonalAccountDto>> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.showById(userId));
    }
}
