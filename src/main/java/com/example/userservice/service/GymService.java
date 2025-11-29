package com.example.userservice.service;

import java.util.List;

import com.example.userservice.dto.GymRequest;
import com.example.userservice.dto.GymResponse;

public interface GymService {
    List<GymResponse> findAll();
    GymResponse findById(Long id);
    GymResponse create(GymRequest gymRequest);
    GymResponse update(Long id, GymRequest gymRequest);
    // void delete(Long id);
    GymResponse findByName(String gym);
    List<GymResponse> getAll(int page, int pageSize);
}