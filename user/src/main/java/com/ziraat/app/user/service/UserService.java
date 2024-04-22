package com.ziraat.app.user.service;


import com.ziraat.app.user.dto.UserContactInformationRequest;
import com.ziraat.app.user.dto.UserInformationRequest;
import com.ziraat.app.user.model.User;
import com.ziraat.app.user.model.UserContactInformation;
import com.ziraat.app.user.model.UserInformation;
import com.ziraat.app.user.repository.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName()).orElseThrow((
                () -> new UsernameNotFoundException("Authenticated user not found")));
    }
}
