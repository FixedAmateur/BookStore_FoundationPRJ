package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.dto.UserDto;
import com.foundationProject.BookStore.model.response.PageCustom;
import com.foundationProject.BookStore.model.response.UserResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse getUserById(Long userId);

    PageCustom<UserResponse> getAllUser(Pageable pageable);

    UserResponse updateUser(Long userId, UserDto userRequest);

    String deleteUser(Long userId);
}
