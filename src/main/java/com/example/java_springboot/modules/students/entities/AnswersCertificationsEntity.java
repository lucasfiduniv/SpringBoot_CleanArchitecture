package com.example.java_springboot.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certifications_students")
public class AnswersCertificationsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "certification_id", nullable = false)  
    private CertificationStudentEntity certification;

   
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @Column(name = "question_id")
    private UUID questionID;

    @Column(name = "answer_id")
    private UUID answerID;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
