package com.ziraat.app.user.model;

import com.ziraat.app.user.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_informations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
    private String job;
    private Double salary;
    private LocalDateTime birthDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
