package com.example.java_springboot.modules.students.controllers.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersCertificationsEntity {
    
    private UUID id;
    private UUID certificationID;
    private UUID studentID;
    private UUID answerID;
    private Boolean isCorrect;
}
