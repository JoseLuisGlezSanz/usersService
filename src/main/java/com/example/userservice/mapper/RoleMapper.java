package com.example.userservice.mapper;

import com.example.userservice.dto.RoleRequest;
import com.example.userservice.dto.RoleResponse;
import com.example.userservice.model.Role;

public class RoleMapper {
    public static RoleResponse toResponse(Role role) {
        if (role == null) return null;
        
        return RoleResponse.builder()
                .id(role.getId())
                .authority(role.getAuthority())
                .status(role.getStatus())
                .build();
    }

    public static Role toEntity(RoleRequest roleRequest) {
        if (roleRequest == null) return null;
        
        return Role.builder()
                .authority(roleRequest.getAuthority())
                .status(roleRequest.getStatus())
                .build();
    }

    public static void copyToEntity(RoleRequest roleRequest, Role entity) {
        if (roleRequest == null || entity == null) return;
        
        entity.setAuthority(roleRequest.getAuthority());
        entity.setStatus(roleRequest.getStatus());
    }
}
