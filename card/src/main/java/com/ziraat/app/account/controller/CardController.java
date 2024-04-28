package com.ziraat.app.account.controller;

import com.ziraat.app.account.dto.CardCreateRequest;
import com.ziraat.app.account.dto.CardDto;
import com.ziraat.app.account.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/card")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody @Valid CardCreateRequest request, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(service.createCard(request,httpServletRequest));
    }
}
