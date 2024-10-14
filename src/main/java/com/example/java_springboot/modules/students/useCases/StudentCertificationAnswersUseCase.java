package com.example.java_springboot.modules.students.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_springboot.modules.questions.entities.QuestionEntity;
import com.example.java_springboot.modules.questions.repositories.QuestionRepository;
import com.example.java_springboot.modules.students.dto.StudentCertificationAnswerDTO;

@Service
public class StudentCertificationAnswersUseCase {
    
    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
     
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswerDTO.getTechnology());
        studentCertificationAnswerDTO.getQuestionsAnswers().stream().forEach(questionsAnswer -> {
          var questions =  questionsEntity.stream().filter(question -> question.getId().equals(questionsAnswer.getId())).findFirst().get();
          var findCorrectAlternative = questions.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

          if (findCorrectAlternative.getId().equals(questionsAnswer.getAlternativeId())) {
            questionsAnswer.setIsCorrect(true);
          }else{
            questionsAnswer.setIsCorrect(false);
          } 
        });
        return studentCertificationAnswerDTO;
    }
}
