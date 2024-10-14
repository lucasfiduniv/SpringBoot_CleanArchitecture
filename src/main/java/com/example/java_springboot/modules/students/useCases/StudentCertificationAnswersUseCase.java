package com.example.java_springboot.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_springboot.modules.questions.entities.AlternativesEntity;
import com.example.java_springboot.modules.questions.entities.QuestionEntity;
import com.example.java_springboot.modules.questions.repositories.QuestionRepository;
import com.example.java_springboot.modules.students.dto.StudentCertificationAnswerDTO;
import com.example.java_springboot.modules.students.dto.VerifyHasCertificationDTO;
import com.example.java_springboot.modules.students.entities.AnswersCertificationsEntity;
import com.example.java_springboot.modules.students.entities.CertificationStudentEntity;
import com.example.java_springboot.modules.students.entities.StudentEntity;
import com.example.java_springboot.modules.students.repositories.CertificationStudentRepository;
import com.example.java_springboot.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private final QuestionRepository questionRepository;

	@Autowired
	private CertificationStudentRepository certificationStudentRepository;

	@Autowired
	public VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

	public StudentCertificationAnswersUseCase(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public CertificationStudentEntity execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO)
			throws Exception {
		var hasCertification = this.verifyIfHasCertificationUseCase
				.execute(new VerifyHasCertificationDTO(studentCertificationAnswerDTO.getEmail(),
						studentCertificationAnswerDTO.getTechnology()));

		if (hasCertification) {
			throw new Exception("Student already has certification");
		}
		List<AnswersCertificationsEntity> answersCertificationsEntities = new ArrayList<>();

		List<QuestionEntity> questionsEntity = questionRepository
				.findByTechnology(studentCertificationAnswerDTO.getTechnology());

		final int[] correctAnswers = { 0 };

		studentCertificationAnswerDTO.getQuestionsAnswers().forEach(questionsAnswer -> {
			var question = questionsEntity.stream()
					.filter(q -> q.getId().equals(questionsAnswer.getId()))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Question not found"));

			var correctAlternative = question.getAlternatives().stream()
					.filter(AlternativesEntity::isCorrect)
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Correct alternative not found"));

			boolean isCorrect = correctAlternative.getId().equals(questionsAnswer.getAlternativeId());
			questionsAnswer.setIsCorrect(isCorrect);

			if (isCorrect) {
				correctAnswers[0]++;
			}

			var answersCertificationEntity = AnswersCertificationsEntity.builder()
					.answerID(questionsAnswer.getAlternativeId())
					.questionID(questionsAnswer.getId())
					.isCorrect(isCorrect)
					.build();

			answersCertificationsEntities.add(answersCertificationEntity);
		});

		var student = studentRepository.findByEmail(studentCertificationAnswerDTO.getEmail());
		UUID studentID;
		if (student.isEmpty()) {
			var studentCreated = StudentEntity.builder().email(studentCertificationAnswerDTO.getEmail()).build();
			studentCreated = studentRepository.save(studentCreated);
			studentID = studentCreated.getId();
		} else {
			studentID = student.get().getId();
		}

		CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
				.technology(studentCertificationAnswerDTO.getTechnology())
				.studentID(studentID)
				.grade(correctAnswers[0])
				.build();

		var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

		answersCertificationsEntities.forEach(answerCertification -> {
			answerCertification.setCertificationID(certificationStudentEntity.getId());
			answerCertification.setCertificationStudentEntity(certificationStudentEntity);
		});
		certificationStudentEntity.setAnswersCertificationsEntities(answersCertificationsEntities);

		certificationStudentRepository.save(certificationStudentEntity);

		return certificationStudentCreated;
	}
}
