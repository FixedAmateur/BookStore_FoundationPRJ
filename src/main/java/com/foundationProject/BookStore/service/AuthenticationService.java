package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.dto.LoginDto;
import com.foundationProject.BookStore.model.dto.RegisterDto;
import com.foundationProject.BookStore.model.response.JwtAuthResponse;

public interface AuthenticationService {
    JwtAuthResponse login(LoginDto loginRequest);

    JwtAuthResponse refreshToken(String refreshToken);

    String register(RegisterDto registerRequest);
}
