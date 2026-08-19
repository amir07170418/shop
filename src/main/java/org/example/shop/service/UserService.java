package org.example.shop.service;

import org.example.shop.dto.UserRequest;
import org.example.shop.exception.ShopException;
import org.example.shop.model.User;
import org.example.shop.repository.UserRepository;
import org.example.shop.security.JwtResponse;
import org.example.shop.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    public ResponseEntity<JwtResponse> login(UserRequest userRequest) {
        User  user = userRepository.findByEmail(userRequest.getEmail());
        if (user == null) {
            throw new ShopException("Email Or Password Incorrect", HttpStatus.BAD_REQUEST);
        }
        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new ShopException("Email Or Password Incorrect", HttpStatus.BAD_REQUEST);
        }
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setToken(jwtService.generateToken(user.getEmail()));
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }

}
