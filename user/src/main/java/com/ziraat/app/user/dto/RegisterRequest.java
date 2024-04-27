package com.ziraat.app.user.dto;


import com.ziraat.app.user.model.enums.Role;

public record RegisterRequest(
        String name,
        String surname,
        String password,
        String identityNumber
){}