package com.example.userservice.mapper;

import com.example.userservice.dto.EmergencyContactRequest;
import com.example.userservice.dto.EmergencyContactResponse;
import com.example.userservice.model.EmergencyContact;

public class EmergencyContactMapper {
    public static EmergencyContactResponse toResponse(EmergencyContact emergencyContact) {
        if (emergencyContact == null) return null;
        
        return EmergencyContactResponse.builder()
                .id(emergencyContact.getId())
                .contactName(emergencyContact.getContactName())
                .contactPhone(emergencyContact.getContactPhone())
                .build();
    }

    public static EmergencyContact toEntity(String contactName, String contactPhone) {
        if (contactName == null && contactPhone == null) return null;
        
        return EmergencyContact.builder()
                .contactName(contactName)
                .contactPhone(contactPhone)
                .build();
    }

    public static void copyToEntity(EmergencyContactRequest emergencyContactRequest, EmergencyContact entity) {
        if (emergencyContactRequest == null || entity == null) return;
        
        entity.setContactName(emergencyContactRequest.getContactName());
        entity.setContactPhone(emergencyContactRequest.getContactPhone());
    }
}