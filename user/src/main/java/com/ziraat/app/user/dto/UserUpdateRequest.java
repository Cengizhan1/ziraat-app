package com.ziraat.app.user.dto;

public record UserUpdateRequest(
        String name,
        String identityNumber,
        String surname,
        String password,
        Integer age
){
}