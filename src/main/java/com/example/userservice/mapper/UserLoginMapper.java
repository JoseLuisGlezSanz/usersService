package com.example.userservice.mapper;

import com.example.userservice.dto.UserLoginRequest;
import com.example.userservice.dto.UserLoginResponse;
import com.example.userservice.model.User;

public final class UserLoginMapper {
    public static UserLoginResponse toResponse(User user) {
        if (user == null) return null;

        return UserLoginResponse.builder()
                .userName(user.getUsername())
                .roleName(user.getRole().getAuthority())
                .build();
    }

    public static User toEntity(UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) return null;

        return User.builder()
                .userName(userLoginRequest.getUserName())
                .password(userLoginRequest.getPassword())
                .build();
    }

    public static void copyToEntity(UserLoginRequest userLoginRequest, User entity) {
        if (userLoginRequest == null || entity == null) return;

        entity.setUserName(userLoginRequest.getUserName());
        entity.setPassword(userLoginRequest.getPassword());
    }
}