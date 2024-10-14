package com.example.java_springboot.modules.students.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_springboot.modules.questions.entities.AlternativesEntity;
import com.example.java_springboot.modules.questions.entities.QuestionEntity;
import com.example.java_springboot.modules.questions.repositories.QuestionRepository;
import com.example.java_springboot.modules.students.dto.StudentCertificationAnswerDTO;

@Service
public class StudentCertificationAnswersUseCase {

	private final QuestionRepository questionRepository;

	public StudentCertificationAnswersUseCase(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO) {

		List<QuestionEntity> questionsEntity = questionRepository
				.findByTechnology(studentCertificationAnswerDTO.getTechnology());

		studentCertificationAnswerDTO.getQuestionsAnswers().forEach(questionsAnswer -> {
			var question = questionsEntity.stream()
					.filter(q -> q.getId().equals(questionsAnswer.getId()))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Question not found"));

			var correctAlternative = question.getAlternatives().stream()
					.filter(AlternativesEntity::isCorrect)
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Correct alternative not found"));

			questionsAnswer.setIsCorrect(correctAlternative.getId().equals(questionsAnswer.getAlternativeId()));
		});

		return studentCertificationAnswerDTO;
	}
}
