package com.example.userservice.mapper;

import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.model.Gym;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        if (user == null) return null;
            
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .mail(user.getMail())
                .phone(user.getPhone())
                .name(user.getName())
                .status(user.getStatus())
                .roleId(user.getRole().getId())
                .authority(user.getRole().getAuthority())
                .gymId(user.getGym().getId())
                .build();
    }

    public static User toEntity(UserRequest userRequest, Role role, Gym gym) {
        if (userRequest == null) return null;

        return User.builder()
                .userName(userRequest.getUserName())
                .mail(userRequest.getMail())
                .phone(userRequest.getPhone())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .status(userRequest.getStatus())
                .role(role)
                .gym(gym)
                .build();
    }

    public static void copyToEntity(UserRequest userRequest, User entity, Role role, Gym gym) {
        if (userRequest == null || entity == null) return;
        
        entity.setUserName(userRequest.getUserName());
        entity.setMail(userRequest.getMail());
        entity.setPhone(userRequest.getPhone());
        entity.setName(userRequest.getName());
        entity.setPassword(userRequest.getPassword());
        entity.setStatus(userRequest.getStatus());
        entity.setRole(role);
        entity.setGym(gym);
    }
}