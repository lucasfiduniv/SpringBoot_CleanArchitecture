package com.example.java_springboot.modules.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_springboot.modules.certifications.useCases.Top10RankingUseCase;
import com.example.java_springboot.modules.students.entities.CertificationStudentEntity;
import com.example.java_springboot.modules.students.repositories.CertificationStudentRepository;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return this.top10RankingUseCase.execute();
    }
}
