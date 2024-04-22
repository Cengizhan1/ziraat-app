package com.ziraat.app.user.dto;


import com.ziraat.app.user.model.User;

public record UserDto(
        String id,
        String name,
        String surname,
        String identityNumber,
        Integer age,
        Integer elo

) {
    public static UserDto convert(User user) {
        return null; // TODO
    }
}