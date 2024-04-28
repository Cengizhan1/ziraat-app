package com.ziraat.app.bank.dto;

import com.ziraat.app.bank.model.Atm;

public record AtmDto(
        String name,
        Double longitude,
        Double latitude,
        String city,
        String country,
        String address
) {
    public static AtmDto convert(Atm atm) {
        return new AtmDto(atm.getName(),
                atm.getLongitude(),
                atm.getLatitude(),
                atm.getCity(),
                atm.getCountry(),
                atm.getAddress());
    }
}
