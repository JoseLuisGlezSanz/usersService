package com.example.userservice.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String mail;
    private String phone;
    private String name;
    private String password;
    private Integer status;
    private Long roleId;
    private Long gymId;
}