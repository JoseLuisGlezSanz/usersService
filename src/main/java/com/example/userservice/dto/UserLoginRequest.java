package com.example.userservice.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userName;
    private String password;
}