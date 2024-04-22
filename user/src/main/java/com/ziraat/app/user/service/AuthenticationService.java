package com.ziraat.app.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziraat.app.user.dto.AuthenticationResponse;
import com.ziraat.app.user.dto.LoginRequest;
import com.ziraat.app.user.dto.RegisterRequest;
import com.ziraat.app.user.model.Token;
import com.ziraat.app.user.model.User;
import com.ziraat.app.user.repository.TokenRepository;
import com.ziraat.app.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {  // TODO

//        if (repository.existsByUsername(request.username())) {
//            throw new TCKNAlreadyExistsException("TCKN already exists for : " + request.name());
//        }
//        var user = User.builder()
//                .name(request.name())
//                .tckn(request.tckn())
//                .surname(request.surname())
//                .password(passwordEncoder.encode(request.password()))
//                .age(request.age())
//                .gender(request.gender())
//                .role(Role.ROLE_USER)
//                .elo(USER_POINT)
//                .build();
//        var savedUser = repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        saveUserToken(savedUser, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                    .firstname(savedUser.getName())
//                .build();
        return null;
    }

    public AuthenticationResponse authenticate(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.identityNumber(),
                        request.password()
                )
        );
        var user = repository.findByIdentityNumber(request.identityNumber())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .firstname(user.getName())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String identityNumber;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        identityNumber = jwtService.extractUsername(refreshToken);
        if (identityNumber != null) {
            var user = this.repository.findByIdentityNumber(identityNumber)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
