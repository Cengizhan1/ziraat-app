package com.ziraat.app.user.dto;

import com.ziraat.app.user.model.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserInformationRequest(
        Gender gender,
        Integer age,
        String job,
        Double salary,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate
) {
}
