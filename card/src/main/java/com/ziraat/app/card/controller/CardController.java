package com.ziraat.app.card.controller;

import com.ziraat.app.card.dto.CardCreateRequest;
import com.ziraat.app.card.dto.CardDto;
import com.ziraat.app.card.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<CardDto> createCard(CardCreateRequest request) {
        return ResponseEntity.ok(service.createCard(request));
    }
}
