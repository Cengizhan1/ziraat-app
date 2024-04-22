package com.ziraat.app.user.service;

import com.ziraat.app.user.dto.UserContactInformationRequest;
import com.ziraat.app.user.model.User;
import com.ziraat.app.user.model.UserContactInformation;
import com.ziraat.app.user.repository.UserContactInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserContactInformationService {

    private final UserContactInformationRepository repository;
    private final UserService userService;

    public UserContactInformationService(UserContactInformationRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void create(UserContactInformationRequest request) {
        User user = userService.getAuthenticatedUser();
        checkUserContactInformation(user);
        UserContactInformation userContactInformation =UserContactInformation.builder()
                .address(request.address())
                .addressType(request.addressType())
                .phone(request.phone())
                .email(request.email())
                .phonePermission(request.phonePermission())
                .emailPermission(request.emailPermission())
                .user(user)
                .build();

        repository.save(userContactInformation);
    }

    private void checkUserContactInformation(User user) {
        if (repository.existsByUser(user)) {
            throw new IllegalStateException("User contact information already exists");
        }
    }
}
