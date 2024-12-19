package com.foundationProject.BookStore.controller;

import com.foundationProject.BookStore.model.dto.LoginDto;
import com.foundationProject.BookStore.model.dto.RegisterDto;
import com.foundationProject.BookStore.model.response.JwtAuthResponse;
import com.foundationProject.BookStore.service.AuthenticationService;
import com.foundationProject.BookStore.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@AllArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Operation(summary = "Login", description = "Login API")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody @Valid LoginDto loginRequest){
        JwtAuthResponse authenticationUser =  authenticationService.login(loginRequest);

        return ResponseEntity.ok(authenticationUser);
    }

    @Operation(summary = "Register", description = "Register API")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerRequest){
        String response = authenticationService.register(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}