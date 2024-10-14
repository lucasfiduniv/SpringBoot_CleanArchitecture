package com.example.java_springboot.modules.students.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_springboot.modules.questions.entities.QuestionEntity;
import com.example.java_springboot.modules.questions.repositories.QuestionRepository;
import com.example.java_springboot.modules.students.dto.StudentCertificationAnswerDTO;
import com.example.java_springboot.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {
    
    private final StudentRepository studentRepository;

    @Autowired
    public StudentCertificationAnswersUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        var student = studentRepository.findByEmail(studentCertificationAnswerDTO.getEmail());
        
        if (student.isEmpty()) {
            throw new RuntimeException("Email não encontrado");
        }
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswerDTO.getTechnology());
        studentCertificationAnswerDTO.getQuestionsAnswers().stream().forEach(questionsAnswer -> {
          var questions =  questionsEntity.stream().filter(question -> question.getId().equals(questionsAnswer.getId())).findFirst().get();
          var findCorrectAlternative = questions.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

          if (findCorrectAlternative.getId().equals(questionsAnswer.getAlternativeId())) {
            questionAnswer.setCorrect(false);
          }else{
            questionAnswer.setCorrect(false);
          } 
        });
        return studentCertificationAnswerDTO;
    }
}
