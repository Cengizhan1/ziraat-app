package com.ziraat.app.user.model;

import com.ziraat.app.user.model.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_contact_informations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    private String phone;
    private String email;

    private Boolean phonePermission = false;
    private Boolean emailPermission = false;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


}
