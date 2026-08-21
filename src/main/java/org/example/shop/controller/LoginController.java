package org.example.shop.controller;

import org.example.shop.dto.UserRequest;
import org.example.shop.security.JwtResponse;
import org.example.shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping()
    public ResponseEntity<JwtResponse>  login(UserRequest userRequest) {
        return   userService.login(userRequest);
    }
}
