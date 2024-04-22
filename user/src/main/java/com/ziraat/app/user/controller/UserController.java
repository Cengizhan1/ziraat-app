package com.ziraat.app.user.controller;


import com.ziraat.app.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
