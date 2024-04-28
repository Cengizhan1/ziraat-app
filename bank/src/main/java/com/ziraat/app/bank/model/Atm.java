package com.ziraat.app.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "atms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;
    private String city;
    private String country;
    private String address;
}
