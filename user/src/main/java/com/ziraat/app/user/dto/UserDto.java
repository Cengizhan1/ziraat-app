package com.ziraat.app.user.dto;


import com.ziraat.app.user.model.User;

public record UserDto(
        String name,
        String surname,
        String identityNumber

) {
    public static UserDto convert(User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getIdentityNumber());
    }
}