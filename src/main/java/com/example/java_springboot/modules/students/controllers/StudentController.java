package com.example.java_springboot.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_springboot.modules.students.dto.StudentCertificationAnswerDTO;
import com.example.java_springboot.modules.students.dto.VerifyHasCertificationDTO;
import com.example.java_springboot.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.example.java_springboot.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyIfhasCertification")
    public String verifyIfhasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
     var result = this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
     if(result) {
        return "usuario ja fez a prova";
     }
        return "usuario nao fez a prova";
    }

    @PostMapping("/certificationAnswer")
    public StudentCertificationAnswerDTO certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
       return this.studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
    }
}
