package com.ziraat.app.user.dto;

public record LoginRequest(
        String username,
        String password
){
}