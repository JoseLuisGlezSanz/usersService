package com.example.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.userservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Buscar role por estatus
    @Query(value = "SELECT * FROM roles WHERE status = :statusValue;", nativeQuery = true)
    List<Role> findByStatus(@Param("statusValue") Integer statusValue);
}