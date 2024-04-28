package com.ziraat.app.user.dto;

public record LoginRequest(
        String identityNumber,
        String password
){
}