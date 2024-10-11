package com.example.java_springboot.modules.students.controllers.entities;

import java.util.UUID;

public class AnswersCertificationsEntity {
    
    private UUID id;
    private UUID certificationID;
    private UUID studentID;
    private UUID answerID;
    private Boolean isCorrect;
}
