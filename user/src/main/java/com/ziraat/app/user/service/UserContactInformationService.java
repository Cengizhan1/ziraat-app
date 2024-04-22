package com.ziraat.app.user.service;

import com.ziraat.app.user.dto.UserContactInformationRequest;
import com.ziraat.app.user.model.UserContactInformation;
import com.ziraat.app.user.repository.UserContactInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserContactInformationService {

    private final UserContactInformationRepository repository;

    public UserContactInformationService(UserContactInformationRepository repository) {
        this.repository = repository;
    }

    public void create(UserContactInformationRequest request) {
        UserContactInformation userContactInformation =UserContactInformation.builder()
                .address(request.address())
                .addressType(request.addressType())
                .phone(request.phone())
                .email(request.email())
                .phonePermission(request.phonePermission())
                .emailPermission(request.emailPermission())
                .build();

        repository.save(userContactInformation);
    }
}
