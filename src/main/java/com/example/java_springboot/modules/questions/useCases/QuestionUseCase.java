package com.example.java_springboot.modules.questions.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_springboot.modules.questions.dto.AlternativesResultDTO;
import com.example.java_springboot.modules.questions.dto.QuestionResultDTO;
import com.example.java_springboot.modules.questions.entities.AlternativesEntity;
import com.example.java_springboot.modules.questions.entities.QuestionEntity;
import com.example.java_springboot.modules.questions.repositories.QuestionRepository;

@Service
public class QuestionUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    
    public List<QuestionResultDTO> findByTechnology(String technology) {
        var result = questionRepository.findByTechnology(technology);

        return result.stream()
                .map(this::mapQuestionToDTO)
                .collect(Collectors.toList());
    }
    private QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription())
                .build();

        List<AlternativesResultDTO> alternativesResultDTOs = question.getAlternatives()
                .stream()
                .map(this::mapAlternativeDTO)
                .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;
    }

    private AlternativesResultDTO mapAlternativeDTO(AlternativesEntity alternative) {
        return AlternativesResultDTO.builder()
                .id(alternative.getId())
                .description(alternative.getDescription())
                .build();
    }
}
