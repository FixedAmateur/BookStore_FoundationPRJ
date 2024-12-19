package com.foundationProject.BookStore.service.impl;


import com.foundationProject.BookStore.exception.AppException;
import com.foundationProject.BookStore.exception.ErrorCode;
import com.foundationProject.BookStore.exception.ResourceNotFoundException;
import com.foundationProject.BookStore.model.dto.LoginDto;
import com.foundationProject.BookStore.model.dto.RegisterDto;
import com.foundationProject.BookStore.model.entity.Order;
import com.foundationProject.BookStore.model.entity.Role;
import com.foundationProject.BookStore.model.entity.User;
import com.foundationProject.BookStore.model.response.JwtAuthResponse;
import com.foundationProject.BookStore.model.response.UserResponse;
import com.foundationProject.BookStore.repository.OrderRepository;
import com.foundationProject.BookStore.repository.RoleRepository;
import com.foundationProject.BookStore.repository.UserRepository;
import com.foundationProject.BookStore.service.AuthenticationService;
import com.foundationProject.BookStore.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final OrderRepository orderRepository;


    @Override
    public JwtAuthResponse login(LoginDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "username", loginRequest.getUsername()));
        if (user == null) {
            throw new AppException(ErrorCode.USER_UNAUTHENTICATED);
        }
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()
            ));
        } catch (Exception exception) {
            throw new AppException(ErrorCode.USER_UNAUTHENTICATED);
        }

        String jwtToken = jwtService.generateToken(user, jwtService.getExpirationTime()*24);
        String refreshToken = jwtService.generateToken(user,jwtService.getExpirationTime()*24*2);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);

        return JwtAuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userResponse)
                .expiredTime(new Timestamp(System.currentTimeMillis() +jwtService.getExpirationTime()))
                .build();
    }

    @Override
    public JwtAuthResponse refreshToken(String refreshToken) {
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }
        String username = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        String accessToken = jwtService.generateToken(user, jwtService.getExpirationTime()*24) ;
        refreshToken = jwtService.generateToken(user, jwtService.getExpirationTime()*24*2);

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);

        return JwtAuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userResponse)
                .expiredTime(new Timestamp(System.currentTimeMillis() +jwtService.getExpirationTime()))
                .build();
    }



    @Override
    public String register(RegisterDto registerRequest){
        if (userRepository.existsByUsername(registerRequest.getUsername())){
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }

        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRoleName("ROLE_USER").orElseThrow(()->new ResourceNotFoundException("role", "role's name","ROLE_USER"));
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);
        return "User registered successfully!.";
    }
}
