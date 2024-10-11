package com.example.java_springboot.modules.students.controllers.entities;

import java.util.List;
import java.util.UUID;

public class StudentyEntity {
    private UUID id;
    private String email;
    private List<CertificationStudentEntity> certificationStudentEntity;
}
