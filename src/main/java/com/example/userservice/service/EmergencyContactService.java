package com.example.userservice.service;

import java.util.List;

import com.example.userservice.dto.EmergencyContactRequest;
import com.example.userservice.dto.EmergencyContactResponse;

public interface EmergencyContactService {
    List<EmergencyContactResponse> findAll();
    EmergencyContactResponse findById(Long id);
    EmergencyContactResponse update(Long id, EmergencyContactRequest emergencyContactRequest);
    // void delete(Long id);
    List<EmergencyContactResponse> getAll(int page, int pageSize);
    EmergencyContactResponse findEmergencyContactByIdCustomer(Long customerId);
}