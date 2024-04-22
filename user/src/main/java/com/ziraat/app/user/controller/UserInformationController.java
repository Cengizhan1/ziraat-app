package com.ziraat.app.user.controller;

import com.ziraat.app.user.dto.UserContactInformationRequest;
import com.ziraat.app.user.dto.UserInformationRequest;
import com.ziraat.app.user.service.UserContactInformationService;
import com.ziraat.app.user.service.UserInformationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user-information")
public class UserInformationController {

    private final UserInformationService service;

    public UserInformationController(UserInformationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> saveUserContactInformation(@Valid @RequestBody UserInformationRequest request) {
        service.create(request);
        return ResponseEntity.ok().build();
    }
}
