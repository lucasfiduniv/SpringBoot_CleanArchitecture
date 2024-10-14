package com.example.java_springboot.modules.students.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_springboot.modules.students.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    public StudentEntity findByEmail(String email);
}
