package com.example.java_springboot.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_springboot.modules.students.dto.StudentCertificationAnswerDTO;
import com.example.java_springboot.modules.students.dto.VerifyHasCertificationDTO;
import com.example.java_springboot.modules.students.entities.CertificationStudentEntity;
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
      if (result) {
         return "usuario ja fez a prova";
      }
      return "usuario nao fez a prova";
   }

   @PostMapping("/certification/answer")
   public ResponseEntity<Object> certificationAnswer(
         @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
      try {
         var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
         return ResponseEntity.ok().body(result);
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }

   }
}
