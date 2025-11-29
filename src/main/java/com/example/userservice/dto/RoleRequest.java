package com.example.userservice.dto;

import lombok.Data;

@Data
public class RoleRequest {
    private String authority;
    private Integer status;
}