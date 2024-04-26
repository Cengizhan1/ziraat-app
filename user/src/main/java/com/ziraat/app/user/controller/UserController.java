package com.ziraat.app.user.controller;


import com.ziraat.app.user.dto.UserDto;
import com.ziraat.app.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserDto> show() {
        return ResponseEntity.ok(service.show());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }
}
