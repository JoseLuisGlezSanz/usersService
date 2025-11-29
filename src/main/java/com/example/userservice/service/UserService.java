package com.example.userservice.service;

import java.util.List;

import com.example.userservice.dto.UserLoginRequest;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.model.User;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findById(Long id);
    UserResponse create(UserRequest userRequest);
    UserResponse update(Long id, UserRequest userRequest);
    // void delete(Long id);
    UserResponse findByMail(String mail);
    UserResponse findByUsername(String userName);
    List<UserResponse> findByRoleId(Long idRole);
    List<UserResponse> getAll(int page, int pageSize);
    List<UserResponse> findByGymId(Long idGym);
    User authenticate(UserLoginRequest userLoginRequest);
}