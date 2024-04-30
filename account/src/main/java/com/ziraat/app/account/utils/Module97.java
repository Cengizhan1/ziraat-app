package com.ziraat.app.account.utils;

public class Module97 {   // TODO Standartlara gore Module97 algoritması şart
    public  int calculateCheckDigits(String input) {
        // Girilen IBAN'ı uygun formata dönüştür (uzunluğu sabit ve belirli bir formatta olmalı)
        input = input.trim().toUpperCase().replaceAll("\\s", "");

        // Girilen IBAN'ın ilk iki karakterini alıp sonuna "00" ekleyerek hesaplama için uygun formata getir
        String formattedInput = input.substring(4) + input.substring(0, 4) + "00";

        // Modulo 97 algoritmasını uygula
        int remainder = 0;
        for (int i = 0; i < formattedInput.length(); i++) {
            int digit = Character.getNumericValue(formattedInput.charAt(i));
            remainder = (remainder * 10 + digit) % 97;
        }

        // Sonucu döndür (98 - kalan değer kontrol rakamlarını verir)
        return 98 - remainder;
    }
}
