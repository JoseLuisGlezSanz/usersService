package com.example.userservice.dto;

import lombok.Data;

@Data
public class EmergencyContactRequest {
    private String contactName;
    private String contactPhone;
}