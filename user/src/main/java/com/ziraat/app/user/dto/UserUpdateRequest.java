package com.ziraat.app.user.dto;

public record UserUpdateRequest(
        String name,
        String username,
        String surname,
        String password,
        Integer age
){
}