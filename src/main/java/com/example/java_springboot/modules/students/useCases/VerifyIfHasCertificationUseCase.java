package com.example.java_springboot.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_springboot.modules.students.dto.VerifyHasCertificationDTO;
import com.example.java_springboot.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyHasCertificationDTO verifyHasCertificationDTO) {
        var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(verifyHasCertificationDTO.getEmail(),
                verifyHasCertificationDTO.getTechnology());
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}