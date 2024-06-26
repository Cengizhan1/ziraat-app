package com.ziraat.app.user.service;

import com.ziraat.app.user.dto.UserDto;
import com.ziraat.app.user.model.User;
import com.ziraat.app.user.repository.UserRepository;
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


    public UserDto show() {
        return UserDto.convert(getAuthenticatedUser());
    }
    public UserDto getUserById(String id) {
        return UserDto.convert(findUserById(id));
    }
    protected User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByIdentityNumber(authentication.getName()).orElseThrow((
                () -> new UsernameNotFoundException("Authenticated user not found")));
    }

    private User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    protected User getUserByIdentityNumber(String identityNumber) {
        return userRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
