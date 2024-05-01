package com.ziraat.app.account.controller;

import com.ziraat.app.account.dto.PersonalAccountDto;
import com.ziraat.app.account.dto.SummaryPersonalAccountDto;
import com.ziraat.app.account.service.PersonalAccountService;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping
    public ResponseEntity<PersonalAccountDto> create(HttpServletRequest request){
        return ResponseEntity.ok((service.create(request)));
    }

    @GetMapping
    public ResponseEntity<List<PersonalAccountDto>> listByUserId(HttpServletRequest request) {
        return ResponseEntity.ok(service.findByUserIdList(request.getAttribute("userId").toString()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<SummaryPersonalAccountDto> summary(HttpServletRequest request) {
        return ResponseEntity.ok(service.summary(request.getAttribute("userId").toString()));
    }
}
