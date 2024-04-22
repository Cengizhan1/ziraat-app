package com.ziraat.app.user.dto;


public record RegisterRequest(
        String name,
        String identityNumber,
        String surname,
        String password,
        Integer age
){
}