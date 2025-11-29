package com.example.userservice.service;

import java.util.List;

import com.example.userservice.dto.RoleRequest;
import com.example.userservice.dto.RoleResponse;

public interface RoleService {
    List<RoleResponse> findAll();
    RoleResponse findById(Long id);
    RoleResponse create(RoleRequest roleRequest);
    RoleResponse update(Long id, RoleRequest roleRequest);
    // void delete(Long id);
    List<RoleResponse> findByStatus(Integer statusValue);
}