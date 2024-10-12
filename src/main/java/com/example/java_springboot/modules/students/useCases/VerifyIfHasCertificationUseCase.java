package com.example.java_springboot.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.example.java_springboot.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {
    
    public boolean execute(VerifyHasCertificationDTO verifyHasCertificationDTO) {
        if(verifyHasCertificationDTO.getEmail().equals("lucfiduniv@gmail.com") 
           && verifyHasCertificationDTO.getTechnology().equals("JAVA")) {
            return true;
        }
        return false;
    }
}
